package cheanxin.service;

import cheanxin.domain.UserPost;

import java.util.List;

/**
 * Created by 273cn on 16/12/21.
 */
public interface UserPostService {
    List<UserPost> list(String username);
}
