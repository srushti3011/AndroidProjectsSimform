package com.example.learningproject

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.TextView

class MovieExpandableLVAdapter(
    var context: Context,
    var data: Array<MoviesForList>
): BaseExpandableListAdapter() {
    override fun getGroupCount(): Int {
        return data.size
    }

    override fun getChildrenCount(p0: Int): Int {
        return data[p0].description.size
    }

    override fun getGroup(p0: Int): Any {
        return data[p0]
    }

    override fun getChild(p0: Int, p1: Int): Any {
        return data[p0].description[p1]
    }

    override fun getGroupId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getChildId(p0: Int, p1: Int): Long {
        return p1.toLong()
    }

    override fun hasStableIds(): Boolean {
        return false
    }

    override fun getGroupView(p0: Int, p1: Boolean, p2: View?, p3: ViewGroup?): View {
        var convertView = p2
        if (convertView == null) {
            val inflater = LayoutInflater.from(context)
            convertView = inflater.inflate(R.layout.list_expand_group, null)
        }
        var itemChild = convertView!!.findViewById<TextView>(R.id.groupHeader)
        itemChild?.text = data[p0].name
        return convertView
    }

    override fun getChildView(p0: Int, p1: Int, p2: Boolean, p3: View?, p4: ViewGroup?): View {
        var convertView = p3
        if (convertView == null) {
            val inflater = LayoutInflater.from(context)
            convertView = inflater.inflate(R.layout.list_expand_item, null)
        }
        var itemChild = convertView!!.findViewById<TextView>(R.id.childItem)
        itemChild?.text = data[p0].description[p1].toString()
        return convertView
    }

    override fun isChildSelectable(p0: Int, p1: Int): Boolean {
        return true
    }
}