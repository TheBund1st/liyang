package com.thebund1st.liyang.domain.model

class JobSourceFixture {
    private String context
    private String objectId

    def withContext(String value) {
        this.context = value
        this
    }

    def withObjectId(String value) {
        this.objectId = value
        this
    }

    def build() {
        JobSource.of(context, objectId)
    }

    static def aJobSource() {
        new JobSourceFixture()
                .withContext("FOO")
                .withObjectId(TestingIdentifier.next())
    }
}
