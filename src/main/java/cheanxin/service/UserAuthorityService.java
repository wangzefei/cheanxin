package cheanxin.service;

import cheanxin.data.UserAuthorityRepository;
import cheanxin.domain.User;
import cheanxin.domain.UserAuthority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 273cn on 16/12/21.
 */
@Service
public class UserAuthorityService implements UserDetailsService {
    @Autowired
    UserAuthorityRepository userAuthorityRepository;

    @Autowired
    UserService userService;

    public List<UserAuthority> getUserAuthorityList(String username) {
        return userAuthorityRepository.findByUsername(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.getUserByUsername(username);
        if (user == null)
            throw new UsernameNotFoundException("User " + username + " not found.");

        List<UserAuthority> userAuthorityList = getUserAuthorityList(username);
        List<GrantedAuthority> authorityList = new ArrayList<>();
        for (UserAuthority userAuthority : userAuthorityList) {
            authorityList.add(new SimpleGrantedAuthority(userAuthority.getRoleName()));
        }
        user.setAuthorities(authorityList);
        return user;
    }
}
