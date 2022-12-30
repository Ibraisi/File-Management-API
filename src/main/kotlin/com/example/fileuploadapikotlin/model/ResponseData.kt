package com.example.fileuploadapikotlin.model

data class ResponseData(
    var fileName: String,
    var downloadURL: String,
    var fileType: String,
    var fileSize: Long
)
