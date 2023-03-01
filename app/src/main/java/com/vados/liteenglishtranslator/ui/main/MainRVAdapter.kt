package com.vados.liteenglishtranslator.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.vados.liteenglishtranslator.databinding.ActivityMainRecyclerviewItemBinding
import com.molchanov.domain.model.domain.DataModel
import com.vados.liteenglishtranslator.utils.parsel.convertMeaningsToString

/**
 * Адаптер для вывода результатов перевода в элементы RecyclerView
 */
class MainRVAdapter(
    private var onListItemClickListener: OnListItemClickListener,
    private var data: List<DataModel>
) : RecyclerView.Adapter<MainRVAdapter.RecyclerItemViewHolder>() {

    fun setData(data: List<DataModel>) {
        this.data = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerItemViewHolder {
        return RecyclerItemViewHolder(
            ActivityMainRecyclerviewItemBinding.inflate(LayoutInflater.from(parent.context)).root
        )
    }

    override fun onBindViewHolder(holder: RecyclerItemViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class RecyclerItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(data: DataModel) {
            ActivityMainRecyclerviewItemBinding.bind(itemView).let {
                if (layoutPosition != RecyclerView.NO_POSITION) {
                    it.headerTextviewRecyclerItem.text = data.text
                    it.descriptionTextviewRecyclerItem.text =
                        convertMeaningsToString(data.meanings!!)

                    itemView.setOnClickListener { openInNewWindow(data) }
                }
            }
        }
    }

    private fun openInNewWindow(listItemData: DataModel) {
        onListItemClickListener.onItemClick(listItemData)
    }

    interface OnListItemClickListener {
        fun onItemClick(data: DataModel)
    }
}