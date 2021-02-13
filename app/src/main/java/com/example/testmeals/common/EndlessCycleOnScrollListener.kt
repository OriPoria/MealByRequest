package com.example.testmeals.common

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class EndlessCycleOnScrollListener(recyclerView: RecyclerView) : RecyclerView.OnScrollListener() {
    private val TAG = "EndlessCycleOnScrollListener"

    val manager = recyclerView.layoutManager as LinearLayoutManager
    var liveListSize = MutableLiveData<Int>()

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        val listSize = liveListSize.value?.div(2)
        if (listSize != null) {
            val firstItemVisible = manager.findFirstVisibleItemPosition()
            if (listSize == 0) {
                return
            }
            if (firstItemVisible != 1 && firstItemVisible % listSize == 1) {
                manager.scrollToPosition(1)
            }
            val firstCompletelyItemVisible = manager.findFirstCompletelyVisibleItemPosition()
            if (firstCompletelyItemVisible == 0) {
                manager.scrollToPositionWithOffset(listSize, 0)
            }
        }
    }


}