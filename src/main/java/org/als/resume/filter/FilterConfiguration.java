package org.als.resume.filter;

import ch.qos.logback.access.common.servlet.TeeFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class FilterConfiguration {

    @Autowired
    @Bean
    public FilterRegistrationBean requestResponseFilter() {
        final FilterRegistrationBean filterRegBean = new FilterRegistrationBean();
        TeeFilter filter = new TeeFilter();
        filterRegBean.setFilter(filter);
        filterRegBean.setUrlPatterns(Arrays.asList("*"));
        filterRegBean.setName("Request Response Filter");
        filterRegBean.setAsyncSupported(Boolean.TRUE);
        return filterRegBean;
    }
}

