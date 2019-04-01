package com.dryPepperoniStickTeam.bePatient.domain.patient;

import com.dryPepperoniStickTeam.bePatient.domain.patient.http.model.PatientDetails;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Authorization;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/patient")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PatientController {

    private final PatientService patientService;

    @PostMapping("registration")
    @ApiOperation(value="Register new patient")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Created patient"),
            @ApiResponse(code = 400, message = "Request body is not correct")
    })
    @ResponseStatus(code = HttpStatus.CREATED)
    public void register(@RequestBody @Valid PatientDetails patientDetails){
        patientService.register(patientDetails);
    }
}
