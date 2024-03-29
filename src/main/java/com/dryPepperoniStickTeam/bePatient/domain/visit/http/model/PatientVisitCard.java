package com.dryPepperoniStickTeam.bePatient.domain.visit.http.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level= AccessLevel.PRIVATE)
public class PatientVisitCard {

    List<Long> services;
    List<Long> diseases;
    String mainSymptoms;
    String treatment;
    String allergy;
    String addiction;
    String comment;
}
