package cheanxin.service.impl;

import cheanxin.data.UserPostRepository;
import cheanxin.domain.Post;
import cheanxin.domain.UserPost;
import cheanxin.service.PostService;
import cheanxin.service.UserPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by 273cn on 16/12/21.
 */
@Service
public class UserPostServiceImpl implements UserPostService {
    @Autowired
    UserPostRepository userPostRepository;

    @Autowired
    PostService postService;

    @Override
    public List<UserPost> list(String username) {
        return userPostRepository.findByUsername(username);
    }

    @Override
    public Map<String, Collection<Post>> listUserPostListMap(Collection<String> usernames) {
        Map<String, Collection<Post>> userPostMap = new HashMap<>();
        List<UserPost> userPostList = userPostRepository.findByUsernameIn(usernames);
        if (userPostList == null) {
            return userPostMap;
        }
        Map<Long, Post> postMap = postService.listPostMap(true);
        if (postMap == null) {
            return userPostMap;
        }
        for (UserPost userPost : userPostList) {
            if (userPost == null) {
                continue;
            }
            Collection<Post> postList = userPostMap.get(userPost.getUsername());
            if (postList == null) {
                postList = new ArrayList<>();
            }
            postList.add(postMap.get(userPost.getPostId()));
            userPostMap.put(userPost.getUsername(), postList);
        }
        return userPostMap;
    }

    @Override
    public List<UserPost> list(Collection<String> usernames, long postId) {
        return userPostRepository.findByUsernameInAndPostId(usernames, postId);
    }
}
