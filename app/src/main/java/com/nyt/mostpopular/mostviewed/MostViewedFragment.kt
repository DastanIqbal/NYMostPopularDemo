package com.nyt.mostpopular.mostviewed

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.observe
import androidx.recyclerview.widget.DividerItemDecoration
import com.dastanapps.dastanlib.utils.NetworkUtils
import com.nyt.mostpopular.R
import com.nyt.mostpopular.articledetails.ArticlesDetailActivity
import com.nyt.mostpopular.mostviewed.adapter.MostViewedAdapter
import com.nyt.network.NetworkStates
import com.nyt.views.NytWebView
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

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.mostviewed, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        viewModel.onOptionsItemSelected(item.itemId)
        return super.onOptionsItemSelected(item)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setHasOptionsMenu(true)
        checkNetworkConnections()

        viewModel = ViewModelProviders.of(this).get(MostViewedViewModel::class.java)
        viewModel.mostViewedList.observe(this, {
            with(rv) {
                addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))

                val mvAdap = MostViewedAdapter(it) {
                    startActivity(Intent(context, ArticlesDetailActivity::class.java).apply {
                        putExtra(NytWebView.NYTWEBVIEW_URL, it.url)
                    })
                }
                adapter = mvAdap
            }
        })
        viewModel.showProgressLiveData.observe(this, {
            with(pb) {
                visibility = if (it) {
                    rv.visibility = View.GONE
                    View.VISIBLE
                } else {
                    rv.visibility = View.VISIBLE
                    View.GONE
                }
            }
        })

        viewModel.networkStatusLiveData.observe(this, {
            when (it) {
                NetworkStates.NOINTERNET -> checkNetworkConnections() //Renable No-Network UI when retry
            }
        })
    }

    private fun checkNetworkConnections() {
        if (NetworkUtils.isConnectingToInternet(context!!)) {
            rv.visibility = View.VISIBLE

            ll_nodata.visibility = View.GONE
        } else {
            rv.visibility = View.GONE
            pb.visibility = View.GONE
            ll_nodata.visibility = View.VISIBLE

            btn_retry.setOnClickListener {
                viewModel.reload()
                ll_nodata.visibility = View.GONE
            }
        }
    }

}
