package com.nyt.mostpopular.mostviewed.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nyt.mostpopular.databinding.ItemMostviewedBinding
import com.nyt.network.model.ResultB

class MostViewedAdapter(
        private val list: List<ResultB>,
        private val clickItems: (resultB: ResultB) -> Unit
) : RecyclerView.Adapter<MostViewedVH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            MostViewedVH(
                    ItemMostviewedBinding.inflate(
                            LayoutInflater.from(parent.context),
                            parent,
                            false
                    )
            )


    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: MostViewedVH, position: Int) {
        holder.bind(list[position])
        holder.binding.root.setOnClickListener {
            clickItems.invoke(list[position])
        }
    }
}
