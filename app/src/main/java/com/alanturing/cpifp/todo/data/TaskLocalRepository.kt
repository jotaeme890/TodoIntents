package com.alanturing.cpifp.todo.data

import com.alanturing.cpifp.todo.model.Task

class TaskLocalRepository() {
    private var cnt = 0
    companion object {
        private var _INSTANCE:TaskLocalRepository? = null
        fun getInstance():TaskLocalRepository {
            if( _INSTANCE == null){
                _INSTANCE = TaskLocalRepository()
            }
            return  _INSTANCE!!
        }
    }
    private val _tasks = mutableListOf<Task>()


    val tasks:List<Task>
        get() = _tasks

    fun add(task:Task) {
        task.id = cnt++
        this._tasks.add(task)
    }
    fun delete(id:Int) {
        val existingTask = this._tasks.find { it.id == id }
        if(existingTask != null){
            this._tasks.remove(existingTask)
        }
    }
    fun update(task:Task) {
        val existingTask = this._tasks.find { it.id == task.id }
        if(existingTask != null){
            val index = this._tasks.indexOf(existingTask)
            if(index != -1) {
                this._tasks[index] = task
            }
        }
    }
}