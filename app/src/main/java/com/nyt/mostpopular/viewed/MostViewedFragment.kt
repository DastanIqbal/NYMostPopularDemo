package com.nyt.mostpopular.viewed

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.observe
import androidx.recyclerview.widget.DividerItemDecoration
import com.nyt.mostpopular.R
import com.nyt.mostpopular.viewed.adapter.MostViewedAdapter
import kotlinx.android.synthetic.main.mostviewed_fragment.*

class MostViewedFragment : Fragment() {

    companion object {
        fun newInstance() = MostViewedFragment()
    }

    private lateinit var viewModel: MostViewedViewModel

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.mostviewed_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MostViewedViewModel::class.java)

        viewModel.mostViewedList.observe(this, {
            with(rv) {
                addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
                val mvAdap = MostViewedAdapter(it)
                adapter = mvAdap
            }
        })
    }

}
