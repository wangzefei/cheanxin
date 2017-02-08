package cheanxin.data;

import cheanxin.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by 273cn on 16/12/14.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long>, UserDao {
    User findByUsernameIgnoringCase(String username);

    User findByMobileNumber(String mobileNumber);
    Long countAll();
}
