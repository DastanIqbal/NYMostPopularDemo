package com.nyt.mostpopular.mostviewed.adapter

import androidx.recyclerview.widget.RecyclerView
import com.nyt.mostpopular.databinding.ItemMostviewedBinding
import com.nyt.network.model.ResultB

class MostViewedVH(val binding: ItemMostviewedBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(result: ResultB) {
        binding.resultB = result
    }
}
