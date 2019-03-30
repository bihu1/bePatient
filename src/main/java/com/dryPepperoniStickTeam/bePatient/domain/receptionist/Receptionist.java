package com.dryPepperoniStickTeam.bePatient.domain.receptionist;

import com.dryPepperoniStickTeam.bePatient.domain.user.User;
import lombok.*;

import javax.persistence.Entity;

@Data
@Entity
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Receptionist extends User {
}
