package ir.peykasa.authaspect.auth;


import ir.peykasa.authaspect.auth.exception.AccessDeniedException;
import ir.peykasa.authaspect.auth.model.CheckAccessReq;
import ir.peykasa.authaspect.common.Constants;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;

@Aspect
@Component
@ConditionalOnProperty(value = "auth.enabled", havingValue = "true", matchIfMissing = false)
public class AuthAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthAspect.class);

    private final HttpServletRequest request;
    private final RestTemplate restTemplate;
//    @Value("${auth.check.url}")
//    String authCheckUrl;

    @Autowired
    public AuthAspect(HttpServletRequest request, RestTemplate restTemplate) {
        this.request = request;
        this.restTemplate = restTemplate;
    }


    @Around("@annotation(auth)")
    public Object auth(ProceedingJoinPoint joinPoint, Auth auth) throws Throwable {
        String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (header != null && auth.check()) {
            if (checkAccess(header, request)) {
                return joinPoint.proceed();
            }
            throw new AccessDeniedException(Constants.ERROR_UNAUTHORIZED_MSG);
        }
        return null;
    }


    Boolean checkAccess(String header, HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        LOGGER.info("Check Access For [{}] [{}] in Thread [{}]", header, requestURI, Thread.currentThread().getName());

        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.AUTHORIZATION, header);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        HttpEntity<CheckAccessReq> entity = new HttpEntity<>(new CheckAccessReq(requestURI), headers);

        // ResponseEntity<CheckAccessResp> checkAccessResp = restTemplate.exchange(authCheckUrl, HttpMethod.POST, entity, CheckAccessResp.class);
        return false;

    }
}
