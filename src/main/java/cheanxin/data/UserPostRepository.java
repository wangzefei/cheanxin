package cheanxin.data;

import cheanxin.domain.PostAuthority;
import cheanxin.domain.UserPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by 273cn on 16/12/21.
 */
@Repository
public interface UserPostRepository extends JpaRepository<UserPost, Long> {
    List<UserPost> findByUsername(String username);
}
