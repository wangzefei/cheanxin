package cheanxin.service.impl;

import cheanxin.data.DeptCityRepository;
import cheanxin.domain.DeptCity;
import cheanxin.service.DeptCityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 273cn on 16/12/21.
 */
@Service
public class DeptCityServiceImpl implements DeptCityService {
    @Autowired
    DeptCityRepository deptCityRepository;

    @Override
    public List<DeptCity> save(Iterable<DeptCity> unsavedDeptCities) {
        return deptCityRepository.save(unsavedDeptCities);
    }

    @Override
    public List<DeptCity> list(long deptId) {
        return deptCityRepository.findAllByDeptId(deptId);
    }
}
