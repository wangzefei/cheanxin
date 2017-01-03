package cheanxin.service.impl;

import cheanxin.data.UserPostRepository;
import cheanxin.domain.UserPost;
import cheanxin.service.UserPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 273cn on 16/12/21.
 */
@Service
public class UserPostServiceImpl implements UserPostService {
    @Autowired
    UserPostRepository userPostRepository;

    @Override
    public List<UserPost> getUserPostList(String username) {
        return userPostRepository.findByUsername(username);
    }
}
