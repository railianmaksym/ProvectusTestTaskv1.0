
package com.railian.maksym.provectustesttask.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Login {
    @SerializedName("username")
    @Expose
    private String username;

    public Login(String username){
        this.username=username;
    }

    public String getUsername() {
        return username;
    }

}
