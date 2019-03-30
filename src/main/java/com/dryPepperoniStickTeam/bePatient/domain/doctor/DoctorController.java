package com.dryPepperoniStickTeam.bePatient.domain.doctor;

import com.dryPepperoniStickTeam.bePatient.domain.user.User;
import com.dryPepperoniStickTeam.bePatient.domain.user.UserRepository;
import com.dryPepperoniStickTeam.bePatient.domain.user.UserRole;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Authorization;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.util.Arrays.asList;

@RestController
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class DoctorController {

    private final DoctorRepository doctorRepository;
    private final UserRepository userRepository;

    @GetMapping("test")
    @ApiOperation(value = "Shit")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
    })
    @ResponseStatus(HttpStatus.OK)
    public List<String> getTest() {
        //doctorRepository.save(new Doctor(1, "a"));
        return asList("a","b","c");
    }

    @GetMapping("/api/auth/test")
    @ApiOperation(value = "Shit", authorizations = {@Authorization("Bearer <oAuth2>")} )
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
    })
    @ResponseStatus(HttpStatus.OK)
    public List<String> getTest2() {
        return asList("a","b","c");
    }

    @GetMapping("registry")
    @ApiOperation(value = "Shit")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
    })
    @ResponseStatus(HttpStatus.OK)
    public void getTest2a(
            @RequestParam String username,
            @RequestParam String password
    ){
        //userRepository.save(new User(0,username,password,asList(new UserRole("user"))));
    }
}
