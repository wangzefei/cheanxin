package cheanxin.service;

import cheanxin.domain.Post;
import org.springframework.data.domain.Page;

/**
 * Created by 273cn on 16/12/21.
 */
public interface PostService {
    public Post save(Post unsavedPost);
    public void delete(long id);
    public Post get(long id);
    public Page<Post> getPosts(String name, boolean enabled, int page, int size);
    public Post enableOrDisablePost(long id, boolean enabled);
}
