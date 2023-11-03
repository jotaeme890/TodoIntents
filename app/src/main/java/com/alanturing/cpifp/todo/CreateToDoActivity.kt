package com.alanturing.cpifp.todo

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.alanturing.cpifp.todo.data.TaskLocalRepository
import com.alanturing.cpifp.todo.databinding.ActivityCreateToDoBinding
import com.alanturing.cpifp.todo.databinding.ActivityMainBinding
import com.alanturing.cpifp.todo.model.Task

class CreateToDoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCreateToDoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        val repository = TaskLocalRepository.getInstance()
        super.onCreate(savedInstanceState)
        binding = ActivityCreateToDoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.botonCrear.setOnClickListener(){
            if(binding.titleInput.text.toString() != "" && binding.descriptionInput.text.toString() != ""){
                var id = -1
                val task = Task(id, binding.titleInput.text.toString(), binding.descriptionInput.text.toString())
                repository.add(task)
            }
            else{
                Toast.makeText(this, "Debe rellenar los campos", Toast.LENGTH_SHORT).show()
            }
            setResult(Activity.RESULT_OK)
            finish()
        }

        binding.btnCancelar.setOnClickListener(){
            setResult(Activity.RESULT_CANCELED)
            finish()
        }
    }
}
