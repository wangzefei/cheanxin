package cheanxin.service;

import cheanxin.domain.PostType;

import java.util.List;

/**
 * Created by 273cn on 16/12/21.
 */
public interface PostTypeService {
    List<PostType> list(boolean enabled);
    PostType getOne(long id);
    boolean isExists(long id);
}
