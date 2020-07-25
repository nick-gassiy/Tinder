package com.example.tinderclone.MainWindows;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.tinderclone.Chat.ChatItem;
import com.example.tinderclone.Chat.ChatItemAdapter;
import com.example.tinderclone.InnerWindows.ChatActivity;
import com.example.tinderclone.R;
import com.example.tinderclone.Users.User;
import java.util.ArrayList;

public class ChatWindowFragment extends Fragment {

    private User mainUser;
    private TextView starterChatText;
    private TextView messagesIntro;

    private ArrayList<ChatItem> chatItemArrayList;
    private RecyclerView userRecyclerView;
    private ChatItemAdapter chatItemAdapter;
    private RecyclerView.LayoutManager userLayoutManager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (getArguments() != null)
            mainUser = (User) getArguments().get("user");
        return inflater.inflate(R.layout.activity_chat_window_fragment,container,false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        starterChatText = getActivity().findViewById(R.id.starterChatText);
        messagesIntro = getActivity().findViewById(R.id.messagesIntro);
        chatItemArrayList = new ArrayList<>();
        userRecyclerView = getActivity().findViewById(R.id.chatListRecyclerView);

        if (!isChatListEmpty()) {
            starterChatText.setVisibility(View.INVISIBLE);
            messagesIntro.setVisibility(View.INVISIBLE);
            userRecyclerView.setVisibility(View.VISIBLE);
            buildRecyclerView();
            attachItems();
        } else {
            starterChatText.setVisibility(View.VISIBLE);
            messagesIntro.setVisibility(View.VISIBLE);
            userRecyclerView.setVisibility(View.INVISIBLE);
        }
    }

    //получение чата из бд
    private void attachItems() {
        ChatItem item1 = new ChatItem(0, "Марічка", null);
        ChatItem item2 = new ChatItem(1, "Аня", null);
        ChatItem item3 = new ChatItem(2, "Катя", null);
        ChatItem item4 = new ChatItem(3, "Настя", null);
        ChatItem item5 = new ChatItem(4, "Самка 228", null);
        ChatItem item6 = new ChatItem(5, "Алина", null);
        ChatItem item7 = new ChatItem(6, "Алина", null);
        ChatItem item8 = new ChatItem(7, "Овца", null);
        chatItemArrayList.add(item1);
        chatItemArrayList.add(item2);
        chatItemArrayList.add(item3);
        chatItemArrayList.add(item4);
        chatItemArrayList.add(item5);
        chatItemArrayList.add(item6);
        chatItemArrayList.add(item7);
        chatItemArrayList.add(item8);
        chatItemAdapter.notifyDataSetChanged();
    }

    private void buildRecyclerView() {
        userRecyclerView.setHasFixedSize(true);
        userLayoutManager = new LinearLayoutManager(getActivity());
        chatItemAdapter = new ChatItemAdapter(chatItemArrayList);

        userRecyclerView.setLayoutManager(userLayoutManager);
        userRecyclerView.setAdapter(chatItemAdapter);

        chatItemAdapter.setOnUserClickListener(this::goToChat);
    }

    private void goToChat(int position) {
        Intent intent = new Intent(ChatWindowFragment.this.getContext(), ChatActivity.class);
        intent.putExtra("user", mainUser);
        intent.putExtra("chat", chatItemArrayList.get(position));
        startActivity(intent);
    }

    // Подвязать проверку из бд
    private boolean isChatListEmpty() {
        return false;
    }
}