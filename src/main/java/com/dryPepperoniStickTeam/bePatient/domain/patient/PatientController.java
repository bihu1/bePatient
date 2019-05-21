package com.dryPepperoniStickTeam.bePatient.domain.patient;

import com.dryPepperoniStickTeam.bePatient.config.security.SecurityUserDetails;
import com.dryPepperoniStickTeam.bePatient.domain.patient.http.model.PatientDetails;
import com.dryPepperoniStickTeam.bePatient.domain.patient.http.model.PatientView;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Authorization;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.xhtmlrenderer.pdf.ITextRenderer;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.List;

@RestController
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PatientController {

    private final PatientService patientService;
    private final TemplateEngine templateEngine;

    @GetMapping("api/patients")
    @ApiOperation(value="Get all patients", authorizations = {@Authorization("Bearer <oAuth2>")})
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
    })
    @ResponseStatus(code = HttpStatus.OK)
    @Secured("ROLE_ADMIN")
    public List<PatientView> getAllPatients(){
        return patientService.getAllPatients();
    }

    @PostMapping("patients/registration")
    @ApiOperation(value="Register new patient")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Created patient"),
            @ApiResponse(code = 400, message = "Request body is not correct")
    })
    @ResponseStatus(code = HttpStatus.CREATED)
    public void register(@RequestBody @Valid PatientDetails patientDetails){
        patientService.register(patientDetails);
    }

    @PostMapping("api/patient/message")
    @ApiOperation(value="Send message to Reception, it figure out current logged user id", authorizations = {@Authorization("Bearer <oAuth2>")})
    @ApiResponses({
            @ApiResponse(code = 200, message = "Send message"),
            @ApiResponse(code = 400, message = "Request body is not correct")
    })
    @ResponseStatus(code = HttpStatus.CREATED)
    @Secured("ROLE_PATIENT")
    public void sendMailToReception(@ApiIgnore @AuthenticationPrincipal SecurityUserDetails userDetails, @RequestBody String message){
        patientService.sendMailToReception(String.valueOf(userDetails.getId()), message);
    }

    @GetMapping("patients/pdf")
    @ApiOperation(value="Get pdf file")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
    })
    @ResponseStatus(code = HttpStatus.CREATED)
    @SneakyThrows
    public ResponseEntity generatePdf(){
        Context context = new Context();
        context.setVariable("name", "Thomas");
        String html = templateEngine.process("data", context);

        ByteArrayOutputStream  outputStream = new ByteArrayOutputStream();
        ITextRenderer renderer = new ITextRenderer();
        renderer.setDocumentFromString(html);
        renderer.layout();
        renderer.createPDF(outputStream);
        InputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());

        return ResponseEntity
                .ok()
                .header("Content-Disposition", "attachment; filename=dane_osobowe.pdf")
                .contentType(new MediaType("application", "pdf", Charset.forName("utf-8")))
                .body(new InputStreamResource(inputStream));
    }

}
