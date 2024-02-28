package com.muham.bamostmobileappv4.Account.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.muham.bamostmobileappv4.R;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AddressAdapter extends RecyclerView.Adapter<AddressAdapter.AddressViewHolder> {

    private Context context;
    private List<com.muham.bamostmobileappv4.Account.Addresses.Address> addressesList;




    public AddressAdapter(Context context, List<com.muham.bamostmobileappv4.Account.Addresses.Address> addressesList) {
        this.context = context;
        this.addressesList = addressesList;

    }

    @NonNull
    @Override
    public AddressViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.address, parent, false);
        return new AddressViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AddressViewHolder holder, int position) {
        holder.changeAddressLayout.setVisibility(View.GONE);
        com.muham.bamostmobileappv4.Account.Addresses.Address address = addressesList.get(position);
        holder.nameSurnameTextView.setText(address.getNameSurname()); // "getNameSurnameTextView" yerine "getNameSurname" kullanılmalı
        holder.titleTextView.setText(address.getTitle()); // "getTitleTextView" yerine "getTitle" kullanılmalı
        holder.addressAdapterTextView.setText(address.getAddressAdapter()); // "getAddressAdapterTextView" yerine "getAddressAdapter" kullanılmalı
        holder.cityCountryTextView.setText(address.getCityCountry()); // "getCityCountryTextView" yerine "getCityCountry" kullanılmalı

        holder.deleteTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int adapterPosition = holder.getAdapterPosition();
                com.muham.bamostmobileappv4.Account.Addresses.Address deletedAddress = addressesList.remove(adapterPosition);
                notifyItemRemoved(adapterPosition);

                // Firebase Firestore'dan da öğeyi sil
                deleteAddressFromFirestore(deletedAddress.getAddressId());
            }
        });

        holder.changeTextView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                holder.changeAddressLayout.setVisibility(View.VISIBLE);
                int adapterPosition = holder.getAdapterPosition();
                com.muham.bamostmobileappv4.Account.Addresses.Address changeAddress = addressesList.get(adapterPosition);

                FirebaseAuth mAuth = FirebaseAuth.getInstance();
                FirebaseUser currentUser = mAuth.getCurrentUser();
                FirebaseFirestore db = FirebaseFirestore.getInstance();

                String userId = currentUser.getUid();

                CollectionReference adresKoleksiyonu = db.collection("Persons").document(userId).collection("adresler");

                DocumentReference belgeReferansi = adresKoleksiyonu.document(changeAddress.getAddressId());

                belgeReferansi.get().addOnCompleteListener(userTask -> {

                    if (userTask.isSuccessful()){
                        DocumentSnapshot document = userTask.getResult();
                        if (document.exists()){
                            String addressTitle = document.getString("addressTitle");
                            String name = document.getString("name");
                            String surname = document.getString("surname");
                            String address = document.getString("address");
                            String apartment = document.getString("apartment");
                            String city = document.getString("city");
                            String semt = document.getString("semt");
                            String number = document.getString("number");

                            holder.editUpdateAddressTitleText.setText(addressTitle);
                            holder.editUpdateNameText.setText(name);
                            holder.editUpdateSurnameText.setText(surname);
                            holder.editUpdateAddressText.setText(address);
                            holder.editUpdateApartmentText.setText(apartment);
                            holder.editUpdateCityText.setText(city);
                            holder.editUpdateSemtText.setText(semt);
                            holder.editUpdateNumberText.setText(number);

                            ///

                        }
                    }
                });

            }
        });
        holder.saveUpdateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.changeAddressLayout.setVisibility(View.VISIBLE);
                int adapterPosition = holder.getAdapterPosition();
                com.muham.bamostmobileappv4.Account.Addresses.Address changeAddress = addressesList.get(adapterPosition);

                FirebaseAuth mAuth = FirebaseAuth.getInstance();
                FirebaseUser currentUser = mAuth.getCurrentUser();
                FirebaseFirestore db = FirebaseFirestore.getInstance();

                String userId = currentUser.getUid();

                CollectionReference adresKoleksiyonu = db.collection("Persons").document(userId).collection("adresler");

                DocumentReference belgeReferansi = adresKoleksiyonu.document(changeAddress.getAddressId());

                String addressTitleUpdate = holder.editUpdateAddressTitleText.getText().toString();
                String nameUpdate =  holder.editUpdateNameText.getText().toString();
                String surnameUpdate =  holder.editUpdateSurnameText.getText().toString();
                String addressUpdate =  holder.editUpdateAddressText.getText().toString();
                String apartmentUpdate =  holder.editUpdateApartmentText.getText().toString();
                String cityUpdate =  holder.editUpdateCityText.getText().toString();
                String semtUpdate =  holder.editUpdateSemtText.getText().toString();
                String numberUpdate =  holder.editUpdateNumberText.getText().toString();

                Map<String, Object> adresInfo = new HashMap<>();

                adresInfo.put("addressTitle", addressTitleUpdate);
                adresInfo.put("name", nameUpdate);
                adresInfo.put("surname", surnameUpdate);
                adresInfo.put("address", addressUpdate);
                adresInfo.put("apartment", apartmentUpdate);
                adresInfo.put("city", cityUpdate);
                adresInfo.put("semt", semtUpdate);
                adresInfo.put("number", numberUpdate);

                belgeReferansi.update(adresInfo);
                holder.changeAddressLayout.setVisibility(View.GONE);
            }
        });
        holder.cancelUpdateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.changeAddressLayout.setVisibility(View.GONE);
            }
        });

    }

    @Override
    public int getItemCount() {
        return addressesList.size();
    }

    private void deleteAddressFromFirestore(String addressIdToDelete) {
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        if (currentUser != null) {
            String userId = currentUser.getUid();
            CollectionReference adresKoleksiyonu = db.collection("Persons").document(userId).collection("adresler");

            if (addressIdToDelete != null) {
                adresKoleksiyonu.document(addressIdToDelete)
                        .delete()
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                // Firestore'dan başarıyla kaldırıldı
                                // Başka işlemler yapabilirsiniz, eğer gerekiyorsa
                                // Kaldırma işlemi başarılı olduğunda buraya gelecektir
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                // Silme işlemi başarısız oldu
                                // Hata mesajını işleyin veya kullanıcıya bildirin
                                e.printStackTrace(); // Hata detaylarını logcat'e yazdırın
                            }
                        });
            }
        }
    }

    public class AddressViewHolder extends RecyclerView.ViewHolder {
        TextView deleteTextView;
        TextView changeTextView;
        TextView nameSurnameTextView;
        TextView titleTextView;
        TextView addressAdapterTextView;
        TextView cityCountryTextView;

        //
        View changeAddressLayout;

        EditText editUpdateAddressTitleText;
        EditText editUpdateNameText;
        EditText editUpdateSurnameText;
        EditText editUpdateAddressText;
        EditText editUpdateApartmentText;
        EditText editUpdateCityText;
        EditText editUpdateSemtText;
        EditText editUpdateNumberText;

        Button saveUpdateButton;
        Button cancelUpdateButton;

        public AddressViewHolder(@NonNull View itemView) {
            super(itemView);
            changeAddressLayout = itemView.findViewById(R.id.changeAddressLayout);
            editUpdateAddressTitleText = itemView.findViewById(R.id.editUpdateAddressTitleText);
            editUpdateNameText = itemView.findViewById(R.id.editUpdateNameText);
            editUpdateSurnameText = itemView.findViewById(R.id.editUpdateSurnameText);
            editUpdateAddressText = itemView.findViewById(R.id.editUpdateAddressText);
            editUpdateApartmentText = itemView.findViewById(R.id.editUpdateApartmentText);
            editUpdateCityText = itemView.findViewById(R.id.editUpdateCityText);
            editUpdateSemtText = itemView.findViewById(R.id.editUpdateSemtText);
            editUpdateNumberText = itemView.findViewById(R.id.editUpdateNumberText);
            saveUpdateButton = itemView.findViewById(R.id.saveUpdateButton);
            cancelUpdateButton = itemView.findViewById(R.id.cancelUpdateButton);

            //
            changeTextView = itemView.findViewById(R.id.changeTextView);
            deleteTextView = itemView.findViewById(R.id.deleteTextView);
            nameSurnameTextView = itemView.findViewById(R.id.nameSurnameTextView);
            titleTextView = itemView.findViewById(R.id.titleTextView);
            addressAdapterTextView = itemView.findViewById(R.id.addressAdapterTextView);
            cityCountryTextView = itemView.findViewById(R.id.cityCountryTextView);
        }
    }
}


