package cheanxin.service;

import cheanxin.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.stereotype.Service;
import cheanxin.data.UserRepository;
import cheanxin.global.Constants;

/**
 * Created by 273cn on 16/12/21.
 */
@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public User getUserByUsername(String username) {
        return userRepository.findByUsernameIgnoringCase(username);
    }

    public User getUserByMobileNumber(String mobileNumber) {
        return userRepository.findByMobileNumber(mobileNumber);
    }

    public User save(User user) {
        user.setCreatedTime(System.currentTimeMillis() / 1000);
        // encode password.
        user.setPassword(new StandardPasswordEncoder(Constants.PASSWORD_SECRET).encode(user.getPassword()));
        return userRepository.save(user);
    }

    public Page<User> getUsers(int page, int size) {
        return userRepository.findAll(new PageRequest(page, size));
    }

    public boolean isUsernameExists(String username) {
        return getUserByUsername(username) != null;
    }

    public boolean isMobileNoExists(String mobileNo) {
        return getUserByMobileNumber(mobileNo) != null;
    }
}
