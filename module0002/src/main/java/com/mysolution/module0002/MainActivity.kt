package com.mysolution.module0002

/**
 * Author: github.com/micro3721
 * E-mail: mysolution@qq.com
 * Provide software outsourcing services
 * 我承接软件定制开发服务,欢迎垂询
 * Created on 2024/6/24
 * Describe:
 */
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.subjects.PublishSubject

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: CustomAdapter
    private val viewAddSubject = PublishSubject.create<CustomTextView>()
    private val viewRemoveSubject = PublishSubject.create<CustomTextView>()
    private val viewList: MutableList<CustomTextView> = mutableListOf()
    private val disposables = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = CustomAdapter(viewList)
        recyclerView.adapter = adapter

        val addButton: Button = findViewById(R.id.add_button)
        val removeButton: Button = findViewById(R.id.remove_button)

        addButton.setOnClickListener { addNewView() }
        removeButton.setOnClickListener { removeView() }

        disposables.add(viewAddSubject.subscribe { view ->
            viewList.add(view)
            adapter.notifyItemInserted(viewList.size - 1)
        })

        disposables.add(viewRemoveSubject.subscribe { view ->
            val position = viewList.indexOf(view)
            if (position != -1) {
                viewList.removeAt(position)
                adapter.notifyItemRemoved(position)
            }
        })
    }

    private fun addNewView() {
        val newView = CustomTextView(this)
        newView.id = View.generateViewId() // Ensure each view has a unique ID
        newView.setViewNumber(viewList.size + 1)
        viewAddSubject.onNext(newView)
    }

    private fun removeView() {
        if (viewList.isNotEmpty()) {
            val viewToRemove = viewList.last()
            viewRemoveSubject.onNext(viewToRemove)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        disposables.clear() // Clear all subscriptions to prevent memory leaks
    }
}

