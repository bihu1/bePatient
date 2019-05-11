package com.dryPepperoniStickTeam.bePatient.domain.visit;

import com.dryPepperoniStickTeam.bePatient.common.IdHolder;
import com.dryPepperoniStickTeam.bePatient.domain.visit.http.model.*;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Authorization;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class VisitController {

    private final VisitService visitService;

    @GetMapping("/doctors/{doctorId}/visits")
    @ApiOperation(value = "Get all available doctor's visits", authorizations = {@Authorization("Bearer <oAuth2>")})
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
    })
    @ResponseStatus(HttpStatus.OK)
    public List<VisitView> getAllAvailableDoctorsVisits(@PathVariable long doctorId, @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Optional<LocalDate> date){
        return visitService.getAllAvailableDoctorsVisits(doctorId, date);
    }

    @GetMapping("/visits")
    @ApiOperation(value = "Get all visits", authorizations = {@Authorization("Bearer <oAuth2>")})
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
    })
    @ResponseStatus(HttpStatus.OK)
    public List<VisitView> getAllVisits(){
        return visitService.getAllVisits();
    }

    @PostMapping("/doctors/{doctorId}/visits")
    @ApiOperation(value = "Add available visit to given doctor", authorizations = {@Authorization("Bearer <oAuth2>")})
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 200, message = "Bad request"),
            @ApiResponse(code = 404, message = "Doctor not found"),
    })
    @ResponseStatus(HttpStatus.OK)
    @Secured("ROLE_ADMIN")
    public void addDoctorAvailableVisit(@PathVariable long doctorId, @RequestBody VisitDetails visitDetails){
        visitService.addDoctorAvailableVisit(doctorId, visitDetails);
    }

    @PostMapping("/doctors/{doctorId}/visits/{visitId}")
    @ApiOperation(value = "Reserve visit for patient", authorizations = {@Authorization("Bearer <oAuth2>")})
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
    })
    @ResponseStatus(HttpStatus.OK)
    @Secured("ROLE_PATIENT")
    public void reserveVisitByPanelist(
            @PathVariable long doctorId,
            @PathVariable long visitId,
            @RequestBody IdHolder patientIdHolder
    ){
        visitService.reserveVisitByPatient(doctorId, visitId, patientIdHolder.getId());
    }

    @GetMapping("/patients/{patientId}/visits")
    @ApiOperation(value = "Get all patient's visits", authorizations = {@Authorization("Bearer <oAuth2>")})
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
    })
    @ResponseStatus(HttpStatus.OK)
    public List<ReservedVisitView> getPatientsVisits(@PathVariable long patientId){
        return visitService.getAllPatientsVisits(patientId);
    }

    @PostMapping("/visits/{visitId}/assign")
    @ApiOperation(value = "Assign diseases and medical services to visit", authorizations = {@Authorization("Bearer <oAuth2>")})
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 404, message = "Not found"),
    })
    @ResponseStatus(HttpStatus.OK)
    @Secured({ "ROLE_DOCTOR", "ROLE_ADMIN" })
    public void assignDiseaseAndServicesToVisit(@PathVariable long visitId, @RequestBody DiseaseAndServicesIdsHolder diseaseAndServicesIdsHolder){
         visitService.assignDiseaseAndMedicalServices(visitId, diseaseAndServicesIdsHolder);
    }

    @PutMapping("/visits/{visitId}")
    @ApiOperation(value = "Change visit status", authorizations = {@Authorization("Bearer <oAuth2>")})
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 404, message = "Not found"),
    })
    @ResponseStatus(HttpStatus.OK)
    @Secured({ "ROLE_DOCTOR", "ROLE_ADMIN" })
    public void changeVisitStatus(@PathVariable long visitId, @RequestParam VisitStatus status){
        visitService.changeVisitStatus(visitId, status);
    }
}
