package com.example.android.lazyengineer.ui.syllabus

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.android.lazyengineer.R
import com.example.android.lazyengineer.databinding.ItemSyllabusBinding

class SyllabusListAdapter: ListAdapter<Result, SyllabusListAdapter.ViewHolder>(DiffCallback){

    class ViewHolder(private val binding: ItemSyllabusBinding): RecyclerView.ViewHolder(binding.root){
        fun bind (syllabus: Result){
            binding.syllabus = syllabus
            binding.executePendingBindings()
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<Result>(){
        override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem.id == newItem.id
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_syllabus,parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val syllabus = getItem(position)
        holder.bind(syllabus)
    }
}