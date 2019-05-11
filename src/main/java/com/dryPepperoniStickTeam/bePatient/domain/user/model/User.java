package com.dryPepperoniStickTeam.bePatient.domain.user.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

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
    @ManyToMany
    List<UserRole> roles;

    public void setPassword(String password){
       this.password = new BCryptPasswordEncoder().encode(password);
    }
}
