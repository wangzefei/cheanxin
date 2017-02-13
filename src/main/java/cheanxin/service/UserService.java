package cheanxin.service;

import cheanxin.domain.User;
import org.springframework.data.domain.Page;

/**
 * Created by 273cn on 16/12/21.
 */
public interface UserService {
    User getByUsername(String username);

    User getByMobileNumber(String mobileNumber);

    User save(User user);

    Page<User> list(int page, int size);

    boolean isUsernameExists(String username);

    boolean isMobileNoExists(String mobileNo);

    Long countAll();
}
