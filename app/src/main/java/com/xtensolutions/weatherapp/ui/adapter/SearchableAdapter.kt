package com.xtensolutions.weatherapp.ui.adapter

import android.widget.Filter
import android.widget.Filterable
import java.util.*
import kotlin.collections.ArrayList


/**
 * Created by Vaghela Mithun R. on 18/02/21.
 * vaghela.mithun@gmail.com
 */
abstract class SearchableAdapter<T>(searchableList: MutableList<T>) :
    SelectableItemAdapter<T>(searchableList), Filterable {

    var dataList: MutableList<T> = ArrayList()

    init {
        dataList.addAll(searchableList)
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(query: CharSequence?): FilterResults {
                val results = FilterResults()
                if (query == null || query.length == 0) {
                    results.values = dataList
                    results.count = dataList.size
                } else {
                    val filteredList = ArrayList<T>()

                    for (position in 0 until dataList.size - 1) {
                        val value =
                            searchableValue(position).lowercase(Locale.getDefault()).trim()
                        val lowerQuery = query.toString().lowercase(Locale.getDefault()).trim()

                        if (value.contains(lowerQuery)) {
                            System.out.println("Search result added::$value")
                            filteredList.add(dataList.get(position))
                        }
                    }

                    results.values = filteredList
                    results.count = filteredList.size
                }
                return results
            }

            override fun publishResults(query: CharSequence?, results: FilterResults?) {
                if (results!!.count > 0)
                    updateList(results!!.values as MutableList<T>)
            }
        }
    }

    abstract fun searchableValue(position: Int): String
}
