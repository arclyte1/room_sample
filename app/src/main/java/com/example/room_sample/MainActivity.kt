package com.example.room_sample

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.room_sample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var repository: PersonRepository

    private lateinit var binding: ActivityMainBinding

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        repository = PersonRepository(applicationContext)

        binding.add.setOnClickListener {
            if (binding.nameField.text.toString().isNotBlank() &&
                    binding.ageField.text.toString().isNotBlank()) {
                val person = Person(
                    name = binding.nameField.text.toString(),
                    age = binding.ageField.text.toString().toInt()
                )
                repository.insertPersons(person)
                repository.getPersonByName(person.name)?.let { it1 -> setPerson(it1) }

            }
        }

        binding.read.setOnClickListener {
            if (binding.nameField.text.toString().isNotBlank()) {
                val person = repository.getPersonByName(binding.nameField.text.toString())
                if (person != null)
                    setPerson(person)
            }
        }

        binding.delete.setOnClickListener {
            if (binding.nameField.text.toString().isNotBlank()) {
                    repository.deletePersonByName(binding.nameField.text.toString())
                    binding.idText.text = "id: "
                    binding.nameField.setText("")
                    binding.ageField.setText("")
            }
        }


    }

    @SuppressLint("SetTextI18n")
    fun setPerson(person: Person) {
        binding.idText.text = ("id: " +person.id.toString())
        binding.nameField.setText(person.name)
        binding.ageField.setText(person.age.toString())
    }
}