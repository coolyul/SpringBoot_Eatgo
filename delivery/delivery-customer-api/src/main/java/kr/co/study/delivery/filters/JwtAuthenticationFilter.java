package kr.co.study.delivery.filters;

import io.jsonwebtoken.Claims;
import kr.co.study.delivery.utils.JwtUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthenticationFilter extends BasicAuthenticationFilter {

    // 인증된 사용자가 어떤 사용자인지(누구인지?) 필터링해서 돌려주는 클래스


    private JwtUtil jwtUtil;

    // AuthenticationManager는 기본 변수 ! 그리고 JwtUtil 쓸거기때문에 같이 넣어줌
    public JwtAuthenticationFilter(AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
        super(authenticationManager);
        this.jwtUtil = jwtUtil;
    }



    // 요청이 있을때마다 메서드가 실행되어야 한다! 기본 자동 메서드 doFilter
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws IOException, ServletException {


        Authentication authentication = getAuthentication(request);

        // authentication 이 있을때만 실행
        if(authentication != null){
            SecurityContext context = SecurityContextHolder.getContext();
            context.setAuthentication(authentication); // 우리가 쓰고있는 context를 셋팅
        }

        // 요 메소드는 항상 실행이 되어야 함 필터링 해야하니까
        chain.doFilter(request, response);

    }


    // 우리 스프링 내부에서 사용하는 Authentication
    private Authentication getAuthentication(HttpServletRequest request){

        String token = request.getHeader("Authorization");

        // authentication이 null일때
        if(token == null){
            return null;
        }

        // TODO : jwtUtil에서 claim 얻기
        Claims claims = jwtUtil.getClaims(token.substring("Bearer ".length()));

        Authentication authentication = new UsernamePasswordAuthenticationToken(claims,null);
        return authentication;
    }
}
