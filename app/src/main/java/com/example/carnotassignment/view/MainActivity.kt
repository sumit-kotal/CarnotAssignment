package com.example.carnotassignment.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.RotateAnimation
import android.widget.AdapterView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.transition.Slide
import androidx.transition.TransitionManager
import com.example.carnotassignment.adapters.RecordsAdapter
import com.example.carnotassignment.databinding.ActivityMainBinding
import com.example.carnotassignment.models.Records
import com.example.carnotassignment.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ObsoleteCoroutinesApi


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding
    private val mainViewModel: MainViewModel by viewModels()
    private var pagesResult = mutableListOf<Records>()
    private var pagesResultCopy = mutableListOf<Records>()

    private var offSet = 0
    private var limit = 50

    lateinit var  adapter : RecordsAdapter
    val layoutManager = LinearLayoutManager(this)


    @SuppressLint("NotifyDataSetChanged")
    @OptIn(ObsoleteCoroutinesApi::class, kotlinx.coroutines.ExperimentalCoroutinesApi::class,
        kotlinx.coroutines.FlowPreview::class
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        showLoadingScreen()

        adapter = RecordsAdapter(applicationContext,pagesResult)
        binding.recordsRecycler.layoutManager = layoutManager

        binding.recordsRecycler.adapter = adapter

        mainViewModel.getResults(offSet,limit)

        mainViewModel.dataResponse.observe(this){
            val result = it.records
            pagesResult.addAll(result.toMutableList())
            pagesResultCopy.addAll(pagesResult)
            showRecyclerList()
        }

        // RecyclerView Pagination********************************
        binding.recordsRecycler.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val visibleItemCount = layoutManager.childCount
                val pastVisibleItem = layoutManager.findFirstCompletelyVisibleItemPosition()
                val total = adapter.itemCount


                    if ((visibleItemCount + pastVisibleItem) >= total) {
                        offSet+=50
                        mainViewModel.getResults(offSet,limit)
                    }

                super.onScrolled(recyclerView, dx, dy)
            }
        })


        binding.sortSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {}

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                showLoadingScreen()
                sortList(position)
            }

        }
    }

    private fun sortList(key: Int) {
        val sortedList =  when(key){
            0 -> pagesResultCopy
            1 -> pagesResult.sortedBy { it.min_price }
            2 -> pagesResult.sortedBy { it.max_price }
            3 -> pagesResult.sortedBy { it.modal_price }
            else -> pagesResultCopy
        }

        pagesResult.clear()
        pagesResult.addAll(sortedList.toMutableList())
        showRecyclerList()
    }

    private fun showLoadingScreen() {
        binding.recordsRecycler.visibility = View.GONE
        binding.progressImage.visibility = View.VISIBLE
        val rotate = RotateAnimation(
            0F, TRANSITION_DEGREES,
            Animation.RELATIVE_TO_SELF, TRANSITION_PIVOT,
            Animation.RELATIVE_TO_SELF, TRANSITION_PIVOT
        )
        rotate.duration = Companion.ROTATE_ANIMATION_DURATION
        rotate.repeatCount = Animation.INFINITE
        binding.progressImage.startAnimation(rotate)
    }

    private fun showRecyclerList() {
        binding.progressImage.clearAnimation()
        binding.recordsRecycler.visibility = View.VISIBLE
        binding.progressImage.visibility = View.GONE
        val transition = Slide(Gravity.BOTTOM)
        transition.duration = TRANSITION_DURATION
        transition.addTarget(binding.recordsRecycler)
        TransitionManager.beginDelayedTransition(binding.rootview as ViewGroup, transition)
        binding.recordsRecycler.visibility = View.VISIBLE
        adapter.notifyDataSetChanged()
    }

    companion object {
        private const val ROTATE_ANIMATION_DURATION = 800L
        private const val TRANSITION_DURATION = 2000L

        private const val TRANSITION_DEGREES = 360F
        private const val TRANSITION_PIVOT = 0.5f
    }

}