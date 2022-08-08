package com.example.myapplication;

public class User {

    public String userEmail;
    public String userNom;
    public String userPrenom;
    public String userNumero;
    public String userPass;
    public String userConfPass;

    public User(){

    }

    public User(String userEmaill,String userNomm, String userPrenomm,String userNumeroo,String userPasss,String userConfPasss){

        this.userEmail=userEmaill;
        this.userNom=userNomm;
        this.userPrenom=userPrenomm;
        this.userNumero=userNumeroo;
        this.userPass=userPasss;
        this.userConfPass=userConfPasss;
    }

}
