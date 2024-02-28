package com.muham.bamostmobileappv4.Account;

public class Persons {
    private String uid;
    private String firstName;
    private String lastName;
    private String password;
    private String email;
    private String number;
    private String address;

    public Persons(String uid, String firstName, String lastName, String password, String email, String number, String address){
        this.uid = uid;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.email = email;
        this.number = number;
        this.address = address;
    }

    public String getUid() {
        return uid;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }
    public String getNumber(){return number;}
    public String getAddress(){return address;}
}