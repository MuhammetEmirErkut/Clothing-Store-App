package com.muham.bamostmobileappv4.menuInfo;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.muham.bamostmobileappv4.Account.LoginActivity;
import com.muham.bamostmobileappv4.Account.LoginListener;
import com.muham.bamostmobileappv4.Adapter.Dresses;
import com.muham.bamostmobileappv4.Adapter.DressesAdapter;
import com.muham.bamostmobileappv4.MainActivity;
import com.muham.bamostmobileappv4.R;
import com.muham.bamostmobileappv4.tasarimInterface;

import java.util.List;

public class ReturnExchange extends AppCompatActivity implements tasarimInterface , LoginListener {
    //Menu
    private View line1, line2, line3;
    private View x;
    TextView menuSellTextView;
    TextView menuNewTextView;
    TextView menuCollectionTextView;
    TextView menuDressTextView;
    //Info
    TextView headerMenuInfoTextView;
    TextView headerBackTextView;
    private NavigationView menuNavigationView;
    TextView headerOrderTrackingTextView;
    TextView headerWholeSaleTextView;
    TextView headerComminicateTextView;
    TextView headerCargoTextView;
    TextView headerOrderBuyTextView;
    TextView headerReturnExchangeTextView;

    LinearLayout menuLinearLayout;
    LinearLayout ınfoLinearLayout;
    LinearLayout collectionLinearLayout;
    LinearLayout dressesLinearLayout;
    //
    //Koleksiyon
    TextView headerCollectionMoreTextView;
    TextView headerCollectionBackTextView;

    // Giyim
    TextView headerDressMoreTextView;
    TextView headerDressBackTexView;

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

    private TextView sell50;
    private TextView sell51;

    //TOOLBAR

    // private View constraintLayout2;
    //private Handler handler = new Handler();
    //private Animation slideDown, slideUp;

    //kıyafet yalandan
    private RecyclerView recyclerView;
    private DressesAdapter adapter;
    private List<Dresses> dressesList;
    //Fav
    ImageView favoriView;

    //Return Exchange

    private boolean isPlus1 = false;
    private boolean isPlus2 = false;
    private boolean isPlus3 = false;
    private boolean isPlus4 = false;
    private boolean isPlus5 = false;
    private boolean isPlus6 = false;
    private boolean isPlus7 = false;
    private boolean isPlus8 = false;
    private boolean isPlus9 = false;


    TextView PlusTextView1;
    LinearLayout ReturnExchangeProvisoLinearLayout;

    TextView PlusTextView2;
    LinearLayout ReturnLinearLayout;

    TextView PlusTextView3;
    LinearLayout ExchangeLinearLayout;

    TextView PlusTextView4;
    LinearLayout ReturnExchangeCargoPaymentLinearLayout;

    TextView PlusTextView5;
    LinearLayout ReturnExchangeTimeLinearLayout;
    TextView PlusTextView6;
    LinearLayout ReturnConditionLinearLayout;
    TextView PlusTextView7;
    LinearLayout ReturnExchangeCargoLinearLayout;
    TextView PlusTextView8;
    LinearLayout ReturnCargoAddressLinearLayout;
    TextView PlusTextView9;
    LinearLayout ReturnExchangeHowCargoLinearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_return_exchange);


        favoriView = findViewById(R.id.imageViewFav);
        favoriView.setVisibility(View.GONE);
        onLogin();
        onInheritCreate();
        returnExchangeQuestions();
    }
    public void returnExchangeQuestions(){
        PlusTextView1 = findViewById(R.id.PlusTextView1);
        ReturnExchangeProvisoLinearLayout = findViewById(R.id.ReturnExchangeProvisoLinearLayout);
        PlusTextView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isPlus1 == false) {
                    LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) ReturnExchangeProvisoLinearLayout.getLayoutParams();
                    layoutParams.height = 510;
                    ReturnExchangeProvisoLinearLayout.setLayoutParams(layoutParams);
                    PlusTextView1.setTextSize(50);
                    PlusTextView1.setText("-");
                    isPlus1 = true;
                }
                else {
                    LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) ReturnExchangeProvisoLinearLayout.getLayoutParams();
                    layoutParams.height = 140;
                    ReturnExchangeProvisoLinearLayout.setLayoutParams(layoutParams);
                    PlusTextView1.setTextSize(31);
                    PlusTextView1.setText("+");
                    isPlus1 = false;
                }
            }
        });

        ReturnLinearLayout = findViewById(R.id.ReturnLinearLayout);
        PlusTextView2 = findViewById(R.id.PlusTextView2);

        PlusTextView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isPlus2 == false) {
                    LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) ReturnLinearLayout.getLayoutParams();
                    layoutParams.height = 730;
                    ReturnLinearLayout.setLayoutParams(layoutParams);
                    PlusTextView2.setTextSize(50);
                    PlusTextView2.setText("-");
                    isPlus2 = true;
                }
                else {
                    LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) ReturnLinearLayout.getLayoutParams();
                    layoutParams.height = 140;
                    ReturnLinearLayout.setLayoutParams(layoutParams);
                    PlusTextView2.setTextSize(31);
                    PlusTextView2.setText("+");
                    isPlus2 = false;
                }
            }
        });

        ExchangeLinearLayout = findViewById(R.id.ExchangeLinearLayout);
        PlusTextView3 = findViewById(R.id.PlusTextView3);

        PlusTextView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isPlus3 == false) {
                    LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) ExchangeLinearLayout.getLayoutParams();
                    layoutParams.height = 685;
                    ExchangeLinearLayout.setLayoutParams(layoutParams);
                    PlusTextView3.setTextSize(50);
                    PlusTextView3.setText("-");
                    isPlus3 = true;
                }
                else {
                    LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) ExchangeLinearLayout.getLayoutParams();
                    layoutParams.height = 140;
                    ExchangeLinearLayout.setLayoutParams(layoutParams);
                    PlusTextView3.setTextSize(31);
                    PlusTextView3.setText("+");
                    isPlus3 = false;
                }
            }
        });

        ReturnExchangeCargoPaymentLinearLayout = findViewById(R.id.ReturnExchangeCargoPaymentLinearLayout);
        PlusTextView4 = findViewById(R.id.PlusTextView4);

        PlusTextView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isPlus4 == false) {
                    LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) ReturnExchangeCargoPaymentLinearLayout.getLayoutParams();
                    layoutParams.height = 350;
                    ReturnExchangeCargoPaymentLinearLayout.setLayoutParams(layoutParams);
                    PlusTextView4.setTextSize(50);
                    PlusTextView4.setText("-");
                    isPlus4 = true;
                }
                else {
                    LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) ReturnExchangeCargoPaymentLinearLayout.getLayoutParams();
                    layoutParams.height = 140;
                    ReturnExchangeCargoPaymentLinearLayout.setLayoutParams(layoutParams);
                    PlusTextView4.setTextSize(31);
                    PlusTextView4.setText("+");
                    isPlus4 = false;
                }
            }
        });

        ReturnExchangeTimeLinearLayout = findViewById(R.id.ReturnExchangeTimeLinearLayout);
        PlusTextView5 = findViewById(R.id.PlusTextView5);

        PlusTextView5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isPlus5 == false) {
                    LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) ReturnExchangeTimeLinearLayout.getLayoutParams();
                    layoutParams.height = 470;
                    ReturnExchangeTimeLinearLayout.setLayoutParams(layoutParams);
                    PlusTextView5.setTextSize(50);
                    PlusTextView5.setText("-");
                    isPlus5 = true;
                }
                else {
                    LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) ReturnExchangeTimeLinearLayout.getLayoutParams();
                    layoutParams.height = 140;
                    ReturnExchangeTimeLinearLayout.setLayoutParams(layoutParams);
                    PlusTextView5.setTextSize(31);
                    PlusTextView5.setText("+");
                    isPlus5 = false;
                }
            }
        });

        ReturnConditionLinearLayout = findViewById(R.id.ReturnConditionLinearLayout);
        PlusTextView6 = findViewById(R.id.PlusTextView6);

        PlusTextView6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isPlus6 == false) {
                    LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) ReturnConditionLinearLayout.getLayoutParams();
                    layoutParams.height = 520;
                    ReturnConditionLinearLayout.setLayoutParams(layoutParams);
                    PlusTextView6.setTextSize(50);
                    PlusTextView6.setText("-");
                    isPlus6 = true;
                }
                else {
                    LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) ReturnConditionLinearLayout.getLayoutParams();
                    layoutParams.height = 140;
                    ReturnConditionLinearLayout.setLayoutParams(layoutParams);
                    PlusTextView6.setTextSize(31);
                    PlusTextView6.setText("+");
                    isPlus6 = false;
                }
            }
        });

        ReturnExchangeCargoLinearLayout = findViewById(R.id.ReturnExchangeCargoLinearLayout);
        PlusTextView7 = findViewById(R.id.PlusTextView7);

        PlusTextView7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isPlus7 == false) {
                    LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) ReturnExchangeCargoLinearLayout.getLayoutParams();
                    layoutParams.height = 470;
                    ReturnExchangeCargoLinearLayout.setLayoutParams(layoutParams);
                    PlusTextView7.setTextSize(50);
                    PlusTextView7.setText("-");
                    isPlus7 = true;
                }
                else {
                    LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) ReturnExchangeCargoLinearLayout.getLayoutParams();
                    layoutParams.height = 140;
                    ReturnExchangeCargoLinearLayout.setLayoutParams(layoutParams);
                    PlusTextView7.setTextSize(31);
                    PlusTextView7.setText("+");
                    isPlus7 = false;
                }
            }
        });

        ReturnCargoAddressLinearLayout = findViewById(R.id.ReturnCargoAddressLinearLayout);
        PlusTextView8 = findViewById(R.id.PlusTextView8);

        PlusTextView8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isPlus8 == false) {
                    LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) ReturnCargoAddressLinearLayout.getLayoutParams();
                    layoutParams.height = 410;
                    ReturnCargoAddressLinearLayout.setLayoutParams(layoutParams);
                    PlusTextView8.setTextSize(50);
                    PlusTextView8.setText("-");
                    isPlus8 = true;
                }
                else {
                    LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) ReturnCargoAddressLinearLayout.getLayoutParams();
                    layoutParams.height = 140;
                    ReturnCargoAddressLinearLayout.setLayoutParams(layoutParams);
                    PlusTextView8.setTextSize(31);
                    PlusTextView8.setText("+");
                    isPlus8 = false;
                }
            }
        });

        ReturnExchangeHowCargoLinearLayout = findViewById(R.id.ReturnExchangeHowCargoLinearLayout);
        PlusTextView9 = findViewById(R.id.PlusTextView9);

        PlusTextView9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isPlus9 == false) {
                    LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) ReturnExchangeHowCargoLinearLayout.getLayoutParams();
                    layoutParams.height = 410;
                    ReturnExchangeHowCargoLinearLayout.setLayoutParams(layoutParams);
                    PlusTextView9.setTextSize(50);
                    PlusTextView9.setText("-");
                    isPlus9 = true;
                }
                else {
                    LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) ReturnExchangeHowCargoLinearLayout.getLayoutParams();
                    layoutParams.height = 140;
                    ReturnExchangeHowCargoLinearLayout.setLayoutParams(layoutParams);
                    PlusTextView9.setTextSize(31);
                    PlusTextView9.setText("+");
                    isPlus9 = false;
                }
            }
        });
    }
    @Override
    public void menu(){
        sell50 = findViewById(R.id.sell50);
        sell51 = findViewById(R.id.sell51);

        line1 = findViewById(R.id.line1);
        line2 = findViewById(R.id.line2);
        line3 = findViewById(R.id.line3);
        x = findViewById(R.id.view1);
        x.setVisibility(View.INVISIBLE);

        searchX = findViewById(R.id.imageViewX);
        search = findViewById(R.id.imageViewSearch);

        menuviewScrollViewH = findViewById(R.id.mainviewLoginScrollView);

        menuDrawerLayout = findViewById(R.id.menuDrawerLayout);//öne atmak için hiyerari

        drawerLayout = findViewById(R.id.menuDrawerLayout);
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

        menuNavigationView = findViewById(R.id.tasarim_navigationview);

        View menuHeaderView = menuNavigationView.getHeaderView(0);

        headerMenuInfoTextView = menuHeaderView.findViewById(R.id.menuInfoTextView);

        menuLinearLayout = menuHeaderView.findViewById(R.id.menuLinearLayout);
        //Giyim
        dressesLinearLayout = menuHeaderView.findViewById(R.id.dressesLinearLayout);
        headerDressMoreTextView = menuHeaderView.findViewById(R.id.dressMoreTextView);
        headerDressBackTexView = menuHeaderView.findViewById(R.id.backDressesTextView);

        //Collection
        collectionLinearLayout = menuHeaderView.findViewById(R.id.collectionLinearLayout);
        headerCollectionMoreTextView = menuHeaderView.findViewById(R.id.collectionMoreTextView);
        headerCollectionBackTextView = menuHeaderView.findViewById(R.id.backCollectionTextView);

        //INFO
        ınfoLinearLayout = menuHeaderView.findViewById(R.id.ınfoLinearLayout);
        headerBackTextView  = menuHeaderView.findViewById(R.id.backTextView);
        headerOrderTrackingTextView = menuHeaderView.findViewById(R.id.ınfoOrderTrackingTextView);
        headerWholeSaleTextView = menuHeaderView.findViewById(R.id.ınfoWholeSaleTexView);
        headerComminicateTextView = menuHeaderView.findViewById(R.id.ınfoComminicateTextView);
        headerCargoTextView = menuHeaderView.findViewById(R.id.ınfoCargoTextView);
        headerOrderBuyTextView = menuHeaderView.findViewById(R.id.ınfoOrderBuyTextView);
        headerReturnExchangeTextView = menuHeaderView.findViewById(R.id.ınfoReturnExchangeTextView);
        ınfoLinearLayout.setVisibility(View.GONE);
        collectionLinearLayout.setVisibility(View.GONE);
        dressesLinearLayout.setVisibility(View.GONE);


        //INFO
        headerMenuInfoTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                menuLinearLayout.setVisibility(View.GONE);
                ınfoLinearLayout.setVisibility(View.VISIBLE);
            }
        });
        headerBackTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                menuLinearLayout.setVisibility(View.VISIBLE);
                ınfoLinearLayout.setVisibility(View.GONE);
            }
        });
        headerOrderTrackingTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ReturnExchange.this, OrderTracking.class);
                startActivity(intent);
            }
        });
        headerWholeSaleTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ReturnExchange.this, WholeSale.class);
                startActivity(intent);
            }
        });
        headerComminicateTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ReturnExchange.this, Comminicate.class);
                startActivity(intent);
            }
        });
        headerCargoTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ReturnExchange.this, Cargo.class);
                startActivity(intent);
            }
        });
        headerOrderBuyTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ReturnExchange.this, OrderBuy.class);
                startActivity(intent);
            }
        });
        headerReturnExchangeTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.closeDrawer(GravityCompat.START);
            }
        });

        //Collection
        headerCollectionMoreTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                menuLinearLayout.setVisibility(View.GONE);
                collectionLinearLayout.setVisibility(View.VISIBLE);
            }
        });
        headerCollectionBackTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                menuLinearLayout.setVisibility(View.VISIBLE);
                collectionLinearLayout.setVisibility(View.GONE);
            }
        });

        //Giyim

        headerDressMoreTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                menuLinearLayout.setVisibility(View.GONE);
                dressesLinearLayout.setVisibility(View.VISIBLE);
            }
        });
        headerDressBackTexView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                menuLinearLayout.setVisibility(View.VISIBLE);
                dressesLinearLayout.setVisibility(View.GONE);
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
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
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
    public void mainHubButton(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
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
}