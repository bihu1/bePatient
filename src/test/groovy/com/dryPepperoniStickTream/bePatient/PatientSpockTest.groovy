package com.dryPepperoniStickTream.bePatient

import com.dryPepperoniStickTeam.bePatient.BePatientApplication
import com.dryPepperoniStickTeam.bePatient.domain.user.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.TestPropertySource
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import spock.lang.Specification

import static groovy.json.JsonOutput.toJson
import static org.springframework.http.MediaType.APPLICATION_JSON
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@AutoConfigureMockMvc(secure = false)
@SpringBootTest(classes = BePatientApplication)
@TestPropertySource(locations="classpath:application-test.properties")
class PatientSpockTest extends Specification{

    @Autowired
    private MockMvc mockMvc

    @Autowired
    UserRepository userRepository

    def "should create new patient"(){
        given:
        def map = [firstName:"Karol", lastName:"Popek", pesel:91010112345, email:"popek123@gmail.com", phone:883491101, password:"Password1!"]

        when:
        def result = mockMvc.perform(MockMvcRequestBuilders
                .post("patients/registration")
                .contentType(APPLICATION_JSON)
                .accept(APPLICATION_JSON)
                .content(toJson(map))
        )

        then:
        result.andExpect(status().isCreated())
        def users = userRepository.findAll().toList()
        users.size() == 1
        users.first().toString() == map.toString()
    }
}
