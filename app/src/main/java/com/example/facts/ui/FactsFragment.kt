package com.example.facts.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.facts.App
import com.example.facts.R
import com.example.facts.isNetworkAvailable
import com.example.facts.network.Status
import com.example.facts.showToast
import com.example.facts.viewModel.MainViewModel
import com.example.facts.viewModel.MainViewModelFactory
import kotlinx.android.synthetic.main.fragment_fact_list.*
import kotlinx.android.synthetic.main.fragment_fact_list.view.*
import javax.inject.Inject


/**
 * Created by Kumuthini.N on 08-08-2020
 */

class FactsFragment : Fragment() {

    @Inject
    lateinit var factory: MainViewModelFactory

    private lateinit var viewModel: MainViewModel
    private val adapter = FactsAdapter()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.component.inject(this)

        viewModel = ViewModelProviders.of(this, factory).get(MainViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_fact_list, container, false)

        val mLayoutManager = LinearLayoutManager(activity)
        mLayoutManager.orientation = RecyclerView.VERTICAL
        view.rView.layoutManager = mLayoutManager
        view.rView.adapter = adapter

        val dividerItemDecoration =
            androidx.recyclerview.widget.DividerItemDecoration(activity, mLayoutManager.orientation)
        view.rView.addItemDecoration(dividerItemDecoration)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        swipeRefreshLayout.setOnRefreshListener {
            swipeRefreshLayout.isRefreshing = true
            getFacts()
        }
    }

    override fun onResume() {
        super.onResume()
        getFacts()
    }

    private fun getFacts() {
        viewModel.getFacts().observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Status.LOADING -> {

                }
                Status.SUCCESS -> {
                    ( activity as FactsActivity).supportActionBar?.title = it.data?.Facts?.title

                    swipeRefreshLayout.isRefreshing = false
                    it?.data?.mRowsItem?.let {
                        adapter.update(this.requireContext(), it)
                    } ?: run {
                        if(isNetworkAvailable(this.requireContext())){
                            showToast(this.requireContext(),resources.getString(R.string.no_data_available))
                        } else {
                            showToast(this.requireContext(),resources.getString(R.string.check_internet))
                        }
                    }
                }
                Status.ERROR -> {
                    showToast(this.requireContext(),resources.getString(R.string.something_went_wrong))
                }
            }
        })
    }
}
