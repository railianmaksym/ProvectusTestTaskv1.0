package com.railian.maksym.provectustesttask.repository

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase

@Database(entities = [(DatabaseModel::class)],version = 1,exportSchema = false)

abstract class Database : RoomDatabase() {

    abstract fun databaseDao() : DatabaseHelper
}