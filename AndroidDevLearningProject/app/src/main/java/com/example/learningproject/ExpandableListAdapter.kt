package com.example.learningproject

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.TextView

class ExpandableListAdapter(
    var context: Context,
    var messagesList: Array<MessageCollection>
): BaseExpandableListAdapter() {
    override fun getGroupCount(): Int {
        return messagesList.size
    }

    override fun getChildrenCount(p0: Int): Int {
        return messagesList[p0].messages.size
    }

    override fun getGroup(p0: Int): Any {
        return messagesList[p0]
    }

    override fun getChild(p0: Int, p1: Int): Any {
        return messagesList[p0].messages[p1]
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
        itemChild?.text = messagesList[p0].date
        return convertView
    }

    override fun getChildView(p0: Int, p1: Int, p2: Boolean, p3: View?, p4: ViewGroup?): View {
        var convertView = p3
        if (convertView == null) {
            val inflater = LayoutInflater.from(context)
            convertView = inflater.inflate(R.layout.list_expand_item, null)
        }
        var itemChild = convertView!!.findViewById<TextView>(R.id.childItem)
        itemChild?.text = messagesList[p0].messages[p1].toString()
        return convertView
    }

    override fun isChildSelectable(p0: Int, p1: Int): Boolean {
        return true
    }
}