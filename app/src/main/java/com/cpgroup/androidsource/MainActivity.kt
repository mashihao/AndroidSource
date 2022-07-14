package com.cpgroup.androidsource

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.Nullable
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cpgroup.androidsource.recyclerview.RecyclerViewActivity
import kotlin.reflect.KClass

class MainActivity : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView


    val mainItems by lazy {
        val list = mutableListOf<MainItem>()

        list.add(MainItem("RecyclerView", RecyclerViewActivity::class.java))
        list
    }

    val adapter by lazy {
        Adapter(this, mainItems)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView = findViewById(R.id.recyclerView)

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter


    }
}


data class MainItem(
    var title: String,
    var clazz: Class<out Activity>
)

class Adapter(var context: Context, var items: MutableList<MainItem>) :
    RecyclerView.Adapter<Adapter.VM>() {


    inner class VM(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VM {
        val itemView = LayoutInflater
            .from(context)
            .inflate(R.layout.item_main, parent, false)
        return VM(itemView)
    }

    override fun onBindViewHolder(holder: VM, position: Int) {
        holder.itemView.findViewById<TextView>(R.id.conent).apply {

            text = items[position].title
            setOnClickListener {
                //页面跳转
                context.startActivity(Intent(context, items[position].clazz))
            }
        }
    }

    override fun getItemCount() = items.size


}