package cheanxin.service;

import cheanxin.domain.Dept;

import java.util.Collection;
import java.util.List;

/**
 * Created by 273cn on 16/12/21.
 */
public interface DeptService {
    Dept save(Dept unsavedDept);
    Dept save(Dept unsavedDept, Dept parentDept);
    void remove(long id);
    Dept get(long id);
    List<Dept> list(int level, boolean enabled);
    List<Dept> list(long parentDeptId, boolean enabled);
    List<Dept> list(boolean enabled);
    List<Dept> list(Collection<Long> deptIds);
    boolean isExists(long id);
}
