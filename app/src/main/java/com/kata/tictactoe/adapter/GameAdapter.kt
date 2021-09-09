package com.kata.tictactoe.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kata.tictactoe.databinding.RecyclerItemLayoutBinding

class GameAdapter(val onItemClicked: (Int) -> Unit) :
    RecyclerView.Adapter<GameAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val rootView = RecyclerItemLayoutBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return MyViewHolder(rootView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount() = TOTAL_CELL_COUNT


    inner class MyViewHolder(private val binding: RecyclerItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            binding.itemBtn.apply {
                setOnClickListener {
                    onItemClicked(position)
                }
            }
        }
    }

    companion object {
        const val TOTAL_CELL_COUNT = 9
    }
}