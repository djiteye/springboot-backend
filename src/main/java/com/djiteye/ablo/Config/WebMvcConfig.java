package com.djiteye.ablo.Config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.djiteye.ablo.Interceptor.FormationInterceptor;
import com.djiteye.ablo.Interceptor.LogoutTokenInterceptor;
import com.djiteye.ablo.Interceptor.ProjetRealiserInterceptor;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

	@Autowired
    private LogoutTokenInterceptor logoutTokenInterceptor;
	@Autowired
	private ProjetRealiserInterceptor projetRealiserInterceptor;
	@Autowired
	private FormationInterceptor formationInterceptor;
	/*@Autowired
	private CommentaireInterceptor commentaireInterceptor;*/
	
    
 

    /*public WebMvcConfig(LogoutTokenInterceptor logoutTokenInterceptor) {
        this.logoutTokenInterceptor = logoutTokenInterceptor;
    }*/

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //registry.addInterceptor(logoutTokenInterceptor).addPathPatterns("/api/auth/signup");
        registry.addInterceptor(logoutTokenInterceptor).addPathPatterns("/api/user/**");
        registry.addInterceptor(projetRealiserInterceptor).addPathPatterns("/projetrealiser/addP");
        registry.addInterceptor(projetRealiserInterceptor).addPathPatterns("/projetrealiser/delete/**");
        registry.addInterceptor(projetRealiserInterceptor).addPathPatterns("/projetrealiser/upP/**");
        registry.addInterceptor(formationInterceptor).addPathPatterns("/formation/addF");
        registry.addInterceptor(formationInterceptor).addPathPatterns("/formation/delete/**");
        registry.addInterceptor(formationInterceptor).addPathPatterns("/formation/upF/**");
        //registry.addInterceptor(commentaireInterceptor).addPathPatterns("/commentaire/**");
        
    }
}
