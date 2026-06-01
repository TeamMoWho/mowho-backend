package team.mowho.backend.global.jwt.filter;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;
import team.mowho.backend.global.jwt.resolver.JwtTokenResolver;

import java.io.IOException;

import static net.logstash.logback.argument.StructuredArguments.keyValue;

@Slf4j
@RequiredArgsConstructor
public class JwtTokenFilter extends OncePerRequestFilter {

    private final JwtTokenResolver jwtTokenResolver;
    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {
        try {
            jwtTokenResolver.resolveTokenFromRequest(request)
                    .filter(jwtTokenResolver::isAccessToken)
                    .ifPresent(token -> setAuthentication(request, token));
        } catch (ExpiredJwtException e) {
            log.debug("액세스 토큰이 만료되었습니다.");
            SecurityContextHolder.clearContext();
        } catch (Exception e) {
            log.error("[ERROR]",
                    keyValue("errorMessage", "인증 처리 중 예상치 못한 오류가 발생했습니다."),
                    e);
            SecurityContextHolder.clearContext();
        }

        filterChain.doFilter(request, response);
    }

    private void setAuthentication(HttpServletRequest request, String token) {
        UserDetails userDetails = loadUserDetails(token);
        UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    private UserDetails loadUserDetails(String token) {
        String subject = jwtTokenResolver.getSubjectFromToken(token);
        return userDetailsService.loadUserByUsername(subject);
    }

}

