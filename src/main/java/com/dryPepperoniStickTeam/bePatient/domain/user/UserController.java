package com.dryPepperoniStickTeam.bePatient.domain.user;

import com.dryPepperoniStickTeam.bePatient.config.security.SecurityUserDetails;
import com.dryPepperoniStickTeam.bePatient.domain.user.http.model.ChangePasswordRequest;
import com.dryPepperoniStickTeam.bePatient.domain.user.http.model.ChangeUsernameRequest;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Authorization;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;

import static java.util.stream.Collectors.toList;

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

    @GetMapping("/me")
    @ApiOperation(value = "Info about me", authorizations = {@Authorization("Bearer <oAuth2>")} )
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
    })
    @ResponseStatus(HttpStatus.OK)
    public UserView currentUser(@ApiIgnore @AuthenticationPrincipal SecurityUserDetails userDetails){
        UserView userView = new UserView();
        userView.setId(userDetails.getId());
        userView.setRoles( userDetails.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(toList())
        );
        return userView;
    }
}
