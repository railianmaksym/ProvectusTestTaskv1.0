
package com.railian.maksym.provectustesttask.models;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.railian.maksym.provectustesttask.models.Result;


public class UserItem {

    @SerializedName("results")
    @Expose
    private List<Result> results = null;

    public List<Result> getResults() {
        return results;
    }
}
