package com.dryPepperoniStickTeam.bePatient.domain.doctor.http.model;

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
public class DoctorView {
    String firstName;
    String lastName;
    String title;
    List<String> professions;
    List<String> services;
}
