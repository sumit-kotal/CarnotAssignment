package com.example.carnotassignment

import com.example.carnotassignment.models.Records
import junit.framework.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class ValidationUtilTest {
    @Test
    fun validateRecordTest() {
        val record = Records("testState","testDistrict","testMarket",
            "testCommodity","testVariety","testDate",100,100,100)
        assertEquals(true, ValidationUtil.validateRecord(record))
    }

    @Test
    fun validateRecordEmptyTest() {
        val record = Records("testState","testDistrict","testMarket",
            "","testVariety","",0,0,0)
        assertEquals(false, ValidationUtil.validateRecord(record))
    }

}