package cheanxin.web;

import cheanxin.domain.User;
import cheanxin.exceptions.ErrorResponse;
import cheanxin.exceptions.InvalidArgumentException;
import cheanxin.exceptions.ResourceConflictException;
import cheanxin.exceptions.ResourceNotFoundException;
import cheanxin.global.Constants;
import cheanxin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

/**
 * Created by 273cn on 16/12/14.
 */
@RestController
@RequestMapping("/users")
public class UserController extends BaseController {
    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<User> register(
            @Valid @RequestBody User user,
            Errors errors,
            UriComponentsBuilder ucb) {
        if (errors.hasErrors()) throw new InvalidArgumentException(errors.getAllErrors().get(0));
        if (user.getPassword().length() > 20) {
            throw new InvalidArgumentException(new ErrorResponse(HttpStatus.BAD_REQUEST.getReasonPhrase(), "password len greater than 20"));
        }
        if (userService.isUsernameExists(user.getUsername())) throw new ResourceConflictException(User.class.getSimpleName(), "username", user.getUsername());
        if (userService.isMobileNoExists(user.getMobileNumber())) throw new ResourceConflictException(User.class.getSimpleName(), "mobileNumber", user.getMobileNumber());
        HttpHeaders httpHeaders = new HttpHeaders();
        URI locationUri = ucb.path("/users/")
                .path(String.valueOf(user.getUsername()))
                .build()
                .toUri();
        httpHeaders.setLocation(locationUri);
        User savedUser = userService.save(user);
        return new ResponseEntity<>(savedUser, httpHeaders, HttpStatus.CREATED);
    }

    @RequestMapping(method=RequestMethod.GET)
    public Page<User> allUsers(
            @RequestParam(value = "page", defaultValue = Constants.DEFAULT_PAGE) int page,
            @RequestParam(value = "size", defaultValue = Constants.DEFAULT_SIZE) int size) {
        return userService.getUsers(page, size);
    }

    @RequestMapping(value="/me", method=RequestMethod.GET)
    public ResponseEntity<User> me(@AuthenticationPrincipal User user) {
        return profile(user.getUsername());
    }

    @RequestMapping(value = "/{username}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<User> profile(@PathVariable String username) {
        User user = userService.getUserByUsername(username);
        if (user == null) {
            throw new ResourceNotFoundException(User.class.getSimpleName(), "username", username);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
