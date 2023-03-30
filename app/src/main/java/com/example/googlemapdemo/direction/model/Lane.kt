package com.example.googlemapdemo.direction.model

import java.io.Serializable

data class Lane (
    val indications: List<String>,
    val valid: Boolean,
    val active: Boolean,
    val validIndication: String? = null
): Serializable