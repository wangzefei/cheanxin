package cheanxin.service;

import cheanxin.domain.User;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * Created by 273cn on 16/12/21.
 */
public interface UserService {
    User getByUsername(String username);

    User getByMobileNumber(String mobileNumber);

    User save(User user);

    Page<User> list(long deptId, String realName, String mobileNumber, String email, int status, int page, int size);

    List<User> list(long deptId, long postId, boolean enabled);

    boolean isUsernameExists(String username);

    boolean isMobileNoExists(String mobileNo);
}
