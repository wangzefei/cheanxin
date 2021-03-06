package cheanxin.config;

import cheanxin.constant.ConfigConstants;
import cheanxin.filter.SimpleCORSFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import java.nio.charset.StandardCharsets;

/**
 * Created by 273cn on 16/12/13.
 */
public class WebApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    public String[] getServletMappings() {
        return new String[] { "/" };
    }

    @Override
    public Class<?>[] getRootConfigClasses() {
        return new Class[] { RootConfig.class };
    }

    @Override
    public Class<?>[] getServletConfigClasses() {
        return new Class[] { WebConfig.class };
    }

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        servletContext.setInitParameter("spring.profiles.active", ConfigConstants.DEV);
        FilterRegistration.Dynamic simpleCORSFilter = servletContext.addFilter("simpleCORSFilter", SimpleCORSFilter.class);
        simpleCORSFilter.setInitParameter("encoding", String.valueOf(StandardCharsets.UTF_8));
        simpleCORSFilter.addMappingForUrlPatterns(null, false, "/*");
        super.onStartup(servletContext);
    }
}
