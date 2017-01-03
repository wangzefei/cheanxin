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
public interface UserService {
    public User getUserByUsername(String username);

    public User getUserByMobileNumber(String mobileNumber);

    public User save(User user);

    public Page<User> getUsers(int page, int size);

    public boolean isUsernameExists(String username);

    public boolean isMobileNoExists(String mobileNo);
}
