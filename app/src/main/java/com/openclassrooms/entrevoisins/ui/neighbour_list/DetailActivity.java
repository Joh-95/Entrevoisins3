package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DetailActivity extends AppCompatActivity {

    @BindView(R.id.avatar_detail)
    ImageView imageAvatar;
    @BindView(R.id.name_title)
    TextView nameTitleDetail;
    @BindView(R.id.fabFavorites)
    FloatingActionButton fabFavorites;
    @BindView(R.id.name_detail)
    TextView nameDescriptionDetail;
    @BindView(R.id.adress_detail)
    TextView adressDetail;
    @BindView(R.id.phoneNumber_detail)
    TextView phoneNumberDetail;
    @BindView(R.id.aboutme_detail)
    TextView aboutMeTitle;
    @BindView(R.id.aboutmedescription_detail)
    TextView aboutMeDescriptionDetail;


    private NeighbourApiService mApiService = DI.getNeighbourApiService();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);

        Intent detailIntent = getIntent();
        Neighbour neighbour = detailIntent.getParcelableExtra("neighbour");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Glide.with(this).load(neighbour.getAvatarUrl()).into(imageAvatar);
        nameTitleDetail.setText(neighbour.getName());
        nameDescriptionDetail.setText(neighbour.getName());
        adressDetail.setText(neighbour.getAddress());
        phoneNumberDetail.setText(neighbour.getPhoneNumber());
        aboutMeDescriptionDetail.setText(neighbour.getAboutMe());






    fabFavorites.setOnClickListener(new View.OnClickListener()

    {
        @Override
        public void onClick (View v){
        if (!neighbour.isFav()) {
            neighbour.setFav(true);
            mApiService.addFavoriteNeighbour(neighbour);
            Toast.makeText(getApplicationContext(), "added", Toast.LENGTH_SHORT).show();
            fabFavorites.setImageResource(R.drawable.ic_star_white_24dp);
        } else {
            neighbour.setFav(false);
            mApiService.deleteFavoriteNeighbour(neighbour);
            Toast.makeText(getApplicationContext(), "removed", Toast.LENGTH_SHORT).show();
            fabFavorites.setImageResource(R.drawable.ic_star_border_white_24dp);
        }
        }
    });
}
}