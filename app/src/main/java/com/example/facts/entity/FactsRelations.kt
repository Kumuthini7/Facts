package com.example.facts.entity

import androidx.room.Embedded
import androidx.room.Relation
import com.google.gson.annotations.SerializedName


class FactsRelations {

    @Embedded
    @SerializedName("Facts")
    var Facts: Facts? = null

    @Relation(parentColumn = "id", entityColumn = "factsId", entity = RowsItem::class)
    @SerializedName("mRowsItem")
    var mRowsItem: List<RowsItem>? = null
}
