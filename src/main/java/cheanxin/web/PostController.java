package cheanxin.web;

import cheanxin.constant.LogicConstants;
import cheanxin.domain.Post;
import cheanxin.domain.PostType;
import cheanxin.service.PostService;
import cheanxin.service.PostTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
@RequestMapping("/posts")
public class PostController extends BaseController {
    @Autowired
    private PostService postService;

    @Autowired
    private PostTypeService postTypeService;

    @RequestMapping(method = RequestMethod.GET)
    public Page<Post> list(
            @RequestParam(value = "name", defaultValue = "") String name,
            @RequestParam(value = "enabled", defaultValue = "1") boolean enabled,
            @RequestParam(value = "page", defaultValue = LogicConstants.DEFAULT_PAGE) int page,
            @RequestParam(value = "size", defaultValue = LogicConstants.DEFAULT_SIZE) int size) {
        Page<Post> posts = postService.list(name, enabled, page, size);
        List<Post> postList = posts.getContent();
        List<PostType> postTypeList = postTypeService.list(true);
        for (Post post:postList){
            for (PostType postType:postTypeList){
                if (post.getPostTypeId() == postType.getId()){
                    post.setPostType(postType.getName());
                }
            }
        }
        return posts;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Post> get(@PathVariable(value = "id") long id) {
        Post post = postService.getOne(id);

        Assert.notNull(post, "Post not found");

        return new ResponseEntity<>(post, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Post> save(@Valid @RequestBody Post post, Errors errors) {
        String errorMessage = errors.hasErrors() ? errors.getAllErrors().get(0).getDefaultMessage() : null;
        Assert.isNull(errorMessage, errorMessage);
        Assert.isTrue(postTypeService.isExists(post.getPostTypeId()), "Post type not found.");

        return new ResponseEntity<>(postService.save(post), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Post> update(
            @PathVariable(value = "id") long id,
            @Valid @RequestBody Post post,
            Errors errors) {
        String errorMessage = errors.hasErrors() ? errors.getAllErrors().get(0).getDefaultMessage() : null;
        Assert.isNull(errorMessage, errorMessage);
        Assert.isTrue(postService.isExists(id), "Post not found.");
        Assert.isTrue(postTypeService.isExists(post.getPostTypeId()), "Post type not found.");

        post.setId(id);
        return new ResponseEntity<>(postService.save(post), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void remove(@PathVariable(value = "id") long id) {
        postService.remove(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PATCH)
    public ResponseEntity<Post> patch(
            @PathVariable(value = "id") long id,
            @RequestBody Post post) {
        Post unsavedPost = postService.getOne(id);

        Assert.notNull(unsavedPost, "Post not found.");
        Assert.notNull(post.getEnabled(), "Field enabled is empty.");

        unsavedPost.setEnabled(post.getEnabled());
        return new ResponseEntity<>(postService.save(unsavedPost), HttpStatus.OK);
    }
}
