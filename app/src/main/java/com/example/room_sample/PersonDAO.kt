package com.example.room_sample

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface PersonDAO {

    @Query("SELECT * FROM person WHERE name LIKE :name LIMIT 1")
    fun findByName(name: String): Person?

    @Insert
    fun insertAll(vararg persons: Person)

    @Query("DELETE FROM person WHERE name LIKE :name")
    fun deleteByName(name: String)
}