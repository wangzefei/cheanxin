package cheanxin.service.impl;

import cheanxin.data.UserRepository;
import cheanxin.domain.Dept;
import cheanxin.domain.User;
import cheanxin.global.Constants;
import cheanxin.service.DeptService;
import cheanxin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by 273cn on 16/12/21.
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    DeptService deptService;

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
        // encode password.
        user.setPassword(new StandardPasswordEncoder(Constants.PASSWORD_SECRET).encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public Page<User> getUsers(int page, int size) {
        Page<User> userPage = userRepository.findAll(new PageRequest(page, size));

        // set user dept info.
        Map<Long, Dept> deptMap = new HashMap<>();
        for (User user : userPage) {
            deptMap.put(user.getDeptId(), null);
        }
        List<Dept> deptList = deptService.getDepts(deptMap.keySet());
        for (Dept dept : deptList) {
            deptMap.put(dept.getId(), dept);
        }
        for (User user : userPage) {
            user.setDept(deptMap.get(user.getDeptId()));
        }

        return userPage;
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
