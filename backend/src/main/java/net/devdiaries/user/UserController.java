package net.devdiaries.user;

import net.devdiaries.user.model.Credentials;
import net.devdiaries.user.model.User;
import net.devdiaries.user.model.UserCredentials;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/users")
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public User register(@RequestBody Credentials credentials) {
        UserCredentials userCredentials = new UserCredentials(credentials.getUsername(), credentials.getPassword(), "ROLE_USER");
        User user = new User(userCredentials);
        return userService.register(user);
    }
}