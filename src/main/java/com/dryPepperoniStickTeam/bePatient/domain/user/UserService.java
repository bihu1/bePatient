package com.dryPepperoniStickTeam.bePatient.domain.user;

import com.dryPepperoniStickTeam.bePatient.config.security.SecurityUserDetails;
import com.dryPepperoniStickTeam.bePatient.domain.user.http.model.ChangePasswordRequest;
import com.dryPepperoniStickTeam.bePatient.domain.user.http.model.ChangeUsernameRequest;
import com.dryPepperoniStickTeam.bePatient.domain.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = getUserByUsername(username);
        return new SecurityUserDetails(user);
    }

    public void changePassword(ChangePasswordRequest changePasswordRequest){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = getUserByUsername(username);
        if(!user.getPassword().equals(changePasswordRequest.getOldPassword())){
            throw new RuntimeException();
        }
        user.setPassword(changePasswordRequest.getNewPassword());
    }

    public void changeUsername(ChangeUsernameRequest changeUsernameRequest){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = getUserByUsername(username);
        if(!user.getPassword().equals(changeUsernameRequest.getPassword())){
            throw new RuntimeException();
        }
        user.setUsername(changeUsernameRequest.getNewUsername());
    }

    private User getUserByUsername(String username){
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("UserName "+username+" not found"));
    }
}
