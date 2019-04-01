package com.dryPepperoniStickTeam.bePatient.domain.visit;

import com.dryPepperoniStickTeam.bePatient.common.IdHolder;
import com.dryPepperoniStickTeam.bePatient.domain.visit.http.model.*;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("/api")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class VisitController {

    private final VisitService visitService;

    @GetMapping("/doctor/{doctorId}/visits")
    @ApiOperation(value = "Get all available doctor's visits")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
    })
    @ResponseStatus(HttpStatus.OK)
    public List<VisitView> getAllAvailableDoctorsVisits(@PathVariable long doctorId){
        return visitService.getAllAvailableDoctorsVisits(doctorId);
    }

    @PostMapping("/doctor/{doctorId}/visits")
    @ApiOperation(value = "Get all available doctor's visits")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 200, message = "Bad request"),
            @ApiResponse(code = 404, message = "Doctor not found"),
    })
    @ResponseStatus(HttpStatus.OK)
    public void addDoctorAvailableVisit(@PathVariable long doctorId, @RequestBody VisitDetails visitDetails){
        visitService.addDoctorAvailableVisit(doctorId, visitDetails);
    }

    @GetMapping("/doctor/{doctorId}/visits/{visitId}")
    @ApiOperation(value = "Reserve visit for patient")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
    })
    @ResponseStatus(HttpStatus.OK)
    public void reserveVisitByPanelist(
            @PathVariable long doctorId,
            @PathVariable long visitId,
            @RequestBody IdHolder patientIdHolder
    ){
        visitService.reserveVisitByPatient(doctorId, visitId, patientIdHolder.getId());
    }

    @GetMapping("/patient/{patientId}/visits")
    @ApiOperation(value = "Get all patient's visits")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
    })
    @ResponseStatus(HttpStatus.OK)
    public List<ReservedVisitView> getPatientsVisits(@PathVariable long patientId){
        return visitService.getAllPatientsVisits(patientId);
    }

    @PostMapping("/visits/{visitId}/assign")
    @ApiOperation(value = "Assign diseases and medical services to visit")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 404, message = "Not found"),
    })
    @ResponseStatus(HttpStatus.OK)
    public void assignDiseaseAndServicesToVisit(@PathVariable long visitId, @RequestBody DiseaseAndServicesIdsHolder diseaseAndServicesIdsHolder){
         visitService.assignDiseaseAndMedicalServices(visitId, diseaseAndServicesIdsHolder);
    }

    @PutMapping("/visits/{visitId}")
    @ApiOperation(value = "Assign diseases and medical services to visit")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 404, message = "Not found"),
    })
    @ResponseStatus(HttpStatus.OK)
    public void changeVisitStatus(@PathVariable long visitId ,@RequestParam VisitStatus status){
        visitService.changeVisitStatus(visitId, status);
    }
}
