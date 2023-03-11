package ru.stud.auc.auth;

import lombok.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.handler.HandlerExceptionResolverComposite;
import ru.stud.auc.auth.jwt.JwtReaderService;
import ru.stud.auc.auth.model.UserAuthPojo;
import ru.stud.auc.common.enums.ClientRole;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtReaderService jwtReaderService;

    private final HandlerExceptionResolver resolver;

    public JwtAuthenticationFilter( JwtReaderService jwtReaderService, List<HandlerExceptionResolver> resolvers) {
        this.jwtReaderService = jwtReaderService;
        this.resolver = resolvers.stream().filter(r -> r instanceof HandlerExceptionResolverComposite).findFirst().get();

    }

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {
        try {
            final String authHeader = request.getHeader("Authorization");
            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                filterChain.doFilter(request, response);
                return;
            }
            String jwt = authHeader.substring(7);
            String login = jwtReaderService.extractUsername(jwt);
            UUID userId =  UUID.fromString(jwtReaderService.extractUserId(jwt));
            if (login != null && userId != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                if (!jwtReaderService.isTokenExpired(jwt)) {
                    UserAuthPojo pojo = new UserAuthPojo();
                    pojo.setId(userId);
                    pojo.setLogin(login);
                    pojo.setPassword(login);
                    pojo.setRole(ClientRole.valueOf(jwtReaderService.extractUserRole(jwt)));
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(pojo,
                                                                                                            userId,
                                                                                                            pojo.getAuthorities());
                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authToken);

                }
            }
            filterChain.doFilter(request, response);
        } catch (Exception e) {
            resolver.resolveException(request, response, null, e);
        }
    }
}
