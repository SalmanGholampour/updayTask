package com.upday.task.ui.main_page

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewOnScrollListener(private val listener: Listener) :
    RecyclerView.OnScrollListener() {
    private var previousTotal = 0
    private var loading = true
    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        val visibleItemCount = recyclerView.childCount
        val totalItemCount = recyclerView.layoutManager!!.itemCount
        val firstVisibleItem =
            (recyclerView.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()

        if (loading) {
            if (totalItemCount > previousTotal) {
                loading = false
                previousTotal = totalItemCount
            }
        }
        val threshold = 5
        if (!loading && totalItemCount - visibleItemCount <= firstVisibleItem + threshold) {

            listener.fetchMoreImage()

            loading = true
        }
    }

    interface Listener {
        fun fetchMoreImage()
    }

}