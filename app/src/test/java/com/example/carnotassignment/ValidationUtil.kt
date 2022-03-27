package com.example.carnotassignment

import com.example.carnotassignment.models.Records

object ValidationUtil {
    fun validateRecord(data: Records) : Boolean {
        if (data.commodity.isNotEmpty() && data.maxPrice != 0 && data.minPrice !=0 && data.modalPrice !=0) {
            return true
        }
        return false
    }
}