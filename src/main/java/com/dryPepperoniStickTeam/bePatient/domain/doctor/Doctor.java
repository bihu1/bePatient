package com.dryPepperoniStickTeam.bePatient.domain.doctor;

import com.dryPepperoniStickTeam.bePatient.domain.service.MedicalService;
import com.dryPepperoniStickTeam.bePatient.domain.user.User;
import com.dryPepperoniStickTeam.bePatient.domain.visit.Visit;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true, exclude = "visits")
@EqualsAndHashCode(callSuper = true, exclude = "visits")
@FieldDefaults(level= AccessLevel.PRIVATE)
public class Doctor extends User {

    String firstName;
    String lastName;
    String title;
    String email;
    @ElementCollection
    List<String> professions;
    @OneToMany
    //@JoinColumn check if it is needed
    List<Visit> visits;
    @OneToMany
    List<MedicalService> medicalServices;

    public void addVisit(Visit visit){
        if(visits == null){
            visits = new ArrayList<>();
        }
        visits.add(visit);
    }
}
