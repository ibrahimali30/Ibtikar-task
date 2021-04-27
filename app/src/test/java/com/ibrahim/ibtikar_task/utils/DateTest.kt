package com.ibrahim.ibtikar_task.utils

import com.google.common.truth.Truth
import org.junit.Test

class DateTest {

    //timestamp 1619490724 = Tuesday, April 27, 2021 4:32:04 AM GMT+02:00

    @Test
    fun testTimeToFormattedStringAdapter(){
        val formatedDate = timeToFormattedStringAdapter(1619490724)

        Truth.assertThat(formatedDate).isEqualTo("07:51 PM 19/01/1970")
    }


    @Test
    fun testtimeToFormattedString(){
        val formatedDate = timeToFormattedString(1619490724)

        Truth.assertThat(formatedDate).isEqualTo("19 Jan 1970 07:51 PM")
    }
}