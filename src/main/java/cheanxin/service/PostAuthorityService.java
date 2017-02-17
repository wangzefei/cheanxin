package cheanxin.service;

import cheanxin.domain.PostAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Collection;
import java.util.List;

/**
 * Created by 273cn on 16/12/21.
 */
public interface PostAuthorityService extends UserDetailsService {
    List<PostAuthority> list(Collection<Long> postIds);
}
