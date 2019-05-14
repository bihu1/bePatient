package com.dryPepperoniStickTeam.bePatient.domain.patient.model;

import com.dryPepperoniStickTeam.bePatient.domain.user.model.User;
import com.dryPepperoniStickTeam.bePatient.domain.user.model.UserRole;
import com.dryPepperoniStickTeam.bePatient.domain.visit.Visit;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@FieldDefaults(level= AccessLevel.PRIVATE)
public class Patient extends User {

    String firstName;
    String lastName;
    String email;
    @OneToMany(cascade = CascadeType.ALL)
    //@JoinColumn ditto
    List<Visit> visits;

    public Patient(long id, String username, String password, List<UserRole> roles, String email, List<Visit> visits, String firstName, String lastName) {
        super(id, username, password, roles);
        this.email = email;
        this.visits = visits;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public void addVisit(Visit visit){
        if(visits == null){
            visits = new ArrayList<>();
        }
        visits.add(visit);
    }
}
