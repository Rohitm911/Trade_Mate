package com.example.trademate;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class UserDashboard extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    RecyclerView featuredRecycler, mostViewedRecycler, categoriesRecycler;
    RecyclerView.Adapter adapter;
    private GradientDrawable gradient1, gradient2, gradient3, gradient4;
    static final float END_SCALE = 0.7f;
    SharedPreferences sharedPreferences;

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ImageView menuIcon;
    LinearLayout contentView;
    ImageView electronics_img;
    ImageView books_img;
    ImageView household_img;
    ImageView grocery_img;
    ImageView cart_img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_user_dashboard);
        featuredRecycler = findViewById(R.id.featured_recycler);
        mostViewedRecycler = findViewById(R.id.most_viewed_recycler);
        categoriesRecycler = findViewById(R.id.categories_recycler);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_view);
        menuIcon = findViewById(R.id.menu_icon);
        contentView = findViewById(R.id.contentView);
        sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);

        //go to electronics items
        electronics_img = findViewById(R.id.electronics);
        electronics_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserDashboard.this, Electronics_category.class);
                startActivity(intent);
                //finish();
            }

        });

        //go to books items
        books_img = findViewById(R.id.books);
        books_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserDashboard.this, Books_category.class);
                startActivity(intent);
                //finish();
            }

        });

        // go to house hold
        household_img = findViewById(R.id.household);
        household_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserDashboard.this, Household_category.class);
                startActivity(intent);
                //finish();
            }

        });

        //go to grocery
        grocery_img = findViewById(R.id.grocery);
        grocery_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserDashboard.this, Grocery_category.class);
                startActivity(intent);
                //finish();
            }

        });

        //go to cart
        cart_img = findViewById(R.id.go_to_cart);
        cart_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserDashboard.this, cart.class);
                startActivity(intent);
            }
        });


        naviagtionDrawer();
        featuredRecycler();
        mostViewedRecycler();
        categoriesRecycler();
    }

    private void naviagtionDrawer() {
        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_home);
        menuIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (drawerLayout.isDrawerVisible(GravityCompat.START))
                    drawerLayout.closeDrawer(GravityCompat.START);
                else drawerLayout.openDrawer(GravityCompat.START);
            }
        });
        animateNavigationDrawer();
    }

    private void animateNavigationDrawer() {

        drawerLayout.addDrawerListener(new DrawerLayout.SimpleDrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {

                // Scale the View based on current slide offset
                final float diffScaledOffset = slideOffset * (1 - END_SCALE);
                final float offsetScale = 1 - diffScaledOffset;
                contentView.setScaleX(offsetScale);
                contentView.setScaleY(offsetScale);

                // Translate the View, accounting for the scaled width
                final float xOffset = drawerView.getWidth() * slideOffset;
                final float xOffsetDiff = contentView.getWidth() * diffScaledOffset / 2;
                final float xTranslation = xOffset - xOffsetDiff;
                contentView.setTranslationX(xTranslation);
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerVisible(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else
            super.onBackPressed();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        switch (menuItem.getItemId()) {

            case R.id.nav_all_categories:
                startActivity(new Intent(getApplicationContext(), AllCategories.class));
                break;
            case R.id.nav_search:
                startActivity(new Intent(getApplicationContext(), Search.class));
                break;
            case R.id.nav_user_profile:
                startActivity(new Intent(getApplicationContext(), UserProfile.class));
                break;
            case R.id.nav_home:
                startActivity(new Intent(getApplicationContext(), UserDashboard.class));
                finish();
                break;
            case R.id.nav_cart:
                startActivity(new Intent(getApplicationContext(), cart.class));
                break;
            case R.id.nav_logout:
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear();
                editor.commit();
                startActivity(new Intent(getApplicationContext(), login.class));
                finish();
                break;
        }

        return true;
    }

    private void featuredRecycler() {
        featuredRecycler.setHasFixedSize(true);
        featuredRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        ArrayList<FeaturedHelperClass> bestOffers = new ArrayList<>();
        bestOffers.add(new FeaturedHelperClass(R.drawable.earphone, "BoAt Earphone", "Rs. 299"));
        bestOffers.add(new FeaturedHelperClass(R.drawable.half_girlfriend_book, "Half Girlfriend", "Rs. 120"));
        bestOffers.add(new FeaturedHelperClass(R.drawable.real_juice, "Real Fruit Juice - Mango", "Rs. 85"));

        adapter = new FeaturedAdapter(bestOffers);
        featuredRecycler.setAdapter(adapter);
    }

    private void mostViewedRecycler() {
        mostViewedRecycler.setHasFixedSize(true);
        mostViewedRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        ArrayList<MostViewedHelperClass> mostViewedItems = new ArrayList<>();
        mostViewedItems.add(new MostViewedHelperClass(R.drawable.dettol_hand_sanitizer, "Dettol Hand Sanitizer", "Rs. 110"));
        mostViewedItems.add(new MostViewedHelperClass(R.drawable.ashirwad_atta, "AASHIRVAAD - Wheat Flour", "Rs. 434"));
        mostViewedItems.add(new MostViewedHelperClass(R.drawable.redmi_mobile, "Redmi Note 8 pro", "Rs. 15999"));
        adapter = new MostViewedAdpater(mostViewedItems);
        mostViewedRecycler.setAdapter(adapter);
    }

    private void categoriesRecycler() {
        gradient2 = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, new int[]{0xffd4cbe5, 0xffd4cbe5});
        gradient1 = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, new int[]{0xff7adccf, 0xff7adccf});
        gradient3 = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, new int[]{0xfff7c59f, 0xFFf7c59f});
        gradient4 = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, new int[]{0xffb8d7f5, 0xffb8d7f5});

        ArrayList<CategoriesHelperClass> categoriesHelperClasses = new ArrayList<>();
        categoriesHelperClasses.add(new CategoriesHelperClass(R.drawable.laptop_icon, "Electronics", gradient1));
        categoriesHelperClasses.add(new CategoriesHelperClass(R.drawable.book_icon, "Books", gradient2));
        categoriesHelperClasses.add(new CategoriesHelperClass(R.drawable.home_icon, "Household", gradient3));
        categoriesHelperClasses.add(new CategoriesHelperClass(R.drawable.cup_icon, "Grocery", gradient4));

        categoriesRecycler.setHasFixedSize(true);
        adapter = new CategoriesAdapter(categoriesHelperClasses);
        categoriesRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        categoriesRecycler.setAdapter(adapter);

    }

    public void goToSearch(View view) {
        startActivity(new Intent(getApplicationContext(), Search.class));
    }


}
