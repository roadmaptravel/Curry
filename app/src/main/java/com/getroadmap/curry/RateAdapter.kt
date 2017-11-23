package com.getroadmap.curry

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.getroadmap.lib.models.Rate
import kotlinx.android.synthetic.main.list_item_rate.view.*
import java.util.*

/**
 * Created by jan on 23/11/2017.
 */
class RateAdapter(val context: Context, var rates: List<Rate>, val clickListener: (Rate) -> Unit) : RecyclerView.Adapter<RateAdapter.ViewHolder>() {

    fun updateRates(list: List<Rate>) {
        rates = list
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = rates.size

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        val rate = rates.get(position)

        val currency = Currency.getInstance(rate.currency)
        //bind views
        holder?.currency?.setText(currency.displayName + " ("+currency.symbol+")")
        holder?.rate?.setText(rate.rate.toString())

        //pass clicked rate
        holder?.itemView?.setOnClickListener {
            clickListener(rate)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent?.context)
                .inflate(R.layout.list_item_rate, parent, false)

        return ViewHolder(itemView)
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var currency: TextView = view.currency
        var rate: TextView = view.rate

    }
}