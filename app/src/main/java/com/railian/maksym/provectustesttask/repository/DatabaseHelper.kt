package com.railian.maksym.provectustesttask.repository

import android.arch.persistence.room.*
import android.arch.persistence.room.OnConflictStrategy.REPLACE


@Dao interface DatabaseHelper {
    @Query("select * from DatabaseModel")
    fun getAllTasks(): List<DatabaseModel>

    @Query("select * from DatabaseModel where id = :id")
    fun findTaskById(id: Int): DatabaseModel

    @Insert(onConflict = REPLACE)
    fun insertTask(task: DatabaseModel)

    @Update(onConflict = REPLACE)
    fun updateTask(task: DatabaseModel)

    @Delete
    fun deleteTask(task: DatabaseModel)

    @Query("DELETE FROM DatabaseModel")
    fun dropTable()

    @Insert(onConflict = REPLACE)
    fun addAll(objects: List<DatabaseModel>)
}