package com.example.tinderclone.DB;

import android.content.Context;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.tinderclone.Swipes.Swipe;
import com.example.tinderclone.Users.User;
import java.util.HashMap;
import java.util.Map;

public class DBHandler {

    // Users
    public static void createUser(Context context, User user) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.URL_USER_REGISTRATION, response -> {}, error -> {}
        ) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("name", user.getName());
                params.put("email", user.getMail());
                params.put("password", user.getPassword());
                params.put("phone", user.getPhoneNumber());
                params.put("bio", user.getBio());
                params.put("birthday", user.getBirthday());
                params.put("main_photo_path", user.getMainPhoto());
                params.put("latitude", String.valueOf(user.getLatitude()));
                params.put("longitude", String.valueOf(user.getLongitude()));
                params.put("city", user.getCity());

                return params;
            }
        };
        RequestHandler.getInstance(context).addToRequestQueue(stringRequest);
    }
    public static void getUserByEmailAndPassword(Context context, String email, String password, VolleyCallback callback) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.URL_GET_USER_BY_EMAIL_AND_PASSWORD, callback::onSuccess, callback::onError
        ) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("email", email);
                params.put("password", password);

                return params;
            }
        };
        RequestHandler.getInstance(context).addToRequestQueue(stringRequest);
    }
    public static void updateUser(Context context, User user) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.URL_UPDATE_USER, response -> {}, error -> {}
        ) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("name", user.getName());
                params.put("email", user.getMail());
                params.put("password", user.getPassword());
                params.put("phone", user.getPhoneNumber());
                params.put("bio", user.getBio());
                params.put("birthday", user.getBirthday());
                params.put("main_photo_path", user.getMainPhoto());
                params.put("latitude", String.valueOf(user.getLatitude()));
                params.put("longitude", String.valueOf(user.getLongitude()));
                params.put("city", user.getCity());

                return params;
            }
        };
        RequestHandler.getInstance(context).addToRequestQueue(stringRequest);
    }
    public static void getUserByCoordinates(Context context, User mainUser, long cardId, double radius, VolleyCallback callback) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.URL_GET_USER_BY_RADIUS, callback::onSuccess, callback::onError) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("id", String.valueOf(mainUser.getId()));
                params.put("latitude", String.valueOf(mainUser.getLatitude()));
                params.put("longitude", String.valueOf(mainUser.getLongitude()));
                params.put("radius", String.valueOf(radius));
                params.put("card_id", String.valueOf(cardId));

                return params;
            }
        };
        RequestHandler.getInstance(context).addToRequestQueue(stringRequest);
    }
    public static void getUserById(Context context, long id, VolleyCallback callback) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.URL_GET_USER_BY_ID, callback::onSuccess, callback::onError) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("id", String.valueOf(id));

                return params;
            }
        };
        RequestHandler.getInstance(context).addToRequestQueue(stringRequest);
    }

    // Photos
    public static void uploadPhoto(Context context, String encodedImage, String owner) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.URL_UPLOAD_PHOTO, response -> {}, error -> {}
        ) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("image", encodedImage);
                params.put("owner", owner);

                return params;
            }
        };
        RequestHandler.getInstance(context).addToRequestQueue(stringRequest);
    }
    public static void getPhotos(Context context,  String ownerEmail, VolleyCallback callback) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.URL_GET_USERS_PHOTOS, callback::onSuccess, error -> {}
        ) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("email", ownerEmail);

                return params;
            }
        };
        RequestHandler.getInstance(context).addToRequestQueue(stringRequest);
    }

    // Swipes
    public static void createSwipe(Context context, Swipe swipe) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.URL_CREATE_SWIPE, response -> {}, error -> {}
        ) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("user_1", String.valueOf(swipe.getWasVisitedBy()));
                params.put("user_2", String.valueOf(swipe.getWhoWasVisited()));
                params.put("is_right", String.valueOf(swipe.isRightSwipe()));

                return params;
            }
        };
        RequestHandler.getInstance(context).addToRequestQueue(stringRequest);
    }

    // Sympathies
    public static void createSympathy(Context context, long firstUserId, long secondUserId) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.URL_CREATE_SYMPATHY, response -> {}, error -> {}
        ) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("user_1", String.valueOf(firstUserId));
                params.put("user_2", String.valueOf(secondUserId));

                return params;
            }
        };
        RequestHandler.getInstance(context).addToRequestQueue(stringRequest);
    }

    // Chats
    public static void createChat(Context context, long firstUserId, long secondUserId) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.URL_CREATE_CHAT, response -> {}, error -> {}
        ) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("user_1", String.valueOf(firstUserId));
                params.put("user_2", String.valueOf(secondUserId));

                return params;
            }
        };
        RequestHandler.getInstance(context).addToRequestQueue(stringRequest);
    }
    public static void getChats(Context context, long userId, VolleyCallback callback) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.URL_GET_CHATS, callback::onSuccess, callback::onError
        ) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("user_id", String.valueOf(userId));

                return params;
            }
        };
        RequestHandler.getInstance(context).addToRequestQueue(stringRequest);
    }

    // Recommendations
    public static void getWhoLikesMe(Context context, long userId, VolleyCallback callback) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.URL_GET_WHO_LIKES_ME, callback::onSuccess, callback::onError
        ) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("id_user", String.valueOf(userId));

                return params;
            }
        };
        RequestHandler.getInstance(context).addToRequestQueue(stringRequest);
    }
    public static void getRecommendations(Context context, User user, int radius, VolleyCallback callback) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.URL_GET_RECOMMENDATIONS, callback::onSuccess, callback::onError
        ) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("id", String.valueOf(user.getId()));
                params.put("latitude", String.valueOf(user.getLatitude()));
                params.put("longitude", String.valueOf(user.getLongitude()));
                params.put("radius", String.valueOf(radius));

                return params;
            }
        };
        RequestHandler.getInstance(context).addToRequestQueue(stringRequest);
    }

    // VolleyCallback
    public interface VolleyCallback {
        void onSuccess(String result);
        void onError(VolleyError error);
    }
}
