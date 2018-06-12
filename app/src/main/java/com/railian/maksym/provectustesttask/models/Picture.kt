package com.railian.maksym.provectustesttask.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Picture(@field:SerializedName("thumbnail")
              @field:Expose
              val thumbnail: String, @field:SerializedName("medium")
              @field:Expose
              val medium: String, @field:SerializedName("large")
              @field:Expose
              val large: String)
