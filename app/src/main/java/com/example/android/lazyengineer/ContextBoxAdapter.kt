package com.example.android.lazyengineer

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.android.lazyengineer.databinding.MainBoxBinding

class ContextBoxAdapter(
        private val data: List<BoxComponents>,
        private val listener: BoxClickListener
) : RecyclerView.Adapter<ContextBoxAdapter.BoxContentViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            BoxContentViewHolder = BoxContentViewHolder.from(parent)

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: BoxContentViewHolder, position: Int) {
        holder.bind(data[position], listener)
    }

    class BoxContentViewHolder private constructor(
            private val binding: MainBoxBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: BoxComponents, listener: BoxClickListener) {
            binding.box = item
            binding.executePendingBindings()
            binding.root.setOnClickListener {
                listener.onBoxClickListener(item.title)
            }
        }

        companion object {
            fun from(parent: ViewGroup): BoxContentViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = MainBoxBinding.inflate(layoutInflater, parent, false)
                return BoxContentViewHolder(binding)
            }
        }
    }
}