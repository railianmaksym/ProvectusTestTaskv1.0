package com.railian.maksym.provectustesttask;

import com.raizlabs.android.dbflow.annotation.*;
import com.raizlabs.android.dbflow.structure.BaseModel;

/**
 * Created by fluffy on 28.06.17.
 */
@Table(database = Database.class)

public class UsersItem extends BaseModel {
   // private String id,title,first,last,street,city,state,postcode,email,dob,registered,phone,cell,pictureUrlSmall,pictureUrlFull;


    @Column
    @PrimaryKey
    int id;

    @Column
    String name;

    @Column
    String url;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


    /*public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getPictureUrlSmall() {
        return pictureUrlSmall;
    }

    public void setPictureUrlSmall(String pictureUrlSmall) {
        this.pictureUrlSmall = pictureUrlSmall;
    }

    public String getPictureUrlFull() {
        return pictureUrlFull;
    }

    public void setPictureUrlFull(String pictureUrlFull) {
        this.pictureUrlFull = pictureUrlFull;
    }*/
}
