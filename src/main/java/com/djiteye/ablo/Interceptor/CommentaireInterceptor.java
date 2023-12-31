package com.djiteye.ablo.Interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.djiteye.ablo.Securite.Jwt.JwtUtils;



@Component
public class CommentaireInterceptor implements HandlerInterceptor {
	@Autowired
	private JwtUtils jwtUtils;
	
	Logger log= LoggerFactory.getLogger(CommentaireInterceptor.class);
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		String authorizationHeader = request.getHeader("Authorization");
		String accessToken = authorizationHeader.substring(7); //

        // Vérifiez si l'en-tête Authorization est présent et commence par "Bearer "
		if (jwtUtils.validateAccessToken(accessToken) && authorizationHeader != null && authorizationHeader.startsWith("Bearer ") ) {
            //String accessToken = authorizationHeader.substring(7); //
		// TODO Auto-generated method stub
		log.info("preHandle invoke ... {}:{} "+ request.getRequestURI(), request.getMethod());
		return true;}else {
			if( authorizationHeader == null) {
				log.info("preHandle invoke ... {}:{} "+ request.getRequestURI(), request.getMethod());
				return true;
			}
			//response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid or missing access token");
			 response.setStatus(HttpServletResponse.SC_UNAUTHORIZED );
			 response.setContentType("application/json");
	            response.getWriter().write("{\n"+"\"error\": \"Invalid or missing access token"+"\n}");
            return false; // Bloquez la requête, ne la laissez pas continuer son traitement
        }
	}
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		log.info("postHandle invoke ... {}:{} "+ request.getRequestURI(), request.getMethod());
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		if(ex!=null) {
			log.error("exception inside after-compilation "+ ex.getMessage());
		}
		log.info("afterCompletion invoke ... {}:{} "+ request.getRequestURI(), request.getMethod());
	}


}
