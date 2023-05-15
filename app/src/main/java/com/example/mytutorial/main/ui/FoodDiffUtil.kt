package com.example.mytutorial.main.ui

import androidx.recyclerview.widget.DiffUtil
import com.example.mytutorial.main.ui.db.Food

class FoodDiffUtil(
    private val oldList: List<Food>,
    private val newList: List<Food>
): DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] === newList[newItemPosition]
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }
}