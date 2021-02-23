package com.example.android.lazyengineer.ui.syllabus

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView

@BindingAdapter("syllabusListData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<Result>?){
    if (!data.isNullOrEmpty()) {
        val adapter = recyclerView.adapter as SyllabusListAdapter
        adapter.submitList(data)
    }
}