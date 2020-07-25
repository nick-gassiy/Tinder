package com.example.tinderclone.DB;

public class Constants {
    private static final String ROOT_URL = "http://192.168.1.102/tinder/";

    // Users
    public static final String URL_USER_REGISTRATION = ROOT_URL + "create_user.php";
    public static final String URL_USER_CHECK_MAIL = ROOT_URL + "unique_email.php";
    public static final String URL_GET_USER_BY_EMAIL_AND_PASSWORD = ROOT_URL + "user_by_email_and_password.php";
    public static final String URL_GET_USER_BY_EMAIL_ONLY = ROOT_URL + "user_by_email_only.php";
    public static final String URL_UPDATE_USER = ROOT_URL + "update_user.php";
    public static final String URL_GET_USER_BY_RADIUS = ROOT_URL + "get_users_by_long_lat.php";
    public static final String URL_GET_USER_BY_ID = ROOT_URL + "user_by_id.php";

    // Photos
    public static final String URL_UPLOAD_PHOTO = ROOT_URL + "upload_photo.php";
    public static final String URL_GET_USERS_PHOTOS = ROOT_URL + "photos_by_user.php";

    // Swipes
    public static final String URL_CREATE_SWIPE = ROOT_URL + "create_swipe.php";

    // Sympathies
    public static final String URL_CREATE_SYMPATHY = ROOT_URL + "create_sympathy.php";

    // Chats
    public static final String URL_CREATE_CHAT = ROOT_URL + "create_chat.php";

    // Recommendations
    public static final String URL_GET_WHO_LIKES_ME = ROOT_URL + "get_likes_users.php";
    public static final String URL_GET_RECOMMENDATIONS = ROOT_URL + "get_recommendations.php";
}
