package com.example.carnotassignment

import com.example.carnotassignment.models.Records

object ValidationUtil {
    fun validateMovie(data: Records) : Boolean {
        if (data.commodity.isNotEmpty() && data.max_price != 0 && data.min_price !=0 && data.modal_price !=0) {
            return true
        }
        return false
    }
}