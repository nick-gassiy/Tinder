package com.example.tinderclone.Swipes;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.tinderclone.R;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.List;

public class CardStackAdapter extends RecyclerView.Adapter<CardStackAdapter.ViewHolder> {

    private List<ItemCard> items;

    public CardStackAdapter(List<ItemCard> items) {
        this.items = items;
    }

    public void addItem(ItemCard item) {
        items.add(item);
    }

    public void removeItem(int position) {
        items.remove(position);
    }

    public ItemCard getItem(int position) {
        return items.get(position);
    }

    public List<ItemCard> getItems() { return items;  }

    public List<Integer> getItemsId() {
        List<Integer> ids = new ArrayList<>();
        for (int i = 0; i < items.size(); i++)
            ids.add(items.get(i).getUserId());
        return ids;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.setData(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView name, age, about;
        long userId;
        ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.item_image_card);
            name = itemView.findViewById(R.id.item_name_card);
            age = itemView.findViewById(R.id.item_age_card);
            about = itemView.findViewById(R.id.item_about_card);
        }

        void setData(ItemCard data) {
            Picasso.get().load(data.getImage()).fit().centerCrop().into(image);
            name.setText(data.getName());
            age.setText(data.getAge());
            about.setText(data.getAbout());
            userId = data.getUserId();
        }
    }
}
