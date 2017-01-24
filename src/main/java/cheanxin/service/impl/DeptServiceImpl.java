package cheanxin.service.impl;

import cheanxin.data.DeptRepository;
import cheanxin.domain.Dept;
import cheanxin.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * Created by 273cn on 16/12/21.
 */
@Service
public class DeptServiceImpl implements DeptService {
    @Autowired
    DeptRepository deptRepository;

    @Override
    public Dept save(Dept unsavedDept, Dept parentDept) {
        Long parentDeptId = 0L;
        if (parentDept != null) {
            if (parentDept.getId() != null)
                parentDeptId = parentDept.getId();
            if (parentDept.getLevel() != null)
                unsavedDept.setLevel(parentDept.getLevel() + 1);
        }

        unsavedDept.setParentDeptId(parentDeptId);
        if (parentDeptId == 0L)
            unsavedDept.setLevel(1);

        return deptRepository.save(unsavedDept);
    }

    @Override
    public List<Dept> getDepts(Integer level, boolean enabled) {
        return deptRepository.findAllByLevelAndEnabled(level, enabled);
    }

    @Override
    public List<Dept> getDepts(Long parentDeptId, boolean enabled) {
        return deptRepository.findAllByParentDeptIdAndEnabled(parentDeptId, enabled);
    }

    @Override
    public List<Dept> getDepts(Set<Long> deptIds) {
        return deptRepository.findAllByIdIn(deptIds);
    }

    @Override
    public Dept save(Dept unsavedDept) {
        return save(unsavedDept, null);
    }

    @Override
    public void delete(long id) {
        deptRepository.delete(id);
    }

    @Override
    public Dept findOne(long id) {
        return deptRepository.findOne(id);
    }

    @Override
    public boolean isExists(long id) {
        return findOne(id) != null;
    }
}
