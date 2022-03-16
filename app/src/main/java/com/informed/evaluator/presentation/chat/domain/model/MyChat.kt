package com.informed.trainee.presentation.chat.domain.model

data class MyChat (
    var id: Int=0,
    var from: Int=0,
    var to: Int=0,
    var content: String?,
    var timestamp: String?
)