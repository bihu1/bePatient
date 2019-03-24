package com.dryPepperoniStickTeam.bePatient;

import com.dryPepperoniStickTeam.bePatient.domain.doctor.Doctor;
import com.dryPepperoniStickTeam.bePatient.domain.doctor.DoctorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
//@TestPropertySource(locations="classpath:application-test.properties")
public class IntegrationTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	DoctorRepository doctorRepository;

	@BeforeEach
	public void contextLoads() {
		doctorRepository.save(new Doctor(0, "a"));
	}

	@Test
	public void exampleTest() {
		List<Doctor> doctors = doctorRepository.findAll();
		int x = 3;
		assertThat(x).isEqualTo(3);
	}

}
