package cheanxin.web;

import cheanxin.domain.Dept;
import cheanxin.exceptions.ErrorResponse;
import cheanxin.exceptions.InvalidArgumentException;
import cheanxin.exceptions.ResourceNotFoundException;
import cheanxin.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by 273cn on 16/12/14.
 */
@RestController
@RequestMapping("/depts")
public class DeptController extends BaseController {
    @Autowired
    private DeptService deptService;

    @RequestMapping(method = RequestMethod.GET)
    public List<Dept> getDepts(
            @RequestParam(value = "level", defaultValue = "1") int level,
            @RequestParam(value = "parentDeptId", defaultValue = "0") long parentDeptId,
            @RequestParam(value = "enabled", defaultValue = "1") boolean enabled) {
        Assert.isTrue(level == 1 || parentDeptId == 0, "level and parentDeptId are exclusive.");

        if (level != 1)
            return deptService.getDepts(level, enabled);

        return deptService.getDepts(parentDeptId, enabled);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Dept> get(@PathVariable(value = "id") long id) {
        Dept dept = deptService.findOne(id);

        Assert.notNull(dept, "Dept not found");

        return new ResponseEntity<>(dept, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Dept> add(
            @Valid @RequestBody Dept dept,
            Errors errors) {
        Assert.isTrue(!errors.hasErrors(), errors.getAllErrors().get(0).getDefaultMessage());
        if (dept.getParentDeptId() == 0L)
            return new ResponseEntity<>(deptService.save(dept), HttpStatus.CREATED);

        Dept parentDept = deptService.findOne(dept.getParentDeptId());
        Assert.notNull(parentDept, "Parent dept not found");

        return new ResponseEntity<>(deptService.save(dept, parentDept), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Dept> update(
            @PathVariable(value = "id") long id,
            @Valid @RequestBody Dept dept,
            Errors errors) {
        Assert.isTrue(!errors.hasErrors(), errors.getAllErrors().get(0).getDefaultMessage());
        Assert.isTrue(deptService.isExists(id), "Dept not found.");

        if (dept.getParentDeptId() == 0L) {
            dept.setId(id);
            return new ResponseEntity<>(deptService.save(dept), HttpStatus.OK);
        }

        Dept parentDept = deptService.findOne(dept.getParentDeptId());
        Assert.notNull(parentDept, "Parent dept not found");

        dept.setId(id);
        return new ResponseEntity<>(deptService.save(dept, parentDept), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable(value = "id") long id) {
        deptService.delete(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PATCH)
    public ResponseEntity<Dept> enableOrDisableDept(
            @PathVariable(value = "id") long id,
            @RequestBody Dept dept) {
        Dept unsavedDept = deptService.findOne(id);

        Assert.notNull(unsavedDept, "Dept not found");
        Assert.notNull(dept.getEnabled(), "Field enabled is empty.");

        unsavedDept.setEnabled(dept.getEnabled());
        return new ResponseEntity<>(deptService.save(unsavedDept), HttpStatus.OK);
    }
}
