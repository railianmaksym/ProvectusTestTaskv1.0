
package com.railian.maksym.provectustesttask.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.raizlabs.android.dbflow.structure.BaseModel;

public class Result extends BaseModel{

    @SerializedName("gender")
    @Expose
    String gender;


    @SerializedName("name")
    @Expose
    Name name;


    @SerializedName("location")
    @Expose
    Location location;


    @SerializedName("email")
    @Expose
    String email;


    @SerializedName("login")
    @Expose

    Login login;

    @SerializedName("dob")
    @Expose

    String dob;

    @SerializedName("registered")
    @Expose

    String registered;

    @SerializedName("phone")
    @Expose
    String phone;

    @SerializedName("cell")
    @Expose
    String cell;

    @SerializedName("picture")
    @Expose

    Picture picture;

    @SerializedName("nat")
    @Expose
    String nat;

    // Getters & Setters

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Login getLogin() {
        return login;
    }

    public void setLogin(Login login) {
        this.login = login;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getRegistered() {
        return registered;
    }

    public void setRegistered(String registered) {
        this.registered = registered;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCell() {
        return cell;
    }

    public void setCell(String cell) {
        this.cell = cell;
    }

    public Picture getPicture() {
        return picture;
    }

    public void setPicture(Picture picture) {
        this.picture = picture;
    }

    public String getNat() {
        return nat;
    }

    public void setNat(String nat) {
        this.nat = nat;
    }

}
