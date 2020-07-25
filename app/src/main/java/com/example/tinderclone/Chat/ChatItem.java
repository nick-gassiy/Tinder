package com.example.tinderclone.Chat;

import android.os.Parcel;
import android.os.Parcelable;

public class ChatItem implements Parcelable {
    private long id;
    private long senderId;
    private long receiverId;
    private String image;
    private String chatName;
    private String sender;
    private String senderImage;

    public ChatItem() { }

    //Конструктор для формирования списка переписок
    public ChatItem(long id, String chatName, String image) {
        this.id = id;
        this.chatName = chatName;
        this.image = image;
    }

    //Конструктор для самого чата
    public ChatItem(long id, String chatName, String image, String sender, String senderImage, long senderId, long receiverId) {
        this(id, chatName, image);
        this.sender = sender;
        this.senderImage = senderImage;
        this.senderId = senderId;
        this.receiverId = receiverId;
    }

    protected ChatItem(Parcel in) {
        id = in.readLong();
        image = in.readString();
        chatName = in.readString();
    }

    public static final Creator<ChatItem> CREATOR = new Creator<ChatItem>() {
        @Override
        public ChatItem createFromParcel(Parcel in) {
            return new ChatItem(in);
        }

        @Override
        public ChatItem[] newArray(int size) {
            return new ChatItem[size];
        }
    };

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }

    public String getChatName() {
        return chatName;
    }
    public void setChatName(String chatName) {
        this.chatName = chatName;
    }

    public String getSender() {
        return sender;
    }
    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getSenderImage() {
        return senderImage;
    }
    public void setSenderImage(String senderImage) {
        this.senderImage = senderImage;
    }

    public long getSenderId() {
        return senderId;
    }
    public void setSenderId(long senderId) {
        this.senderId = senderId;
    }

    public long getReceiverId() {
        return receiverId;
    }
    public void setReceiverId(long receiverId) {
        this.receiverId = receiverId;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(id);
        parcel.writeString(image);
        parcel.writeString(chatName);
    }
}
