package cheanxin.service.impl;

import cheanxin.constant.ConfigConstants;
import cheanxin.data.UserRepository;
import cheanxin.domain.Dept;
import cheanxin.domain.Post;
import cheanxin.domain.User;
import cheanxin.domain.UserPost;
import cheanxin.service.DeptService;
import cheanxin.service.PostService;
import cheanxin.service.UserPostService;
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

    @Autowired
    UserPostService userPostService;

    @Autowired
    PostService postService;

    @Override
    public User getByUsername(String username) {
        return userRepository.findByUsernameIgnoringCase(username);
    }

    @Override
    public User getByMobileNumber(String mobileNumber) {
        return userRepository.findByMobileNumber(mobileNumber);
    }

    @Override
    public User save(User user) {
        // encode password.
        user.setPassword(new StandardPasswordEncoder(ConfigConstants.PASSWORD_SECRET).encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public Page<User> list(int page, int size) {
        Page<User> userPage = userRepository.findAll(new PageRequest(page, size));

        // set user dept info.
        Map<Long, Dept> deptMap = new HashMap<>();
        for (User user : userPage) {
            deptMap.put(user.getDeptId(), null);
        }
        List<Dept> deptList = deptService.list(deptMap.keySet());
        for (Dept dept : deptList) {
            deptMap.put(dept.getId(), dept);
        }
        for (User user : userPage) {
            user.setDept(deptMap.get(user.getDeptId()));

            // get user posts
            List<UserPost> userPostList = userPostService.list(user.getUsername());
            Map<Long, Post> postMap = postService.listPostMap(true);
            Collection<Post> posts = new ArrayList<>();
            for (UserPost userPost : userPostList) {
                posts.add(postMap.get(userPost.getPostId()));
            }
            user.setPosts(posts);
        }

        return userPage;
    }

    @Override
    public boolean isUsernameExists(String username) {
        return getByUsername(username) != null;
    }

    @Override
    public boolean isMobileNoExists(String mobileNo) {
        return getByMobileNumber(mobileNo) != null;
    }

    @Override
    public Long countAll() {
        return userRepository.countAll();
    }
}
