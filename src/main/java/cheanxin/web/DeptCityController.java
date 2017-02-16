package cheanxin.web;

import cheanxin.domain.DeptCity;
import cheanxin.domain.User;
import cheanxin.service.DeptCityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.util.Assert;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jawinton on 17/02/16.
 */
@RestController
@RequestMapping("/dept_cities")
public class DeptCityController extends BaseController {
    @Autowired
    private DeptCityService deptCityService;

    @RequestMapping(method = RequestMethod.POST ,consumes = "application/json")
    public ResponseEntity<List<DeptCity>> save(
            @Valid @RequestBody DeptCity deptCity,
            Errors errors,
            @AuthenticationPrincipal User user) {
        String errorMessage = errors.hasErrors() ? errors.getAllErrors().get(0).getDefaultMessage() : null;
        Assert.isNull(errorMessage, errorMessage);
        Assert.notEmpty(deptCity.getCityIds(), "CityIds can not be empty.");

        List<DeptCity> deptCities = new ArrayList<>();
        long now = System.currentTimeMillis() / 1000;
        for (Long cityId : deptCity.getCityIds()) {
            DeptCity tmpDeptCity = new DeptCity();
            tmpDeptCity.setDeptId(deptCity.getDeptId());
            tmpDeptCity.setCityId(cityId);
            tmpDeptCity.setCreatorUsername(user.getUsername());
            tmpDeptCity.setCreatedTime(now);
            deptCities.add(tmpDeptCity);
        }

        return new ResponseEntity<>(deptCityService.save(deptCities), HttpStatus.CREATED);
    }
}
