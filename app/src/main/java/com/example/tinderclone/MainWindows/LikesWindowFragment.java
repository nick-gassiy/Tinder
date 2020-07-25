package com.example.tinderclone.MainWindows;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.VolleyError;
import com.example.tinderclone.DB.DBHandler;
import com.example.tinderclone.Helpers.ModelsFiller;
import com.example.tinderclone.Helpers.Utils;
import com.example.tinderclone.LikesAndRecommendations.Recommendation;
import com.example.tinderclone.LikesAndRecommendations.RecommendationAdapter;
import com.example.tinderclone.R;
import com.example.tinderclone.Users.User;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class LikesWindowFragment extends Fragment {

    private User mainUser;

    private List<Recommendation[]> recommendationList;
    private RecyclerView recyclerView;
    private RecommendationAdapter adapter;
    private RecyclerView.LayoutManager manager;

    private TextView amountLikes;
    private TextView topSimilar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (getArguments() != null)
            mainUser = (User) getArguments().get("user");
        return inflater.inflate(R.layout.activity_likes_window_fragment,container,false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        amountLikes = getActivity().findViewById(R.id.amountLikes);
        topSimilar = getActivity().findViewById(R.id.topSimilar);

        onLikes();

        amountLikes.setOnClickListener(view -> onLikes());
        topSimilar.setOnClickListener(view -> onRecommendations());
    }

    private void onLikes() {
        amountLikes.setTextColor(getResources().getColor(R.color.black));
        topSimilar.setTextColor(getResources().getColor(R.color.gray_light));

        recommendationList = new ArrayList<>();
        recyclerView = getActivity().findViewById(R.id.likeGoldRecyclerView);

        buildRecyclerView();
        attachItemsLikes();
    }

    private void onRecommendations() {
        topSimilar.setTextColor(getResources().getColor(R.color.black));
        amountLikes.setTextColor(getResources().getColor(R.color.gray_light));

        recommendationList = new ArrayList<>();
        recyclerView = getActivity().findViewById(R.id.likeGoldRecyclerView);

        buildRecyclerView();
        attachItemsRecommendations();
    }

    private void buildRecyclerView() {
        recyclerView.setHasFixedSize(true);
        manager = new LinearLayoutManager(getActivity());
        adapter = new RecommendationAdapter(recommendationList, position -> {
            if (position < 0) {
                position++;
                position *= -1;
                Toast.makeText(getActivity(), adapter.getRecommendationRow(position)[0].getNameAge(), Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getActivity(), adapter.getRecommendationRow(position)[1].getNameAge(), Toast.LENGTH_SHORT).show();
            }
        });

        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
    }

    private void attachItemsRecommendations() {
        DBHandler.getRecommendations(LikesWindowFragment.this.getActivity(), mainUser, 10, new DBHandler.VolleyCallback() {
            @Override
            public void onSuccess(String result) {
                onAttachingSuccess(result);
            }
            @Override
            public void onError(VolleyError error) { }
        });
    }

    private void attachItemsLikes() {
        DBHandler.getWhoLikesMe(LikesWindowFragment.this.getActivity(), mainUser.getId(), new DBHandler.VolleyCallback() {
            @Override
            public void onSuccess(String result) {
                onAttachingSuccess(result);
            }

            @Override
            public void onError(VolleyError error) { }
        });
    }

    private void onAttachingSuccess(String result) {
        try {
            List<Recommendation> recommendations = new ArrayList<>();
            JSONArray jsonArray = new JSONObject(result).getJSONArray("result");
            for (int i = 0; i < jsonArray.length(); i++) {
                Recommendation recommendation = new Recommendation();
                recommendation.setId(jsonArray.getJSONObject(i).getLong("id"));
                DBHandler.getUserById(LikesWindowFragment.this.getActivity(), recommendation.getId(), new DBHandler.VolleyCallback() {
                    @Override
                    public void onSuccess(String result) {
                        try {
                            JSONObject obj = new JSONObject(result);
                            User localUser = ModelsFiller.fillUser(obj);
                            recommendation.setImageUrl(localUser.getMainPhoto());
                            String nameAge = localUser.getName() + ", " + Utils.howOld(localUser.getBirthday());
                            recommendation.setNameAge(nameAge);
                            recommendation.setUserMail(localUser.getMail());
                            recommendations.add(recommendation);

                            if (recommendations.size() == jsonArray.length()) {
                                if (recommendations.size() % 2 != 0) {
                                    for (int i = 0; i < recommendations.size() - 1; i += 2) {
                                        Recommendation[] arr = new Recommendation[2];
                                        arr[0] = recommendations.get(i);
                                        arr[1] = recommendations.get(i + 1);

                                        recommendationList.add(arr);
                                    }
                                    Recommendation[] arr = new Recommendation[1];
                                    arr[0] = recommendations.get(recommendations.size() - 1);
                                    recommendationList.add(arr);
                                } else {
                                    for (int i = 0; i < recommendations.size(); i += 2) {
                                        Recommendation[] arr = new Recommendation[2];
                                        arr[0] = recommendations.get(i);
                                        arr[1] = recommendations.get(i + 1);

                                        recommendationList.add(arr);
                                    }
                                }
                                adapter.notifyDataSetChanged();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(VolleyError error) { }
                });

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}