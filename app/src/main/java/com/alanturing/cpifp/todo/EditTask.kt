package com.alanturing.cpifp.todo

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.alanturing.cpifp.todo.data.TaskLocalRepository
import com.alanturing.cpifp.todo.databinding.ActivityEditTaskBinding
import com.alanturing.cpifp.todo.databinding.ActivityMainBinding
import com.alanturing.cpifp.todo.model.Task
import java.io.Serializable

class EditTask : AppCompatActivity() {
    private lateinit var binding: ActivityEditTaskBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        val repository = TaskLocalRepository.getInstance()
        binding = ActivityEditTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)

        super.onCreate(savedInstanceState)

        val bundle = this.intent.extras
        var id = 0
        if(bundle != null){
            val t: Task= bundle.getSerializable("Task") as Task
            id = t.id
            binding.titleInputEdit.setText(t.title)
            binding.descriptionInputEdit.setText(t.description)
            Log.d("check", t.isCompleted.toString())
            binding.switchEdit.isChecked = t!!.isCompleted
        }

        binding.btnAceptarEdit.setOnClickListener(){
            val task: Task = Task(id, binding.titleInputEdit.text.toString(), binding.descriptionInputEdit.text.toString(), binding.switchEdit.isChecked)
            repository.update(task)
            setResult(Activity.RESULT_OK)
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.btnCancelarEdit.setOnClickListener(){
            setResult(Activity.RESULT_CANCELED)
            finish()
        }
    }
}