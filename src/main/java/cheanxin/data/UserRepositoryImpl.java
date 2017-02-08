package cheanxin.data;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by 273cn on 16/12/21.
 */
public class UserRepositoryImpl implements UserDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Long countAll() {
        final String sql = "SELECT COUNT(1) FROM user";
        Long result = (Long) entityManager.createQuery(sql).getSingleResult();
        return result == null ? -1L : result;
    }
}
