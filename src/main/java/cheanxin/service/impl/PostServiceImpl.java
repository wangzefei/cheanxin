package cheanxin.service.impl;

import cheanxin.data.PostRepository;
import cheanxin.domain.Post;
import cheanxin.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public void remove(long id) {
        postRepository.delete(id);
    }

    @Override
    public Post getOne(long id) {
        return postRepository.findOne(id);
    }

    @Override
    public boolean isExists(long id) {
        return getOne(id) != null;
    }

    @Override
    public Page<Post> list(String name, boolean enabled, int page, int size) {
        Pageable pageable = new PageRequest(page, size);
        if (name == null || name.trim().isEmpty()) {
            return postRepository.findByEnabled(enabled, pageable);
        }
        return postRepository.findByNameIgnoreCaseContainingAndEnabled(name, enabled, pageable);
    }

    @Override
    public Map<Long, Post> listPostMap(boolean enabled) {
        List<Post> postList = postRepository.findByEnabled(enabled);
        Map<Long, Post> postMap = new HashMap<>();
        for (Post post : postList) {
            postMap.put(post.getId(), post);
        }
        return postMap;
    }
}
