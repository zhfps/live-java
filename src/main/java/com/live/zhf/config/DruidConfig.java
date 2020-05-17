package com.live.zhf.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/***
 * 数据库连接池配置
 */
@Configuration
public class DruidConfig {
    @ConfigurationProperties(prefix = "spring.datasource")
    @Bean
    public DataSource druid(){
        return new DruidDataSource();
    }
    @Bean
    public ServletRegistrationBean stateServletView(){
        ServletRegistrationBean bean =  new ServletRegistrationBean<>(new StatViewServlet(),"/druid/*");
        Map<String,String> map = new HashMap<>();
        map.put("loginUsername","admin");
        map.put("loginPassword","admin");
        bean.setInitParameters(map);
        return bean;
    }
    public FilterRegistrationBean webStatFilter(){
        FilterRegistrationBean bean = new FilterRegistrationBean();
        bean.setFilter(new WebStatFilter());
        Map<String,String> map = new HashMap<>();
        map.put("exclusions","*.js,*.css,*.jpeg,/druid/*");
        bean.setInitParameters(map);
        bean.setUrlPatterns(Arrays.asList("/*"));
        return bean;
    }
}
