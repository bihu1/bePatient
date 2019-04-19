package com.dryPepperoniStickTeam.bePatient.domain.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserView {
    long id;
    List<String> roles;
}
