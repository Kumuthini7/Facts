package com.example.facts.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "Facts")
data class Facts(
    @PrimaryKey(autoGenerate = true)
	@SerializedName("id")
	var id: Int?,

    @SerializedName("title")
    var title: String? = null,

    @Ignore
    @Embedded
    @SerializedName("rows")
    var rows: List<RowsItem?>? = null
){
	constructor() : this(0,"",null)
}

@Entity(tableName = "RowsItem")
data class RowsItem(
    @PrimaryKey(autoGenerate = true)
    @SerializedName("id")
    var id: Int?,

    @SerializedName("factsId")
    var factsId: Int?,

    @SerializedName("imageHref")
    var imageHref: String? = null,

    @SerializedName("description")
    var description: String? = null,

    @SerializedName("title")
    var title: String? = null
)
