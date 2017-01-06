package cheanxin.web;

import cheanxin.domain.Post;
import cheanxin.domain.PostType;
import cheanxin.exceptions.ErrorResponse;
import cheanxin.exceptions.InvalidArgumentException;
import cheanxin.exceptions.ResourceNotFoundException;
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

    @Autowired
    private PostTypeService postTypeService;

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
        Post post = postService.findOne(id);
        if (post == null)
            throw new ResourceNotFoundException(Post.class.getSimpleName(), "id", String.valueOf(id));
        return new ResponseEntity<>(post, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Post> add(@Valid @RequestBody Post post) {
        if (!postTypeService.isExists(post.getPostTypeId()))
            throw new ResourceNotFoundException(PostType.class.getSimpleName(), "id", String.valueOf(post.getPostTypeId()));
        return new ResponseEntity<>(postService.save(post), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Post> update(
            @PathVariable(value = "id") long id,
            @Valid @RequestBody Post post) {
        if (!postService.isExists(id))
            throw new ResourceNotFoundException(Post.class.getSimpleName(), "id", String.valueOf(id));
        if (!postTypeService.isExists(post.getPostTypeId()))
            throw new ResourceNotFoundException(PostType.class.getSimpleName(), "id", String.valueOf(post.getPostTypeId()));
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
        Post unsavedPost = postService.findOne(id);
        if (unsavedPost == null)
            throw new ResourceNotFoundException(Post.class.getSimpleName(), "id", String.valueOf(id));
        if (post.getEnabled() == null)
            throw new InvalidArgumentException(new ErrorResponse(HttpStatus.BAD_REQUEST.getReasonPhrase(), "Field enabled is empty."));
        unsavedPost.setEnabled(post.getEnabled());
        return new ResponseEntity<>(postService.save(unsavedPost), HttpStatus.OK);
    }
}
