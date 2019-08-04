package com.thebund1st.liyang.domain.model

class TestingIdentifier {
    static def next() {
        UUID.randomUUID().toString().replace("-", "")
    }
}
