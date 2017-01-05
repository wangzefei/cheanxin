package cheanxin.service.impl;

import cheanxin.data.PostRepository;
import cheanxin.data.PostTypeRepository;
import cheanxin.domain.Post;
import cheanxin.domain.PostType;
import cheanxin.service.PostService;
import cheanxin.service.PostTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 273cn on 16/12/21.
 */
@Service
public class PostServiceImpl implements PostService {
    @Autowired
    PostRepository postRepository;

    @Override
    public Post save(Post unsavedPost) {
        return postRepository.save(unsavedPost);
    }

    @Override
    public void delete(long id) {
        postRepository.delete(id);
    }

    @Override
    public Post get(long id) {
        return postRepository.getOne(id);
    }

    @Override
    public Post enableOrDisablePost(long id, boolean enabled) {
        Post post = get(id);
        post.setEnabled(enabled);
        return save(post);
    }

    @Override
    public Page<Post> getPosts(String name, boolean enabled, int page, int size) {
        Pageable pageable = new PageRequest(page, size);
        if (name == null || name.trim().isEmpty()) {
            return postRepository.findByEnabled(enabled, pageable);
        }
        return postRepository.findByNameIgnoreCaseContainingAndEnabled(name, enabled, pageable);
    }
}
