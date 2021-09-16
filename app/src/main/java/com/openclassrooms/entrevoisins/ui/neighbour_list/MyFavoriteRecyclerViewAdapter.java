package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.events.DeleteNeighbourEvent;
import com.openclassrooms.entrevoisins.model.Neighbour;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyFavoriteRecyclerViewAdapter extends RecyclerView.Adapter<MyFavoriteRecyclerViewAdapter.ViewHolder> {

    private final List<Neighbour> mFavorites;

    public MyFavoriteRecyclerViewAdapter(List<Neighbour> items) {
        mFavorites = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_favorite, parent, false);
        return new ViewHolder(view);
    }




    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Neighbour neighbour = mFavorites.get(position);
        holder.mFavoriteName.setText(neighbour.getName());
        Glide.with(holder.mFavoriteAvatar.getContext())
                .load(neighbour.getAvatarUrl())
                .apply(RequestOptions.circleCropTransform())
                .into(holder.mFavoriteAvatar);

        holder.mFavoriteName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent detailIntent = new Intent(holder.mFavoriteName.getContext(), DetailActivity.class);
                detailIntent.putExtra("neighbour", mFavorites.get(position));
                holder.mFavoriteName.getContext().startActivity(detailIntent);
            }
        });


        holder.mDeleteButtonFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new DeleteNeighbourEvent(neighbour));
            }
        });
    }

    @Override
    public int getItemCount() {
        return mFavorites.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_list_avatar_favorites)
        public ImageView mFavoriteAvatar;
        @BindView(R.id.item_list_name_favorites)
        public TextView mFavoriteName;
        @BindView(R.id.item_list_delete_button_favorites)
        public ImageButton mDeleteButtonFav;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);


        }
    }

}
