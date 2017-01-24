package cheanxin.service;

import cheanxin.domain.Dept;

import java.util.List;
import java.util.Set;

/**
 * Created by 273cn on 16/12/21.
 */
public interface DeptService {
    Dept save(Dept unsavedDept);
    Dept save(Dept unsavedDept, Dept parentDept);
    void delete(long id);
    Dept findOne(long id);
    List<Dept> getDepts(Integer level, boolean enabled);
    List<Dept> getDepts(Long parentDeptId, boolean enabled);
    List<Dept> getDepts(Set<Long> deptIds);
    boolean isExists(long id);
}
