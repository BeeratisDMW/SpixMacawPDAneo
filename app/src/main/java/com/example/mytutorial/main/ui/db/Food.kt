package com.example.mytutorial.main.ui.db

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize


@Entity(tableName = "food_table")
@Parcelize
data class Food(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    var title: String ,
    var desc: String ,
    var price: Float

): Parcelable {
    constructor(): this(0, "", "", 0f)

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Food

        if (id != other.id) return false
        if (title != other.title) return false
        if (desc != other.desc) return false
        if (price != other.price) return false

        return true
    }


}




