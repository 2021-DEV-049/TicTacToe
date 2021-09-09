package com.kata.tictactoe.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kata.tictactoe.databinding.RecyclerItemLayoutBinding

class GameAdapter(val onItemClicked: (Int) -> Unit) :
    RecyclerView.Adapter<GameAdapter.MyViewHolder>() {

    private var cellValue: String = ""

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val rootView = RecyclerItemLayoutBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return MyViewHolder(rootView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(cellValue, position)
    }

    override fun getItemCount() = TOTAL_CELL_COUNT

    fun updateGameBoardData(position: Int, newCellValue: String) {
        cellValue = newCellValue
        notifyItemChanged(position, cellValue)
    }

    inner class MyViewHolder(private val binding: RecyclerItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(cellValue: String, position: Int) {
            binding.itemBtn.apply {
                text = cellValue
                setOnClickListener {
                    if (cellValue.isEmpty())
                        onItemClicked(position)
                }
            }
        }
    }

    companion object {
        const val TOTAL_CELL_COUNT = 9
    }
}