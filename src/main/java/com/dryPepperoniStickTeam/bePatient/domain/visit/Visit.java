package com.dryPepperoniStickTeam.bePatient.domain.visit;

import com.dryPepperoniStickTeam.bePatient.domain.doctor.Doctor;
import com.dryPepperoniStickTeam.bePatient.domain.patient.model.Patient;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
public class Visit {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    long id;
    LocalDateTime dateFrom;
    LocalDateTime dateTo;
    @ManyToOne
    Doctor doctor;
    @ManyToOne
    Patient patient;
}
