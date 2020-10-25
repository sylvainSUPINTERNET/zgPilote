package com.example.myapplication.ui

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.myapplication.R
import com.example.myapplication.api.dto.ProductDto

class HomeAdapter(private val ctx: Context, private val dataSource: List<ProductDto>) : BaseAdapter() {
    private val inflater: LayoutInflater = ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    // TODO => https://www.raywenderlich.com/155-android-listview-tutorial-with-kotlin#toc-anchor-004


    fun getItemAtPosition(position: Int): ProductDto {
        return dataSource[position]
    }


    //1
    override fun getCount(): Int {
        return dataSource.size
    }

    //2
    override fun getItem(position: Int): Any {
        Log.d("test", dataSource[position].name)
        return dataSource[position]
    }

    //3
    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    //4
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        // Get view for row item
        val rowView = inflater.inflate(R.layout.list_item_product, parent, false)
        val textTitle = rowView.findViewById<TextView>(R.id.item_title) as TextView

        val task = getItem(position) as ProductDto
        textTitle.text = task.name

        return rowView
    }
}