package com.dryPepperoniStickTeam.bePatient.domain.receptionist;

import com.dryPepperoniStickTeam.bePatient.domain.user.UserRepository;
import com.dryPepperoniStickTeam.bePatient.domain.user.model.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import static java.util.Collections.singletonList;

@Component
public class ReceptionistInitializer {

    @Autowired
    UserRepository userRepository;

    @Bean
    public void initializeFirstReceptionist(){
        Receptionist receptionist = new Receptionist(0, "Basia", "WasCoNieJednaPizdaTrzasl", singletonList(new UserRole("admin")));
        if(!userRepository.existsByUsername("Basia")){
            userRepository.save(receptionist);
        }
    }
}
