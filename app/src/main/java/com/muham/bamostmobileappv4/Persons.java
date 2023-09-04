package com.muham.bamostmobileappv4;

public class Persons {
    private String uid;
    private String firstName;
    private String lastName;
    private String password;
    private String email;

    public Persons(String uid, String firstName, String lastName, String password, String email){
        this.uid = uid;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.email = email;
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
}