package com.dryPepperoniStickTeam.bePatient.domain.receptionist;

import com.dryPepperoniStickTeam.bePatient.domain.user.model.User;
import com.dryPepperoniStickTeam.bePatient.domain.user.model.UserRole;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Receptionist extends User {
    public Receptionist(long id, String username, String password, List<UserRole> roles) {
        super(id, username, password, roles);
    }
}
