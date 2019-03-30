package com.dryPepperoniStickTeam.bePatient.domain.user;

import com.dryPepperoniStickTeam.bePatient.domain.user.UserRole;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.List;

@Data
@Entity(name="users")
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level= AccessLevel.PRIVATE)
@Inheritance(strategy=InheritanceType.JOINED)
public abstract class User {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    long id;
    String username;
    String password;
    @ElementCollection
    List<UserRole> roles;
}
