package cheanxin.web;

import cheanxin.domain.PostType;
import cheanxin.global.Constants;
import cheanxin.service.PostTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by 273cn on 16/12/14.
 */
@RestController
@RequestMapping("/post_types")
public class PostTypeController extends BaseController {
    @Autowired
    private PostTypeService postTypeService;

    @RequestMapping(method=RequestMethod.GET)
    public List<PostType> allPostTypes() {
        return postTypeService.getPostTypes();
    }

}
