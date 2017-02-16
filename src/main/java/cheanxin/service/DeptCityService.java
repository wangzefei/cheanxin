package cheanxin.service;

import cheanxin.domain.Dept;
import cheanxin.domain.DeptCity;

import java.util.List;
import java.util.Set;

/**
 * Created by 273cn on 16/12/21.
 */
public interface DeptCityService {
    List<DeptCity> save(Iterable<DeptCity> unsavedDeptCities);
    List<DeptCity> list(long deptId);
}
