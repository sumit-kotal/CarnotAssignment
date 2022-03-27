package com.example.carnotassignment.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.request.CachePolicy
import coil.size.Scale
import com.example.carnotassignment.R
import com.example.carnotassignment.databinding.RecylerItemBinding
import com.example.carnotassignment.models.Records

class RecordsAdapter(private val context: Context, private val recordslist: List<Records>) :
    RecyclerView.Adapter<RecordsAdapter.ViewHolder>() {

    //this method is returning the view for each item in the list
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.recyler_item, parent, false)
        return ViewHolder(v)
    }

    //this method is binding the data on the list
    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            val record = recordslist[position]

            binding.textState.text = "${record.state} -"
            binding.textDistrict.text = record.district
            binding.textMarket.text = record.market
            binding.textCommodity.text = record.commodity
            binding.textVariety.text = "Variety - ${record.variety}"
            binding.textArrDate.text = record.arrival_date
            binding.textMinPrice.text = record.min_price.toString()
            binding.textMaxPrice.text = record.max_price.toString()
            binding.textModalPrice.text = record.modal_price.toString()


        }
    }

    //this method is giving the size of the list
    override fun getItemCount(): Int {
        return recordslist.size
    }

    //this method binds views to position
    override fun getItemViewType(position: Int): Int {
        return position
    }

    //the class is holding the list view and item click listener
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = RecylerItemBinding.bind(view)
    }


}