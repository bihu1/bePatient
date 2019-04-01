package com.dryPepperoniStickTeam.bePatient.domain.doctor;

import com.dryPepperoniStickTeam.bePatient.domain.doctor.http.model.DoctorDetails;
import com.dryPepperoniStickTeam.bePatient.domain.doctor.http.model.DoctorView;
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
@RequestMapping("/api/doctors")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class DoctorController {

    private final DoctorService doctorService;

    @GetMapping
    @ApiOperation(value = "Get all doctors", authorizations = {@Authorization("Bearer <oAuth2>")})
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
    })
    @ResponseStatus(HttpStatus.OK)
    public List<DoctorView> getAllDoctors() {
        return doctorService.getAllDoctors();
    }

    @PostMapping
    @ApiOperation(value = "Add new doctor", authorizations = {@Authorization("Bearer <oAuth2>")} )
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
    })
    @ResponseStatus(HttpStatus.OK)
    public void addDoctor(@RequestBody DoctorDetails doctorDetails) {
        doctorService.addDoctor(doctorDetails);
    }
}
