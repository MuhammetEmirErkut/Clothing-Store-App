package com.muham.bamostmobileappv4.Account.Addresses;

import com.google.firebase.firestore.Exclude;

public class Address {
    @Exclude // Firestore tarafından dikkate alınmayacak alanlar için bu işareti kullanabilirsiniz.
    private String addressId;
    private String nameSurnameTextView;
    private String titleTextView;
    private String addressAdapterTextView;
    private String cityCountryTextView;

    private String addressTitle;
    private String name;
    private String surname;
    private String fullAddress;
    private String apartment;
    private String city;
    private String semt;
    private String number;

    // Boş kurucu metot Firestore için gereklidir.
    public Address() {}

    public Address(String addressId, String nameSurnameTextView, String titleTextView, String addressAdapterTextView, String cityCountryTextView) {
        this.addressId = addressId;
        this.nameSurnameTextView = nameSurnameTextView;
        this.titleTextView = titleTextView;
        this.addressAdapterTextView = addressAdapterTextView;
        this.cityCountryTextView = cityCountryTextView;
    }

    // Getter metotları
    public String getAddressId() {
        return addressId;
    }

    public String getNameSurname() {
        return nameSurnameTextView;
    }

    public String getTitle() {
        return titleTextView;
    }

    public String getAddressAdapter() {
        return addressAdapterTextView;
    }

    public String getCityCountry() {
        return cityCountryTextView;
    }
}
