package cheanxin.service.impl;

import cheanxin.data.PostTypeRepository;
import cheanxin.domain.PostType;
import cheanxin.service.PostTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 273cn on 16/12/21.
 */
@Service
public class PostTypeServiceImpl implements PostTypeService {
    @Autowired
    PostTypeRepository postTypeRepository;

    @Override
    public List<PostType> getPostTypes(boolean enabled) {
        return postTypeRepository.findAllByEnabledOrderBySortIndexAsc(enabled);
    }
}
