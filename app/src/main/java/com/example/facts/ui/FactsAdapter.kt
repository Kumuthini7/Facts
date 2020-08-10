package com.example.facts.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.facts.R
import com.example.facts.entity.RowsItem
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.adapter_facts_item.view.*


/**
 * Created by Kumuthini.N on 08-08-2020
 */

class FactsAdapter() : RecyclerView.Adapter<FactsAdapter.ViewHolder>() {

    private var context: Context? = null
    private var features: List<RowsItem?>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.adapter_facts_item, parent, false)
        return ViewHolder(view)
    }

    /**
     * bind title, description and image if data is available
     */
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = features?.get(position)
        holder.type.text = item?.title
        item?.description?.let {
            holder.description.visibility = View.VISIBLE
            holder.description.text = it
        } ?: run {
            holder.description.visibility = View.GONE
        }
        if (!item?.imageHref.isNullOrEmpty()) {
            Picasso.get().load(item?.imageHref).resize(170, 170)
                .into(holder.imageView, object : com.squareup.picasso.Callback {
                    override fun onSuccess() {
                        holder.imageView.visibility = View.VISIBLE
                    }

                    override fun onError(e: java.lang.Exception?) {
                        holder.imageView.visibility = View.GONE
                    }
                })

        } else {
            holder.imageView.visibility = View.GONE
        }
    }

    override fun getItemCount(): Int = features?.size ?: 0

    /**
     * get updated values from [FactsFragment]
     * filter values only if it has row information ie) combination of title, description and image should not be empty
     */
    fun update(
        context: Context,
        it1: List<RowsItem?>
    ) {
        this.context = context
        val filteredList = it1.filter {
            (it?.title.isNullOrEmpty() && it?.description.isNullOrEmpty() && it?.imageHref.isNullOrEmpty()).not()
        }

        this.features = filteredList
        notifyDataSetChanged()

    }

    inner class ViewHolder(
        mView: View
    ) : RecyclerView.ViewHolder(mView) {
        val type: TextView = mView.type
        val description: TextView = mView.description
        val imageView: ImageView = mView.imageView
    }
}
