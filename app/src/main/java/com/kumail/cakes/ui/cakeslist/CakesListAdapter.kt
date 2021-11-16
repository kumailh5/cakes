package com.kumail.cakes.ui.cakeslist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.kumail.cakes.R
import com.kumail.cakes.data.model.Cake
import com.kumail.cakes.databinding.ItemCakeBinding
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by kumailhussain on 16/11/2021.
 */
@Singleton
class CakesListAdapter @Inject constructor() :
    ListAdapter<Cake, CakesListAdapter.ViewHolder>(CakesListDiffCallback) {

    private var onItemClick: ((String, String) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context), R.layout.item_cake, parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val cake = getItem(position)
        holder.bind(cake)
    }

    fun setOnItemClickListener(onClick: (String, String) -> Unit) {
        onItemClick = onClick
    }

    inner class ViewHolder(private val binding: ItemCakeBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(cake: Cake) {
            binding.cake = cake
            binding.root.setOnClickListener {
                onItemClick?.let {
                    it(cake.title, cake.description)
                }
            }
        }
    }
}

object CakesListDiffCallback : DiffUtil.ItemCallback<Cake>() {
    override fun areItemsTheSame(oldItem: Cake, newItem: Cake): Boolean {
        return oldItem.title == newItem.title
    }

    override fun areContentsTheSame(oldItem: Cake, newItem: Cake): Boolean {
        return oldItem == newItem
    }
}