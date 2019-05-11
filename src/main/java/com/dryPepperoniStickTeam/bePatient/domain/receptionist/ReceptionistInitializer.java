package com.dryPepperoniStickTeam.bePatient.domain.receptionist;

import com.dryPepperoniStickTeam.bePatient.domain.user.RoleRepository;
import com.dryPepperoniStickTeam.bePatient.domain.user.UserRepository;
import com.dryPepperoniStickTeam.bePatient.domain.user.model.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;

@Component
public class ReceptionistInitializer {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    private void roleInitializer(){
        if(roleRepository.findAll().size() == 0){
            roleRepository.saveAll(asList(
                    new UserRole(0, "ROLE_ADMIN"),
                    new UserRole(0, "ROLE_DOCTOR"),
                    new UserRole(0, "ROLE_PATIENT"))
            );
        }
    }

    @Bean
    public void initializeFirstReceptionist(){
        roleInitializer();
        Receptionist receptionist = new Receptionist(
                0,
                "Basia",
                new BCryptPasswordEncoder().encode("xvcf123"),
                singletonList(roleRepository.findByRole("ROLE_ADMIN"))
        );
        if(!userRepository.existsByUsername("Basia")){
            userRepository.save(receptionist);
        }
    }
}
