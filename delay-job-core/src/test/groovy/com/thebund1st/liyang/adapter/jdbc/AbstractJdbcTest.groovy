package com.thebund1st.liyang.adapter.jdbc

import com.thebund1st.liyang.boot.adapter.jdbc.JdbcConfiguration
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.context.annotation.Import
import org.springframework.test.context.ActiveProfiles
import org.springframework.transaction.annotation.Transactional
import spock.lang.Specification

import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE
import static org.springframework.transaction.annotation.Propagation.NOT_SUPPORTED

@Import([JdbcConfiguration])
@AutoConfigureTestDatabase(replace = NONE)
@Transactional(propagation = NOT_SUPPORTED)
@DataJdbcTest
@ActiveProfiles(profiles = "commit")
class AbstractJdbcTest extends Specification {


}
