package cheanxin.data;

import cheanxin.domain.UserAuthority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by 273cn on 16/12/21.
 */
@Repository
public interface UserAuthorityRepository extends JpaRepository<UserAuthority, Long> {
    List<UserAuthority> findByUsername(String username);
}
