package com.muham.bamostmobileappv4.Account;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.muham.bamostmobileappv4.Account.Adapter.AddressAdapter;
import com.muham.bamostmobileappv4.Account.Addresses.Address;
import com.muham.bamostmobileappv4.MainActivity;
import com.muham.bamostmobileappv4.R;
import com.muham.bamostmobileappv4.tasarimInterface;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AccountAddressesActivity extends AppCompatActivity implements tasarimInterface, LoginListener {
    TextView headerMenuInfoTextView;
    TextView headerBackTextView;
    private NavigationView menuNavigationView;

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

    View newAddressLayout;

    View newAddressButtonLayout;
    TextView helloNameSurnameTextView;
    TextView mailTextView;

    //adres
    EditText editAddressTitleText;
    EditText editNameText;
    EditText editSurnameText;
    EditText editAddressText;
    EditText editApartmentText;
    EditText editCityText;
    EditText editSemtText;
    EditText editNumberText;
    private TextView deleteHeader;
    private TextView changeHeader;

    private List<Address> addressesList;
    private AddressAdapter adapter;
    private RecyclerView addressesRecyclerView;
    private Layout address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_addresses);

        favoriView = findViewById(R.id.imageViewFav);

        newAddressLayout = findViewById(R.id.changeAddressLayout);
        newAddressLayout.setVisibility(View.GONE);

        newAddressButtonLayout = findViewById(R.id.newAddressButtonLayout);
        controlAddresses();

        onInheritCreate();
    }

    public void SaveButton(View view) {
        //Adres
        editAddressTitleText = findViewById(R.id.editAddressTitleText);
        editNameText = findViewById(R.id.editNameText);
        editSurnameText = findViewById(R.id.editSurnameText);
        editAddressText = findViewById(R.id.editAddressText);
        editApartmentText = findViewById(R.id.editApartmentText);
        editCityText = findViewById(R.id.editCityText);
        editSemtText = findViewById(R.id.editSemtText);
        editNumberText = findViewById(R.id.editNumberText);
        //
        String addressTitle = editAddressTitleText.getText().toString();
        String name = editNameText.getText().toString();
        String surname = editSurnameText.getText().toString();
        String address = editAddressText.getText().toString();
        String apartment = editApartmentText.getText().toString();
        String city = editCityText.getText().toString();
        String semt = editSemtText.getText().toString();
        String number = editNumberText.getText().toString();

        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        String userId = currentUser.getUid();

        if (currentUser != null) {
            CollectionReference adresKoleksiyonu = db.collection("Persons").document(userId).collection("adresler");
            Map<String, Object> adresInfo = new HashMap<>();

            adresInfo.put("addressTitle", addressTitle);
            adresInfo.put("name", name);
            adresInfo.put("surname", surname);
            adresInfo.put("address", address);
            adresInfo.put("apartment", apartment);
            adresInfo.put("city", city);
            adresInfo.put("semt", semt);
            adresInfo.put("number", number);

            adresKoleksiyonu.add(adresInfo)
                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            String addressId = documentReference.getId();
                            String nameSurnameTextView = name + " " + surname;
                            String titleTextView = addressTitle;
                            String addressAdapterTextView = address + " " + apartment + " " + semt + " " + city;
                            String cityCountryTextView = city;

                            CollectionReference adresKoleksiyonu = db.collection("Persons").document(userId).collection("adresler");
                            Map<String, Object> adresInfo = new HashMap<>();
                            DocumentReference belgeReferansi = adresKoleksiyonu.document(addressId);

                            adresInfo.put("addressId", addressId);

                            belgeReferansi.update(adresInfo);

                            if (addressesList != null) {
                                addressesList.add(new Address(addressId, nameSurnameTextView, titleTextView, addressAdapterTextView, cityCountryTextView));
                                adapter.notifyDataSetChanged(); // RecyclerView'i güncelleyin
                                System.out.println("21"+addressesList.get(0).getAddressId());
                            }

                            // Adres başarıyla Firestore'a eklendi, diğer işlemleri burada yapabilirsiniz.
                            Toast.makeText(AccountAddressesActivity.this, "Kayıt başarılı", Toast.LENGTH_SHORT).show();
                            newAddressLayout.setVisibility(View.GONE);
                            newAddressButtonLayout.setVisibility(View.VISIBLE);
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            // Kayıt başarısız olduğunda burada işlemleri yapabilirsiniz.
                            Toast.makeText(AccountAddressesActivity.this, "Kayıt başarısız", Toast.LENGTH_SHORT).show();
                        }
                    });
        }
    }

    public void controlAddresses() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();

        addressesRecyclerView = findViewById(R.id.addressesRecyclerView);
        addressesRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        addressesRecyclerView.setVisibility(View.GONE);

        if (currentUser != null) {
            String userId = currentUser.getUid();

            CollectionReference adresKoleksiyonu = db.collection("Persons").document(userId).collection("adresler");

            adresKoleksiyonu.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if (task.isSuccessful()) {
                        boolean adresBulundu = false;
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            // Her bir adres belgesini kontrol edin
                            // Eğer aradığınız adres bilgisini burada bulursanız adresBulundu değişkenini true yapabilirsiniz
                            // Örneğin, belirli bir adres adını kontrol edebilirsiniz
                            addressesRecyclerView.setVisibility(View.VISIBLE);

                            String addressId = document.getString("addressId");//Hatayı düzelt
                            String nameSurnameTextView = document.getString("name") + " " + document.getString("surname");
                            String titleTextView = document.getString("addressTitle");
                            String addressAdapterTextView = document.getString("address") + " " + document.getString("apartment") + " " + document.getString("semt") + " " + document.getString("city");
                            String cityCountryTextView = document.getString("city");

                            if (addressesList != null) {
                                addressesList.add(new Address(addressId, nameSurnameTextView, titleTextView, addressAdapterTextView, cityCountryTextView));
                            } else {
                                // Eğer addressesList null ise, uygun bir şekilde başlatılması gerekir.
                                addressesList = new ArrayList<Address>();
                                addressesList.add(new Address(addressId, nameSurnameTextView, titleTextView, addressAdapterTextView, cityCountryTextView));
                            }

                            adapter = new AddressAdapter(AccountAddressesActivity.this, addressesList);
                            addressesRecyclerView.setAdapter(adapter);
                        }

                        if (!adresBulundu) {
                            Toast.makeText(AccountAddressesActivity.this, "Adres bulunamadı yeni adres ekleyin.", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(AccountAddressesActivity.this, "Sorgu başarısız", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    public void dbConnection(){
        helloNameSurnameTextView = findViewById(R.id.helloNameSurnameTextView);
        mailTextView = findViewById(R.id.mailtextView);

        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        String userId = mAuth.getCurrentUser().getUid();

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        DocumentReference userRef = db.collection("Persons").document(userId);

        userRef.get().addOnCompleteListener(userTask -> {
            if (userTask.isSuccessful()) {
                DocumentSnapshot document = userTask.getResult();
                if (document.exists()) {
                    // Firestore'dan verileri alın
                    String firstName = document.getString("firstName");
                    String lastName = document.getString("lastName");
                    String mail = document.getString("email");

                    helloNameSurnameTextView.setText("MERHABA " + firstName + " " + lastName);
                    mailTextView.setText(mail);

                }
            }
        });

    }
    public void newAddressButton(View view){
        newAddressButtonLayout.setVisibility(View.GONE);
        newAddressLayout.setVisibility(View.VISIBLE);
    }
    public void CancelButton(View view){
        newAddressButtonLayout.setVisibility(View.VISIBLE);
        newAddressLayout.setVisibility(View.GONE);
    }
    public void buttonOrders(View view){
        Intent intent = new Intent(this,AccountOrderActivity.class);
        startActivity(intent);
    }
    public void buttonAccountDetails(View view){
        Intent intent = new Intent(this,AccountDetailsActivity.class);
        startActivity(intent);
    }
    public void buttonSignout(View view){
        // Ana iş parçacığını dondurmamak için çıkış işlemini arka plan iş parçacığında yapın
        new Thread(new Runnable() {
            @Override
            public void run() {
                FirebaseAuth.getInstance().signOut();

                // Ana iş parçacığına geri dön ve UI güncellemelerini yap
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        // Favori düğmesini gizle veya diğer UI güncellemelerini yap
                        favoriView.setVisibility(View.GONE);
                    }
                });
            }
        }).start();
        mainHubButton();
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
    public void menu(){
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
        dbConnection();
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
    private void mainHubButton(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    public void mainHubButton(View view){
        mainHubButton();
    }

}