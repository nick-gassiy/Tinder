package com.example.tinderclone.MainWindows;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.tinderclone.Helpers.Utils;
import com.example.tinderclone.InnerWindows.ProfileEditActivity;
import com.example.tinderclone.InnerWindows.ProfileSettingsActivity;
import com.example.tinderclone.R;
import com.example.tinderclone.Users.User;
import com.squareup.picasso.Picasso;
import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileWindowFragment extends Fragment {

    private User mainUser;
    private CircleImageView userProfilePhoto;
    private CircleImageView addPhoto;
    private CircleImageView settings;
    private CircleImageView editProfile;
    private TextView nameAgeProfileTextView;
    private TextView bioProfileTextView;
    private TextView settingsText;
    private TextView addPhotosText;
    private TextView editProfileText;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (getArguments() != null)
            mainUser = (User) getArguments().get("user");
        return inflater.inflate(R.layout.activity_profile_window_fragment,container,false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        userProfilePhoto = getActivity().findViewById(R.id.userProfilePhoto);
        nameAgeProfileTextView = getActivity().findViewById(R.id.nameAgeProfileTextView);
        bioProfileTextView = getActivity().findViewById(R.id.bioProfileTextView);
        loadInfo();

        addPhoto = getActivity().findViewById(R.id.addPhotosButton);
        settings = getActivity().findViewById(R.id.settingsButton);
        editProfile = getActivity().findViewById(R.id.editProfile);

        addPhotosText = getActivity().findViewById(R.id.addPhotosText);
        settingsText = getActivity().findViewById(R.id.settingsText);
        editProfileText = getActivity().findViewById(R.id.editProfileText);

        //addPhoto.setOnClickListener(view -> startActivity(Utils.goToNextActivity(mainUser, new Intent(getActivity(), ProfilePhotosActivity.class))));
        //addPhotosText.setOnClickListener(view -> startActivity(Utils.goToNextActivity(mainUser, new Intent(getActivity(), ProfilePhotosActivity.class))));
        settings.setOnClickListener(view -> startActivity(Utils.goToNextActivity(mainUser, new Intent(getActivity(), ProfileSettingsActivity.class))));
        settingsText.setOnClickListener(view -> startActivity(Utils.goToNextActivity(mainUser, new Intent(getActivity(), ProfileSettingsActivity.class))));
        editProfile.setOnClickListener(view -> startActivity(Utils.goToNextActivity(mainUser, new Intent(getActivity(), ProfileEditActivity.class))));
        editProfileText.setOnClickListener(view -> startActivity(Utils.goToNextActivity(mainUser, new Intent(getActivity(), ProfileEditActivity.class))));
    }

    private void loadInfo() {
        if (mainUser.getMainPhoto() != null)
            Picasso.get().load(mainUser.getMainPhoto()).into(userProfilePhoto);
        String years = Utils.howOld(mainUser.getBirthday());
        nameAgeProfileTextView.setText(mainUser.getName() + ", " + years);
        bioProfileTextView.setText(mainUser.getBio());
    }
}