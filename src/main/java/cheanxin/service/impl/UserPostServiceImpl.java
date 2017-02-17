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
        List<UserPost> userPostList = userPostRepository.findByUsernameIn(usernames);
        Map<Long, Post> postMap = postService.listPostMap(true);
        Map<String, Collection<Post>> userPostMap = new HashMap<>();
        for (UserPost userPost : userPostList) {
            Collection<Post> postList = userPostMap.get(userPost.getUsername());
            if (postList == null)
                postList = new ArrayList<>();
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
