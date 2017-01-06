package cheanxin.service.impl;

import cheanxin.data.UserRepository;
import cheanxin.domain.User;
import cheanxin.global.Constants;
import cheanxin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Created by 273cn on 16/12/21.
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Override
    public User findUserByUsername(String username) {
        return userRepository.findByUsernameIgnoringCase(username);
    }

    @Override
    public User findUserByMobileNumber(String mobileNumber) {
        return userRepository.findByMobileNumber(mobileNumber);
    }

    @Override
    public User save(User user) {
        user.setCreatedTime(System.currentTimeMillis() / 1000);
        // encode password.
        user.setPassword(new StandardPasswordEncoder(Constants.PASSWORD_SECRET).encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public Page<User> getUsers(int page, int size) {
        return userRepository.findAll(new PageRequest(page, size));
    }

    @Override
    public boolean isUsernameExists(String username) {
        return findUserByUsername(username) != null;
    }

    @Override
    public boolean isMobileNoExists(String mobileNo) {
        return findUserByMobileNumber(mobileNo) != null;
    }
}
