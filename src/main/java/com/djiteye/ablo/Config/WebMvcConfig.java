package com.djiteye.ablo.Config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


import com.djiteye.ablo.Interceptor.LogoutTokenInterceptor;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

	@Autowired
    private LogoutTokenInterceptor logoutTokenInterceptor;
	
    
 

    /*public WebMvcConfig(LogoutTokenInterceptor logoutTokenInterceptor) {
        this.logoutTokenInterceptor = logoutTokenInterceptor;
    }*/

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //registry.addInterceptor(logoutTokenInterceptor).addPathPatterns("/api/auth/signup");
        registry.addInterceptor(logoutTokenInterceptor).addPathPatterns("/api/user/**");
        
    }
}
