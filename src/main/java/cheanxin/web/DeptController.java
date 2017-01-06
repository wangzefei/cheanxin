package cheanxin.web;

import cheanxin.domain.Dept;
import cheanxin.exceptions.ErrorResponse;
import cheanxin.exceptions.InvalidArgumentException;
import cheanxin.exceptions.ResourceNotFoundException;
import cheanxin.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
        if (level != 1 && parentDeptId != 0)
            throw new InvalidArgumentException(new ErrorResponse(HttpStatus.BAD_REQUEST.getReasonPhrase(), "level and parentDeptId are exclusive."));

        if (level != 1)
            return deptService.getDepts(level, enabled);

        return deptService.getDepts(parentDeptId, enabled);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Dept> get(@PathVariable(value = "id") long id) {
        Dept dept = deptService.findOne(id);
        if (dept == null)
            throw new ResourceNotFoundException(Dept.class.getSimpleName(), "id", String.valueOf(id));
        return new ResponseEntity<>(dept, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Dept> add(@Valid @RequestBody Dept dept) {
        if (dept.getParentDeptId() == 0L) {
            return new ResponseEntity<>(deptService.save(dept), HttpStatus.CREATED);
        }

        Dept parentDept = deptService.findOne(dept.getParentDeptId());
        if (parentDept == null)
            throw new ResourceNotFoundException(Dept.class.getSimpleName(), "id", String.valueOf(dept.getParentDeptId()));
        return new ResponseEntity<>(deptService.save(dept, parentDept), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Dept> update(
            @PathVariable(value = "id") long id,
            @Valid @RequestBody Dept dept) {
        if (!deptService.isExists(id))
            throw new ResourceNotFoundException(Dept.class.getSimpleName(), "id", String.valueOf(id));
        if (dept.getParentDeptId() == 0L) {
            dept.setId(id);
            return new ResponseEntity<>(deptService.save(dept), HttpStatus.OK);
        }

        Dept parentDept = deptService.findOne(dept.getParentDeptId());
        if (parentDept == null)
            throw new ResourceNotFoundException(Dept.class.getSimpleName(), "id", String.valueOf(dept.getParentDeptId()));
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
        if (unsavedDept == null)
            throw new ResourceNotFoundException(Dept.class.getSimpleName(), "id", String.valueOf(id));
        if (dept.getEnabled() == null)
            throw new InvalidArgumentException(new ErrorResponse(HttpStatus.BAD_REQUEST.getReasonPhrase(), "Field enabled is empty."));
        unsavedDept.setEnabled(dept.getEnabled());
        return new ResponseEntity<>(deptService.save(unsavedDept), HttpStatus.OK);
    }
}
