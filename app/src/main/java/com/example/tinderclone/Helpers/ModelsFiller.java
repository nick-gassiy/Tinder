package com.example.tinderclone.Helpers;

import com.example.tinderclone.Chat.ChatItem;
import com.example.tinderclone.Users.User;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.List;

public class ModelsFiller {

    public static User fillUser(JSONObject jsonData) throws JSONException {
        User user = new User();
        user.setId(jsonData.getInt("id"));
        user.setName(jsonData.getString("name"));
        user.setMail(jsonData.getString("email"));
        user.setPassword(jsonData.getString("password"));
        user.setPhoneNumber(jsonData.getString("phone"));
        user.setBio(jsonData.getString("bio"));
        user.setCity(jsonData.getString("city"));
        user.setBirthday(jsonData.getString("birthday"));
        user.setMainPhoto(jsonData.getString("main_photo_path"));
        user.setLatitude(Double.parseDouble(jsonData.getString("latitude")));
        user.setLongitude(Double.parseDouble(jsonData.getString("longitude")));
        return user;
    }

    public static void fillImagesList(List<String> images, String jsonData) {
        try {
            JSONArray jsonArray = new JSONArray(jsonData);
            for (int i = 0; i < jsonArray.length(); i++)
                images.add(jsonArray.getJSONObject(i).getString("image_path"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static ChatItem chatItemFillerId(JSONObject jsonObject) throws JSONException {
        ChatItem item = new ChatItem();
        item.setId(jsonObject.getLong("id"));
        item.setFirstUserId(jsonObject.getLong("participant_1"));
        item.setSecondUserId(jsonObject.getLong("participant_2"));
        return item;
    }

    public static ChatItem chatItemFillerOtherFields(ChatItem chatItem, User user, JSONObject jsonObject) throws JSONException {
        ChatItem item = chatItem;
        chatItem.setChatName(user.getName());
        chatItem.setImage(user.getMainPhoto());
        return item;
    }
}
