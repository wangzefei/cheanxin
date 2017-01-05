package cheanxin.web;

import cheanxin.domain.Post;
import cheanxin.domain.PostType;
import cheanxin.global.Constants;
import cheanxin.service.PostService;
import cheanxin.service.PostTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by 273cn on 16/12/14.
 */
@RestController
@RequestMapping("/posts")
public class PostController extends BaseController {
    @Autowired
    private PostService postService;

    @RequestMapping(method = RequestMethod.GET)
    public Page<Post> getPosts(
            @RequestParam(value = "name", defaultValue = "") String name,
            @RequestParam(value = "enabled", defaultValue = "1") boolean enabled,
            @RequestParam(value = "page", defaultValue = Constants.DEFAULT_PAGE) int page,
            @RequestParam(value = "size", defaultValue = Constants.DEFAULT_SIZE) int size) {
        return postService.getPosts(name, enabled, page, size);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Post> get(@PathVariable(value = "id") long id) {
        return new ResponseEntity<>(postService.get(id), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Post> add(@Valid @RequestBody Post post) {
        return new ResponseEntity<>(postService.save(post), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Post> update(
            @PathVariable(value = "id") long id,
            @Valid @RequestBody Post post) {
        post.setId(id);
        return new ResponseEntity<>(postService.save(post), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable(value = "id") long id) {
        postService.delete(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PATCH)
    public ResponseEntity<Post> patch(
            @PathVariable(value = "id") long id,
            @RequestBody Post post) {
        return new ResponseEntity<>(postService.enableOrDisablePost(id, post.getEnabled()), HttpStatus.OK);
    }
}
