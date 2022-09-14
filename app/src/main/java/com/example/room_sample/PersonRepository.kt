package com.example.room_sample

import android.content.Context
import androidx.room.Room

class PersonRepository(context: Context) {

    val db = Room.databaseBuilder(
        context,
        PersonDB::class.java, "persons"
    ).allowMainThreadQueries().build()
    val dao = db.personDao()

    fun getPersonByName(name: String): Person? {
        return dao.findByName(name)
    }

    fun insertPersons(vararg persons: Person) {
        dao.insertAll(*persons)
    }

    fun deletePersonByName(name: String) {
        dao.deleteByName(name)
    }

}