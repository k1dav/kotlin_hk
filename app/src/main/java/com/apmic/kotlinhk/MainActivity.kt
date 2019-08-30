package com.apmic.kotlinhk

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private val charPool : List<Char> = ('a'..'z') + ('A'..'Z') + ('0'..'9')

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val dispatcherUI = Dispatchers.Main
        val taskApi = TaskApi()

        btnGetById.setOnClickListener {
            GlobalScope.launch(dispatcherUI) {
                val tasks = taskApi.getTask(editTextID.text.toString().toInt())
                textView.text = tasks.toString()
            }
        }

        btnGetAll.setOnClickListener {
            GlobalScope.launch(dispatcherUI) {
                val tasks = taskApi.getTasks()
                textViewAll.text = tasks.toString()
            }
        }

        btnRandom.setOnClickListener {
            val randomTitle = (1..10)
                .map { i -> kotlin.random.Random.nextInt(0, charPool.size) }
                .map(charPool::get)
                .joinToString("")

            val randomDesc = (1..20)
                .map { i -> kotlin.random.Random.nextInt(0, charPool.size) }
                .map(charPool::get)
                .joinToString("")


            textViewTitle.text = randomTitle
            textViewDesc.text = randomDesc
        }

        btnAdd.setOnClickListener {
            val task = Task(
                title=textViewTitle.text.toString(),
                description=textViewDesc.text.toString()
            )
            GlobalScope.launch(dispatcherUI) {
                val task = taskApi.postTask(task)
                textViewResult.text = task.toString()
            }
        }
    }
}
