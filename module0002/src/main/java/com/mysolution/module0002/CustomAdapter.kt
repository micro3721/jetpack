package com.mysolution.module0002

/**
 * Author: github.com/micro3721
 * E-mail: mysolution@qq.com
 * Provide software outsourcing services
 * 我承接软件定制开发服务,欢迎垂询
 * Created on 2024/6/24
 * Describe:
 */

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class CustomAdapter(private val items: MutableList<CustomTextView>) :
    RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    class ViewHolder(val customTextView: CustomTextView) : RecyclerView.ViewHolder(customTextView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val customTextView = LayoutInflater.from(context)
            .inflate(R.layout.custom_text_view, parent, false) as CustomTextView
        return ViewHolder(customTextView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.customTextView.text = item.text
    }

    override fun getItemCount(): Int = items.size
}
