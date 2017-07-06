
package com.railian.maksym.provectustesttask;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Picture {

    // Applying annotations for gson convertor
    @SerializedName("large")
    @Expose
    private String large;
    @SerializedName("medium")
    @Expose
    private String medium;
    @SerializedName("thumbnail")
    @Expose
    private String thumbnail;

    // Constructor for using in mapper
    public Picture(String thumbnail,String medium,String large){
        this.thumbnail=thumbnail;
        this.medium=medium;
        this.large=large;
    }

    //Geters & Setters
    public String getLarge() {
        return large;
    }

    public void setLarge(String large) {
        this.large = large;
    }

    public String getMedium() {
        return medium;
    }

    public void setMedium(String medium) {
        this.medium = medium;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

}
