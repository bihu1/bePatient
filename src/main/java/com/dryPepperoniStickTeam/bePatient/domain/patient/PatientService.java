package com.dryPepperoniStickTeam.bePatient.domain.patient;

import com.dryPepperoniStickTeam.bePatient.domain.patient.http.model.PatientDetails;
import com.dryPepperoniStickTeam.bePatient.domain.patient.model.Patient;
import com.dryPepperoniStickTeam.bePatient.domain.user.UserRole;
import lombok.AllArgsConstructor;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import static java.util.Arrays.asList;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PatientService {

    private final PatientRepository patientRepository;
    private JavaMailSender emailSender;
    private final MapperFacade mapper;

    public void register(PatientDetails patientDetails){
        Patient patient = mapper.map(patientDetails, Patient.class);
        patient.setRoles(asList(new UserRole("user")));
        sendSimpleMessage("jaszczur.p@gmail.com","Aloha!", "password");
        patientRepository.save(patient);
    }

    public void sendSimpleMessage(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        emailSender.send(message);
    }
}
