package cheanxin.service.impl;

import cheanxin.data.PostAuthorityRepository;
import cheanxin.domain.PostAuthority;
import cheanxin.domain.User;
import cheanxin.domain.UserPost;
import cheanxin.service.PostAuthorityService;
import cheanxin.service.UserPostService;
import cheanxin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by 273cn on 16/12/21.
 */
@Service
public class PostAuthorityServiceImpl implements PostAuthorityService {
    @Autowired
    UserPostService userPostService;

    @Autowired
    PostAuthorityRepository postAuthorityRepository;

    @Autowired
    UserService userService;

    @Override
    public List<PostAuthority> getPostAuthorityList(Collection<Long> postIds) {
        return postAuthorityRepository.findByPostIdIn(postIds);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findUserByUsername(username);
        if (user == null)
            throw new UsernameNotFoundException("User " + username + " not found.");

        List<UserPost> userPostList = userPostService.getUserPostList(username);
        if (userPostList.isEmpty()) return user;

        Collection<Long> postIds = new ArrayList<>();
        for (UserPost userPost : userPostList)
            postIds.add(userPost.getPostId());

        List<PostAuthority> postAuthorityList = getPostAuthorityList(postIds);
        List<GrantedAuthority> authorityList = new ArrayList<>();
        for (PostAuthority postAuthority : postAuthorityList) {
            authorityList.add(new SimpleGrantedAuthority(postAuthority.getAuthority()));
        }
        user.setAuthorities(authorityList);
        return user;
    }
}
