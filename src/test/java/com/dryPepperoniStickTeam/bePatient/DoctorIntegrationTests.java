package com.dryPepperoniStickTeam.bePatient;

import com.dryPepperoniStickTeam.bePatient.domain.doctor.Doctor;
import com.dryPepperoniStickTeam.bePatient.domain.doctor.DoctorRepository;
import com.dryPepperoniStickTeam.bePatient.domain.doctor.DoctorService;
import com.dryPepperoniStickTeam.bePatient.domain.doctor.http.model.DoctorView;
import com.dryPepperoniStickTeam.bePatient.domain.user.RoleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@SpringBootTest
@ExtendWith(SpringExtension.class)
@TestPropertySource(locations="classpath:application-test.properties")
@Transactional
public class DoctorIntegrationTests {

	@Autowired
	DoctorRepository doctorRepository;

	@Autowired
	DoctorService doctorService;

	@Autowired
	RoleRepository roleRepository;

	@BeforeEach
	public void loadDoctor() {
		Doctor doctor = new Doctor(0, "doctor2House", "kot2MaAle", null,
				"Aleksander", "Ziółko", "dr n. md.", "zi2olko@gmail.com" , null, null, null);
		doctorRepository.save(doctor);
	}

	@Test
	public void shouldReturn1Doctor() {
		List<DoctorView> doctors = doctorService.getAllDoctors();
		assertThat(doctors.size()).isEqualTo(1);
	}

	@Test
	public void shouldReturnCorrectDoctor() {
		DoctorView doctor = doctorService.getAllDoctors().get(0);
		assertThat(doctor.getFirstName()).isEqualTo("Aleksander");
		assertThat(doctor.getLastName()).isEqualTo("Ziółko");
		assertThat(doctor.getTitle()).isEqualTo("dr n. md.");
	}

}
