package bankApi.bank_api.services.userService;
import bankApi.bank_api.repository.user.UserRepository;
import bankApi.bank_api.security.CustomUserDetails;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        if (!userRepository.findByUsername(username).isPresent()) {
            throw new UsernameNotFoundException("User does not exist");
        }
        //return new CustomUserDetails(userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User does not exist")));
        return new CustomUserDetails(userRepository.findByUsername(username).get());
    }
}
