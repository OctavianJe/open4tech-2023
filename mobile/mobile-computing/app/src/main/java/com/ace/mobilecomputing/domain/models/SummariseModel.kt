package com.ace.mobilecomputing.domain.models

import com.ace.mobilecomputing.data.models.NewsDataModel
import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.util.Date


class SummariseModel(
    @field:SerializedName("text") val text: String,
    @field:SerializedName("summary") val summary: String,
) : Serializable {

}