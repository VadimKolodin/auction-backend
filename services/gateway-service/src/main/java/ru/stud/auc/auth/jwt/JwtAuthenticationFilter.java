package ru.stud.auc.auth.jwt;

import lombok.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.handler.HandlerExceptionResolverComposite;
import ru.stud.auc.auth.model.UserAuthPojo;
import ru.stud.auc.users.UsersAuthMapper;
import ru.stud.auc.auth.token.TokenService;
import ru.stud.auc.exception.UnauthorizedException;
import ru.stud.auc.users.UsersAuthRepository;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final UsersAuthMapper mapper;

    private final TokenService tokenService;

    private final UsersAuthRepository usersRepository;


    private final HandlerExceptionResolver resolver;

    public JwtAuthenticationFilter(UsersAuthMapper mapper,
                                   TokenService tokenService,
                                   UsersAuthRepository usersRepository,
                                   List<HandlerExceptionResolver> resolvers) {
        this.mapper = mapper;
        this.tokenService = tokenService;
        this.usersRepository = usersRepository;
        this.resolver = resolvers.stream().filter( r -> r instanceof HandlerExceptionResolverComposite).findFirst().get();;
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
            String login = tokenService.getLoginFromToken(jwt);
            if (login != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserAuthPojo userDetails = mapper.toPojo(usersRepository.findByLogin(login).orElseThrow(UnauthorizedException::new));
                if (tokenService.isTokenExistAndValid(jwt, userDetails)) {
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, userDetails.getId(), userDetails.getAuthorities());
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
