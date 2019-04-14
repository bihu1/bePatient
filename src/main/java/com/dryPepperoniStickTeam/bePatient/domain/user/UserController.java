package com.dryPepperoniStickTeam.bePatient.domain.user;

import com.dryPepperoniStickTeam.bePatient.domain.user.http.model.ChangePasswordRequest;
import com.dryPepperoniStickTeam.bePatient.domain.user.http.model.ChangeUsernameRequest;
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
@RequestMapping("/api/users")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UserController {

    private final UserService userService;

    @PutMapping("/password/changing")
    @ApiOperation(value = "Get all doctors", authorizations = {@Authorization("Bearer <oAuth2>")})
    @ApiResponses({
            @ApiResponse(code = 204, message = "Updated"),
            @ApiResponse(code = 400, message = "Bad old password")
    })
    @ResponseStatus(HttpStatus.OK)
    public void changePassword(@RequestBody @Valid ChangePasswordRequest changePasswordRequest) {
        userService.changePassword(changePasswordRequest);
    }

    @PutMapping("/username/changing")
    @ApiOperation(value = "Add new doctor", authorizations = {@Authorization("Bearer <oAuth2>")} )
    @ApiResponses({
            @ApiResponse(code = 204, message = "OK"),
            @ApiResponse(code = 400, message = "Bad old password")
    })
    @ResponseStatus(HttpStatus.OK)
    public void changeUsername(@RequestBody @Valid ChangeUsernameRequest changeUsernameRequest) {
        userService.changeUsername(changeUsernameRequest);
    }
}
