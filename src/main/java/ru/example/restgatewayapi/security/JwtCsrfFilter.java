package ru.example.restgatewayapi.security;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.InvalidCsrfTokenException;
import org.springframework.security.web.csrf.MissingCsrfTokenException;
import org.springframework.security.web.util.UrlUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;
import ru.example.restgatewayapi.resources.property.MultyResourcePropertiesReader;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class JwtCsrfFilter extends OncePerRequestFilter {

    private String api;

    private String auth;

    private String login;

    private final CsrfTokenRepository tokenRepository;

    private final HandlerExceptionResolver resolver;

    public JwtCsrfFilter(CsrfTokenRepository tokenRepository, HandlerExceptionResolver resolver) {
        this.tokenRepository = tokenRepository;
        this.resolver = resolver;
        MultyResourcePropertiesReader propsreader = new MultyResourcePropertiesReader();
        Map<String, String> pair = propsreader
                .setSourcePropFile("application.properties")
                //todo readValueByKey передавать ссылку this.key и убрать 'pair' и get()
                .readValueByKey("paths.api")
                .readValueByKey("paths.auth")
                .readValueByKey("paths.login")
                .get();
        this.api = pair.get("paths.api");
        this.auth = pair.get("paths.auth");
        this.login = pair.get("paths.login");
        pair.clear();
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        request.setAttribute(HttpServletResponse.class.getName(), response);
        CsrfToken csrfToken = this.tokenRepository.loadToken(request);
        boolean missingToken = csrfToken == null;
        if (missingToken) {
            try {
                csrfToken = this.tokenRepository.generateToken(request);
                this.tokenRepository.saveToken(csrfToken, request, response);
            } catch (RuntimeException e) {
                /* если какая-либо ошибка , например, JwtTokenRepository (нет jwt.secret) тут отлавливаем */
                this.logger.error("Ошибка во время работы tokenRepository, подробнее: " + e.getMessage());
                resolver.resolveException(request, response, null, new IllegalArgumentException("Server Error"));
                return;
            }
        }
        request.setAttribute(CsrfToken.class.getName(), csrfToken);
        request.setAttribute(csrfToken.getParameterName(), csrfToken);
        if (request.getServletPath().equals(this.api + this.auth + this.login)) {
            try {
                filterChain.doFilter(request, response);
            } catch (Exception e) {
                resolver.resolveException(request, response, null, new MissingCsrfTokenException(csrfToken.getToken()));
            }
        } else {
            String actualToken = request.getHeader(csrfToken.getHeaderName());
            if (actualToken == null) {
                actualToken = request.getParameter(csrfToken.getParameterName());
            }
            try {
                if (!StringUtils.isEmpty(actualToken)) {
                    Jwts.parser()
                            .setSigningKey(((JwtTokenRepository) tokenRepository).getSecret())
                            .parseClaimsJws(actualToken);

                    filterChain.doFilter(request, response);
                } else {
                    /* если убрать configure(WebSecurity web) метод в SpringSecurityConfig
                    * тогда тут будет ловиться исключение при попытки посетить публичные адреса без аутентификации */
                    resolver.resolveException(request, response, null, new InvalidCsrfTokenException(csrfToken, actualToken));
                }
            } catch (JwtException e) {
                if (this.logger.isDebugEnabled()) {
                    this.logger.debug("Invalid CSRF token found for " + UrlUtils.buildFullRequestUrl(request));
                }

                if (missingToken) {
                    resolver.resolveException(request, response, null, new MissingCsrfTokenException(actualToken));
                } else {
                    resolver.resolveException(request, response, null, new InvalidCsrfTokenException(csrfToken, actualToken));
                }
            }
        }
    }
}
