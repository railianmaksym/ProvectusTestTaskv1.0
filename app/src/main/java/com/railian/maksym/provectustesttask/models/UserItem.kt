package com.railian.maksym.provectustesttask.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.railian.maksym.provectustesttask.models.Result


class UserItem {

    @SerializedName("results")
    @Expose
    val results: List<Result>? = null
}
