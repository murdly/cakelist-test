package com.akarbowy.cakelisttest.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import com.akarbowy.cakelisttest.R
import com.akarbowy.cakelisttest.injection.getViewModel
import com.akarbowy.cakelisttest.injection.injector
import com.akarbowy.cakelisttest.ui.MainViewModel.MainState.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.view_error.*

class MainActivity : AppCompatActivity(), CakeViewHolder.OnCakeClickListener {

    private val viewModel by lazy {
        getViewModel { injector.mainViewModel }
    }

    private lateinit var adapter: CakesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initUi()
    }

    private fun initUi() {
        setupList()

        refresh.setOnRefreshListener { viewModel.refresh() }

        retry.setOnClickListener { viewModel.retry() }

        viewModel.state.observe(this, Observer { updateUI(it) })

        viewModel.loadCakes()
    }

    private fun setupList() {
        adapter = CakesAdapter(this)
            .also { list.adapter = it }

        list.setHasFixedSize(true)
        list.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
    }

    private fun updateUI(mainAction: MainViewModel.MainState?) {
        when (mainAction) {
            is ShowLoading -> showLoading()

            is ShowCakes -> showCakes(mainAction.uim)

            is ShowError -> showError()

            is ShowEmpty -> showEmpty()
        }
    }

    private fun showLoading() {
        loading.visibility = View.VISIBLE
        list.visibility = View.GONE
        error.visibility = View.GONE
        refresh.isRefreshing = false
    }

    private fun showCakes(uim: List<CakeUIM>) {
        list.visibility = View.VISIBLE
        empty.visibility = View.GONE
        loading.visibility = View.GONE
        error.visibility = View.GONE
        refresh.isRefreshing = false

        adapter.submitList(uim)
    }

    private fun showError() {
        error.visibility = View.VISIBLE
        empty.visibility = View.GONE
        list.visibility = View.GONE
        loading.visibility = View.GONE
        refresh.isRefreshing = false
    }

    private fun showEmpty() {
        empty.visibility = View.VISIBLE
        error.visibility = View.GONE
        list.visibility = View.GONE
        loading.visibility = View.GONE
        refresh.isRefreshing = false
    }

    override fun onCakeClicked(cake: CakeUIM) {
        AlertDialog.Builder(this)
            .setTitle(cake.title)
            .setMessage(cake.description)
            .setPositiveButton(getString(R.string.dialog_positive_button)) { _, _ -> /*do nothing*/ }
            .show()
    }

}