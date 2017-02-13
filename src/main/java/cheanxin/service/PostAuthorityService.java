package cheanxin.service;

import cheanxin.data.PostAuthorityRepository;
import cheanxin.domain.User;
import cheanxin.domain.PostAuthority;
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
public interface PostAuthorityService extends UserDetailsService {
    public List<PostAuthority> list(Collection<Long> postIds);
}
