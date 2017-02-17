package cheanxin.service;

import cheanxin.domain.Post;
import cheanxin.domain.UserPost;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Created by 273cn on 16/12/21.
 */
public interface UserPostService {
    List<UserPost> list(String username);
    Map<String, Collection<Post>> listUserPostListMap(Collection<String> usernames);
    List<UserPost> list(Collection<String> usernames, long postId);
}
