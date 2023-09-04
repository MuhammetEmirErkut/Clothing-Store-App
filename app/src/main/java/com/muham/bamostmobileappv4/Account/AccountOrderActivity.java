package com.muham.bamostmobileappv4.Account;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.muham.bamostmobileappv4.LoginListener;
import com.muham.bamostmobileappv4.R;
import com.muham.bamostmobileappv4.tasarimInterface;

public class AccountOrderActivity extends AppCompatActivity implements tasarimInterface, LoginListener {
    private View line1, line2, line3;
    private View x;

    private View searchX, search;
    private boolean isXShape = false;
    private boolean isXShapeForSearch = false;

    private boolean isXShapeForCart = false;

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private NavigationView navigationView;

    private DrawerLayout searchDrawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle2;
    private NavigationView searchNavigationView;


    private DrawerLayout cartDrawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle3;
    private NavigationView cartNavigationView;

    private Button headerButton;
    private EditText headerEditText;

    private ImageButton headerCartBackButton1;
    private Button headerCartBackButton2;

    View menuDrawerLayout;

    View searchDrawerLayoutH;

    View cartDrawerLayoutH;//Hİyerarşinin H si

    View menuviewScrollViewH;

    ImageView favoriView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_order);
        onInheritCreate();
    }

    @Override
    public void onLogin() {
        // Kullanıcı giriş yaptığında burası çalışır
        // Favori butonunu görünür yapabilirsiniz
        super.onStart();
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();

        if (currentUser != null) {
            // Kullanıcı oturum açmışsa favori butonunu görünür yap
            favoriView.setVisibility(View.VISIBLE);
        } else {
            // Kullanıcı oturum açmamışsa favori butonunu gizle
            favoriView.setVisibility(View.GONE);
        }
    }

    @Override
    public void menu() {
        line1 = findViewById(R.id.line1);
        line2 = findViewById(R.id.line2);
        line3 = findViewById(R.id.line3);
        x = findViewById(R.id.view1);
        x.setVisibility(View.INVISIBLE);

        searchX = findViewById(R.id.imageViewX);
        search = findViewById(R.id.imageViewSearch);

        menuviewScrollViewH = findViewById(R.id.mainviewLoginScrollView);

        menuDrawerLayout = findViewById(R.id.drawerLayout);//öne atmak için hiyerari

        drawerLayout = findViewById(R.id.drawerLayout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        navigationView = findViewById(R.id.tasarim_navigationview);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });
    }

    @Override
    public void search() {
        searchDrawerLayoutH = findViewById(R.id.searchDrawerLayout);//öne atmak için hiyerarşi

        searchDrawerLayout = findViewById(R.id.searchDrawerLayout);
        actionBarDrawerToggle2 = new ActionBarDrawerToggle(this, searchDrawerLayout, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        searchDrawerLayout.addDrawerListener(actionBarDrawerToggle2);
        actionBarDrawerToggle2.syncState();

        searchNavigationView = findViewById(R.id.search_navigationview);

        View headerView = searchNavigationView.getHeaderView(0);

        headerButton = headerView.findViewById(R.id.headerButton);
        headerEditText = headerView.findViewById(R.id.headerEditText);

        headerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Button tıklandığında yapılacak işlemler
            }
        });

        headerEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // EditText tıklandığında yapılacak işlemler
            }
        });
    }

    @Override
    public void cart() {
        cartDrawerLayoutH = findViewById(R.id.cartDrawerLayout);

        cartDrawerLayout = findViewById(R.id.cartDrawerLayout);
        actionBarDrawerToggle3 = new ActionBarDrawerToggle(this, searchDrawerLayout, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        searchDrawerLayout.addDrawerListener(actionBarDrawerToggle3);
        actionBarDrawerToggle3.syncState();

        cartNavigationView = findViewById(R.id.cart_navigationview);

        headerCartBackButton1 = findViewById(R.id.headerCartBack1);
        headerCartBackButton2 = findViewById(R.id.headerCartBack2);

    }

    @Override
    public void onInheritCreate() {
        menu();
        search();
        cart();
    }

    public void cartBackButton(View view) {
        cartDrawerLayout.bringToFront();
        cartDrawerLayout.closeDrawer(GravityCompat.END);
        isXShapeForCart =  !isXShapeForCart;
    }
    public void menuButton(View view) {
        //menu kapandığında da çalışsın
        x.setVisibility(View.VISIBLE);

        AnimatorSet animatorSet = new AnimatorSet();

        ObjectAnimator anim1, anim2, anim3, anim4, anim5, anim6;

        if (isXShape) {

            drawerLayout.closeDrawer(GravityCompat.START); // NavigationView'ı aç
            cartDrawerLayout.bringToFront();
            menuDrawerLayout.bringToFront();
            searchDrawerLayoutH.bringToFront();
            menuviewScrollViewH.bringToFront();


            anim1 = ObjectAnimator.ofFloat(line1, "rotation", 45f, 0f);
            anim2 = ObjectAnimator.ofFloat(line2, "alpha", 0f, 1f);
            anim3 = ObjectAnimator.ofFloat(line3, "rotation", -45f, 0f);
            anim4 = ObjectAnimator.ofFloat(line1, "alpha", 0f, 1f);
            anim5 = ObjectAnimator.ofFloat(line3, "alpha", 0f, 1f);
            anim6 = ObjectAnimator.ofFloat(x, "alpha", 1f, 0f);
        }
        else {
            searchDrawerLayoutH.bringToFront();
            cartDrawerLayout.bringToFront();
            menuviewScrollViewH.bringToFront();
            menuDrawerLayout.bringToFront();



            drawerLayout.openDrawer(GravityCompat.START); // NavigationView'ı aç

            anim1 = ObjectAnimator.ofFloat(line1, "rotation", 0f, 45f);
            anim2 = ObjectAnimator.ofFloat(line2, "alpha", 1f, 0f);
            anim3 = ObjectAnimator.ofFloat(line3, "rotation", 0f, -45f);
            anim4 = ObjectAnimator.ofFloat(line1, "alpha", 1f, 0f);
            anim5 = ObjectAnimator.ofFloat(line3, "alpha", 1f, 0f);
            anim6 = ObjectAnimator.ofFloat(x, "alpha", 0f, 1f);
        }

        anim1.setDuration(200);
        anim2.setDuration(200);
        anim3.setDuration(200);
        anim4.setDuration(200);
        anim5.setDuration(200);
        anim6.setDuration(300);

        line1.setPivotX(0);
        line2.setPivotX(0);
        line3.setPivotX(0);

        animatorSet.playTogether(anim1, anim2, anim3, anim4, anim5, anim6);

        animatorSet.start();
        isXShape = !isXShape;




    }

    @Override
    public void search(View view) {
        AnimatorSet animatorSet = new AnimatorSet();
        ObjectAnimator anim1;

        if (isXShapeForSearch){

            searchDrawerLayoutH.bringToFront();
            cartDrawerLayout.bringToFront();
            menuDrawerLayout.bringToFront();
            menuviewScrollViewH.bringToFront();

            searchDrawerLayout.closeDrawer(GravityCompat.END);
            anim1 = ObjectAnimator.ofFloat(search, "alpha", 0f, 1f);
        } else {
            menuDrawerLayout.bringToFront();
            cartDrawerLayout.bringToFront();
            menuviewScrollViewH.bringToFront();
            searchDrawerLayoutH.bringToFront();

            searchDrawerLayout.openDrawer(GravityCompat.END);
            anim1 = ObjectAnimator.ofFloat(search, "alpha", 1f, 0f);
        }
        anim1.start();
        isXShapeForSearch = !isXShapeForSearch;


    }

    @Override
    public void avatar(View view) {
        // Intent intent = new Intent(this, LoginActivity.class);
        //startActivity(intent);
    }

    @Override
    public void cart(View view) {

        if (isXShapeForCart){
            cartDrawerLayout.bringToFront();
            menuviewScrollViewH.bringToFront();
            cartDrawerLayout.closeDrawer(GravityCompat.END);
        }else {
            cartDrawerLayout.bringToFront();
            cartDrawerLayout.openDrawer(GravityCompat.END);
        }
        isXShapeForCart =  !isXShapeForCart;
    }
    public void logout(View view){
        // Çıkış işlemi
        FirebaseAuth.getInstance().signOut();

        // Favori düğmesini gizle
        favoriView.setVisibility(View.GONE);
    }
}