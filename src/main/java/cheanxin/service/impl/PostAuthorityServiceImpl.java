package cheanxin.service.impl;

import cheanxin.data.PostAuthorityRepository;
import cheanxin.domain.DeptCity;
import cheanxin.domain.PostAuthority;
import cheanxin.domain.User;
import cheanxin.domain.UserPost;
import cheanxin.service.DeptCityService;
import cheanxin.service.PostAuthorityService;
import cheanxin.service.UserPostService;
import cheanxin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;

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
    DeptCityService deptCityService;

    @Autowired
    UserService userService;

    @Override
    public List<PostAuthority> list(Collection<Long> postIds) {
        if (postIds == null || postIds.isEmpty()) {
            return new ArrayList<>();
        }
        return postAuthorityRepository.findByPostIdIn(postIds);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.getByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User " + username + " not found.");
        }

        List<UserPost> userPostList = userPostService.list(username);
        if (userPostList.isEmpty()) {
            return user;
        }

        List<DeptCity> deptCityList = deptCityService.list(user.getDeptId());
        Set<Long> cityIds = new HashSet<>();
        for (DeptCity deptCity : deptCityList) {
            cityIds.add(deptCity.getCityId());
        }
        user.setCityIds(cityIds);

        Collection<Long> postIds = new ArrayList<>();
        for (UserPost userPost : userPostList)
            postIds.add(userPost.getPostId());

        List<PostAuthority> postAuthorityList = list(postIds);
        List<GrantedAuthority> authorityList = new ArrayList<>();
        for (PostAuthority postAuthority : postAuthorityList) {
            authorityList.add(new SimpleGrantedAuthority(postAuthority.getAuthority()));
        }
        user.setAuthorities(authorityList);
        return user;
    }
}
