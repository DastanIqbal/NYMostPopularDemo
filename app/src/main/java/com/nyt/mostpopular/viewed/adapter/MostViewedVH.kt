package com.nyt.mostpopular.viewed.adapter

import androidx.recyclerview.widget.RecyclerView
import com.nyt.mostpopular.databinding.ItemMostviewedBinding
import com.nyt.mostpopular.network.model.ResultB

class MostViewedVH(private val binding: ItemMostviewedBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(result: ResultB) {
        binding.resultB = result
    }
}
