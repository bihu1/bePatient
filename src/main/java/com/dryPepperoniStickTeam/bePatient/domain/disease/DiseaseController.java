package com.dryPepperoniStickTeam.bePatient.domain.disease;

import com.dryPepperoniStickTeam.bePatient.domain.disease.http.model.DiseaseDetails;
import com.dryPepperoniStickTeam.bePatient.domain.disease.http.model.DiseaseUpdate;
import com.dryPepperoniStickTeam.bePatient.domain.disease.http.model.DiseaseView;
import com.dryPepperoniStickTeam.bePatient.domain.service.MedicalServiceService;
import com.dryPepperoniStickTeam.bePatient.domain.service.http.model.MedicalServiceDetails;
import com.dryPepperoniStickTeam.bePatient.domain.service.http.model.MedicalServiceUpdate;
import com.dryPepperoniStickTeam.bePatient.domain.service.http.model.MedicalServiceView;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Authorization;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class DiseaseController {

    private final DiseaseService diseaseService;

    @GetMapping("/diseases/(diseaseId)")
    @ApiOperation(value="Get disease", authorizations = {@Authorization("Bearer <oAuth2>")})
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Disease not found")
    })
    @ResponseStatus(code = HttpStatus.OK)
    public DiseaseView getDiseases(
            @PathVariable long diseaseId
    ){
       return diseaseService.getDisease(diseaseId);
    }

    @GetMapping("/diseases")
    @ApiOperation(value="Get all diseases", authorizations = {@Authorization("Bearer <oAuth2>")})
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
    })
    @ResponseStatus(code = HttpStatus.OK)
    public List<DiseaseView> getDiseases(){
        return diseaseService.getAllDiseases();
    }

    @PostMapping("/diseases")
    @ApiOperation(value="Add new disease", authorizations = {@Authorization("Bearer <oAuth2>")})
    @ApiResponses({
            @ApiResponse(code = 201, message = "Created disease"),
            @ApiResponse(code = 400, message = "Request body is not correct")
    })
    @ResponseStatus(code = HttpStatus.CREATED)
    public void addDisease(@RequestBody @Valid DiseaseDetails diseaseDetails){
        diseaseService.addDisease(diseaseDetails);
    }

    @PutMapping("/diseases/(diseaseId)")
    @ApiOperation(value="Add new disease", authorizations = {@Authorization("Bearer <oAuth2>")})
    @ApiResponses({
            @ApiResponse(code = 204, message = "Updated disease"),
            @ApiResponse(code = 400, message = "Request body is not correct"),
            @ApiResponse(code = 404, message = "Not found")
    })
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void updateDisease(
            @PathVariable long diseaseId,
            @RequestBody @Valid DiseaseUpdate diseaseUpdate
    ){
        diseaseService.updateDisease(diseaseId, diseaseUpdate);
    }

    @DeleteMapping("/disease/(diseaseId)")
    @ApiOperation(value="Delete disease", authorizations = {@Authorization("Bearer <oAuth2>")})
    @ApiResponses({
            @ApiResponse(code = 204, message = "Delete disease"),
            @ApiResponse(code = 404, message = "Not found")
    })
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteDisease(
            @PathVariable long diseaseId
    ){
        diseaseService.deleteDisease(diseaseId);
    }
}
