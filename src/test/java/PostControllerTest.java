import cheanxin.config.DatabaseConfig;
import cheanxin.config.RootConfig;
import cheanxin.config.WebConfig;
import cheanxin.web.PostController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

/**
 * Created by 273cn on 16/12/13.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {RootConfig.class, WebConfig.class, DatabaseConfig.class})
@ActiveProfiles(value = "dev")
public class PostControllerTest {
    @Autowired
    private PostController postController;

    @Test
    public void testGet() throws Exception {
        MockMvc mockMvc = standaloneSetup(postController)
                .build();
        mockMvc.perform(get("/posts/2"))
                .andExpect(status().isOk());
    }

}
