
package com.railian.maksym.provectustesttask;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Login {

    // Applying annotations for gson convertor
    @SerializedName("username")
    @Expose
    private String username;

    // Constructor for using in mapper
    public Login(String username){
        this.username=username;
    }

    // Getter & Setter
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
