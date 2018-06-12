package com.railian.maksym.provectustesttask.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Login(@field:SerializedName("username")
            @field:Expose
            val username: String)
