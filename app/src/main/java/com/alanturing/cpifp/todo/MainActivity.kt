package com.alanturing.cpifp.todo

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import com.alanturing.cpifp.todo.adapter.TasksAdapter
import com.alanturing.cpifp.todo.data.TaskLocalRepository
import com.alanturing.cpifp.todo.databinding.ActivityMainBinding
import com.alanturing.cpifp.todo.model.Task
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val repository = TaskLocalRepository.getInstance()
    private val getResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        when (it.resultCode){
            Activity.RESULT_OK -> {
                binding.tasks.adapter = TasksAdapter(repository.tasks, ::onShareItem, ::onEditItem)
            }
            Activity.RESULT_CANCELED -> {
                Snackbar.make(this.binding.root,"Se ha cancelado", Snackbar.LENGTH_SHORT).show()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //TODO("Recuperar el RecyclerView y asignar el adaptador")
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tasks.adapter = TasksAdapter(repository.tasks, ::onShareItem, ::onEditItem)
        //TODO("Crear vista de detalles")
        //TODO("Crear vista de formulario de creacón")
        //TODO("Crear manejador de evento para navegar al formulario de crear")

        binding.floatingButton.setOnClickListener(){
            val intent = Intent(this,CreateToDoActivity::class.java)
            //startActivity(intent)
            getResult.launch(intent)
        }
    }

    override fun onResume() {
        super.onResume()
    }

    fun onShareItem(task: Task){
        val statusText = if(task.isCompleted)"Completada" else "Pendiente"
        val shareText = getString(R.string.share_text,task.title,task.description,statusText)
        val intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, shareText)
            type = "text/plain"
        }
        val shareIntent = Intent.createChooser(intent, null)
        startActivity(shareIntent)
    }

    fun onEditItem(task: Task){
        val intent = Intent(this,EditTask::class.java)
        val bundle = Bundle()
        bundle.putSerializable("Task",task)
        intent.putExtras(bundle)
        startActivity(intent)
    }
}
