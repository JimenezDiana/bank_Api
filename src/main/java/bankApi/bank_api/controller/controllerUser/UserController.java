package bankApi.bank_api.controller.controllerUser;

import bankApi.bank_api.entities.users.User;
import bankApi.bank_api.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    UserRepository userRepository;

    @GetMapping("/users")
    public List<User> findAll(@AuthenticationPrincipal UserDetails userDetails) {
        System.out.println(userDetails.getUsername());

        return userRepository.findAll();
    }
}

