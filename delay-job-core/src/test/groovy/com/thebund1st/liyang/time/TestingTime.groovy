package com.thebund1st.liyang.time

import java.time.ZoneId
import java.time.ZonedDateTime

class TestingTime {
    private static ZoneId ZONED_ID = ZoneId.of("Asia/Shanghai")

    static ZonedDateTime nowWithZone() {
        ZonedDateTime.now(ZONED_ID)
    }
}
