package com.example.tinderclone.InnerWindows;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.util.Base64;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import com.example.tinderclone.Chat.ChatItem;
import com.example.tinderclone.Chat.Message;
import com.example.tinderclone.Chat.MessageAdapter;
import com.example.tinderclone.R;
import com.example.tinderclone.Users.User;
import com.squareup.picasso.Picasso;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import de.hdodenhof.circleimageview.CircleImageView;

public class ChatActivity extends AppCompatActivity {

    private ChatItem chatItem;
    private ListView messageListView;
    private MessageAdapter adapter;
    private ImageButton sendImageButton;
    private ImageButton sendMessageButton;
    private EditText messageEditText;

    private static final int RC_IMAGE_PICKER = 17;
    private TextView userNameChat;
    private CircleImageView avatarChat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        Intent givenIntent = getIntent();
        ChatItem givenItem = givenIntent.getParcelableExtra("chat");
        User user = givenIntent.getParcelableExtra("user");
        chatItem = new ChatItem(givenItem.getId(), givenItem.getChatName(), givenItem.getImage(),
                user.getName(), user.getMainPhoto(), user.getId(), givenItem.getReceiverId());

        sendImageButton = findViewById(R.id.sendPhotoChat);
        sendMessageButton = findViewById(R.id.sendMessageChat);
        messageEditText = findViewById(R.id.chatTextView);
        userNameChat = findViewById(R.id.userNameChat);
        avatarChat = findViewById(R.id.avatarChat);
        sendMessageButton.setEnabled(false);

        userNameChat.setText(chatItem.getChatName());
        Picasso.get().load(chatItem.getImage()).into(avatarChat);

        messageListView = findViewById(R.id.messagesListView);
        List<Message> messages = new ArrayList<>();
        adapter = new MessageAdapter(this, R.layout.item_message, messages, user);
        messageListView.setAdapter(adapter);

        preloadMessage();

        messageEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().trim().length() > 0)
                    sendMessageButton.setEnabled(true);
                else
                    sendMessageButton.setEnabled(false);
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        messageEditText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(500)});

        sendMessageButton.setOnClickListener(view -> {
            Message message = new Message(chatItem.getId(), messageEditText.getText().toString(), null,
                    chatItem.getSenderId(), chatItem.getSender());

            //Создание сообщения для БД
            adapter.add(message);
            messageEditText.setText("");
        });

        sendImageButton.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
            intent.setType("image/*");
            intent.putExtra(Intent.EXTRA_LOCAL_ONLY, true);
            startActivityForResult(Intent.createChooser(intent, "Choose image"), RC_IMAGE_PICKER);
        });
    }

    private void preloadMessage() {

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == RC_IMAGE_PICKER && resultCode == RESULT_OK && data != null) {
            Uri uri = data.getData();
            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);

                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 74, outputStream);
                byte[] imageBytes = outputStream.toByteArray();

                String encodedImage = android.util.Base64.encodeToString(imageBytes, Base64.DEFAULT);
                String imageData = encodedImage;

                //Создание сообщения для БД, imageData - параметр в бд

                Message message = new Message(chatItem.getId(), null, uri.toString(), chatItem.getSenderId(), chatItem.getSender());
                adapter.add(message);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
