package cheanxin.data;

import cheanxin.domain.DeptCity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by 273cn on 16/12/14.
 */
@Repository
public interface DeptCityRepository extends JpaRepository<DeptCity, Long> {
    List<DeptCity> findAllByDeptId(long deptId);
}
