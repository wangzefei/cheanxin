package cheanxin.service;

import cheanxin.domain.Post;
import org.springframework.data.domain.Page;

/**
 * Created by 273cn on 16/12/21.
 */
public interface PostService {
    Post save(Post unsavedPost);
    void delete(long id);
    Post findOne(long id);
    Page<Post> getPosts(String name, boolean enabled, int page, int size);
    boolean isExists(long id);
}
