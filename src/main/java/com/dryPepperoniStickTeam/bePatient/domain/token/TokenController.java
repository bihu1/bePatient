package com.dryPepperoniStickTeam.bePatient.domain.token;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
public class TokenController {

    //@CrossOrigin(origins = "http://localhost:4000")
    @GetMapping("token")
    @ApiOperation(value = "Shit")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
    })
    @ResponseStatus(HttpStatus.OK)
    @SneakyThrows
    public void postAccessToken(
            @RequestParam String username,
            @RequestParam String password,
            HttpServletResponse response
     ){
        response.sendRedirect("oauth/token?username="+username+"&password="+password+"&grant_type=password");
    }
}
