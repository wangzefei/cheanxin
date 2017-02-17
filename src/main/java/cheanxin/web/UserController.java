package cheanxin.web;

import cheanxin.constant.LogicConstants;
import cheanxin.domain.User;
import cheanxin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.util.Assert;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

/**
 * Created by 273cn on 16/12/14.
 */
@RestController
@RequestMapping("/users")
public class UserController extends BaseController {
    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<User> save(
            @Valid @RequestBody User user,
            Errors errors,
            UriComponentsBuilder ucb) {
        String errorMessage = errors.hasErrors() ? errors.getAllErrors().get(0).getDefaultMessage() : null;
        Assert.isNull(errorMessage, errorMessage);
//        Assert.isTrue(user.getPassword().length() <= 20, "password len greater than 20");
        Assert.isTrue(!userService.isUsernameExists(user.getUsername()), "Username already exists.");
        Assert.isTrue(!userService.isMobileNoExists(user.getMobileNumber()), "Mobile number already exists.");

        HttpHeaders httpHeaders = new HttpHeaders();
        URI locationUri = ucb.path("/users/")
                .path(String.valueOf(user.getUsername()))
                .build()
                .toUri();
        httpHeaders.setLocation(locationUri);
        user.setCreatedTime(System.currentTimeMillis() / 1000);
        User savedUser = userService.save(user);
        return new ResponseEntity<>(savedUser, httpHeaders, HttpStatus.CREATED);
    }

    @RequestMapping(method=RequestMethod.GET)
    public Page<User> list(
            @RequestParam(value = "deptId", defaultValue = "-1") long deptId,
            @RequestParam(value = "realName", defaultValue = "") String realName,
            @RequestParam(value = "mobileNumber", defaultValue = "") String mobileNumber,
            @RequestParam(value = "email", defaultValue = "") String email,
            @RequestParam(value = "status", defaultValue = "-1") int status,
            @RequestParam(value = "page", defaultValue = LogicConstants.DEFAULT_PAGE) int page,
            @RequestParam(value = "size", defaultValue = LogicConstants.DEFAULT_SIZE) int size) {
        return userService.list(deptId, realName, mobileNumber, email, status, page, size);
    }

    @RequestMapping(value="/all", method=RequestMethod.GET)
    public List<User> list(
            @RequestParam(value = "deptId", defaultValue = "-1") long deptId,
            @RequestParam(value = "postId", defaultValue = "-1") long postId,
            @RequestParam(value = "enabled", defaultValue = "1") boolean enabled) {
        return userService.list(deptId, postId, enabled);
    }

    @RequestMapping(value="/me", method=RequestMethod.GET)
    public ResponseEntity<User> get(@AuthenticationPrincipal User user) {
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @RequestMapping(value = "/{username}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<User> get(@PathVariable String username) {
        User user = userService.getByUsername(username);
        Assert.notNull(user, "User not found.");
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
