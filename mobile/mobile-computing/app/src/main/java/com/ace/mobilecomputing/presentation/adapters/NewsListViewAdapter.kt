package com.ace.mobilecomputing.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ace.mobilecomputing.R
import com.ace.mobilecomputing.data.models.NewsDataModel


class NewsListViewAdapter(
    private val contentList: List<NewsDataModel>,
    private val itemClickListener: OnItemClickListener
) : RecyclerView.Adapter<NewsListViewAdapter.ViewHolder>() {

    interface OnItemClickListener {
        fun onItemClick(content: NewsDataModel)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val contentName: TextView = itemView.findViewById(R.id.contentName)
        val contentDescription: TextView = itemView.findViewById(R.id.contentDescription)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.news_list_item_template, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val content = contentList[position]

        holder.contentName.text = content.title
        holder.contentDescription.text = content.description

        holder.itemView.setOnClickListener {
            itemClickListener.onItemClick(content)
        }
    }

    override fun getItemCount() = contentList.size
}