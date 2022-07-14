package com.cpgroup.androidsource.recyclerview

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cpgroup.androidsource.R

/**
 * @author : 马世豪
 * time : 2022/7/13 14
 * email : ma_shihao@yeah.net
 * des : RecyclerView
 */
class RecyclerViewActivity : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView


    val mainItems by lazy {
        val list = mutableListOf<com.cpgroup.androidsource.MainItem>()

        for (i in 1..100) {
            list.add(
                com.cpgroup.androidsource.MainItem(
                    "$i",
                    RecyclerViewActivity::class.java
                )
            )
        }
        list
    }

    val adapter by lazy {
        com.cpgroup.androidsource.Adapter(this, mainItems)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView = findViewById(R.id.recyclerView)

        recyclerView.adapter = adapter

        recyclerView.layoutManager = LinearLayoutManager(this)

        findViewById<Button>(R.id.refresh).setOnClickListener {
            mainItems.add(
                com.cpgroup.androidsource.MainItem(
                    "102",
                    RecyclerViewActivity::class.java
                )
            )
            adapter.notifyDataSetChanged()
        }

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
//                context.startActivity(Intent(context, items[position].clazz))
            }
        }
    }

    override fun getItemCount() = items.size


}