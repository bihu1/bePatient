package com.dryPepperoniStickTeam.bePatient.domain.visit;

import com.dryPepperoniStickTeam.bePatient.domain.doctor.Doctor;
import com.dryPepperoniStickTeam.bePatient.domain.patient.model.Patient;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level= AccessLevel.PRIVATE)
public class Visit {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;
    LocalDateTime dateFrom;
    LocalDateTime dateTo;
    @ManyToOne
    Doctor doctor;
    @ManyToOne
    Patient patient;
}
