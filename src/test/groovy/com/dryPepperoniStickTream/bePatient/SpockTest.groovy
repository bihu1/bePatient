package com.dryPepperoniStickTream.bePatient

import com.dryPepperoniStickTeam.bePatient.BePatientApplication
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import spock.lang.Specification

@AutoConfigureMockMvc
@SpringBootTest(classes = BePatientApplication)
class SpockTest extends Specification{

    @Autowired
    private MockMvc mockMvc

    def "example test"(){
        given:
        def a = 5

        expect:
        a ==5
    }
}
