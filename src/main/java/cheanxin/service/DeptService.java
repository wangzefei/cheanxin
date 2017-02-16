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
    void remove(long id);
    Dept get(long id);
    List<Dept> list(Integer level, boolean enabled);
    List<Dept> list(Long parentDeptId, boolean enabled);
    List<Dept> list(boolean enabled);
    List<Dept> list(Set<Long> deptIds);
    boolean isExists(long id);
}
