package kr.co.study.delivery;

import kr.co.study.delivery.filters.JwtAuthenticationFilter;
import kr.co.study.delivery.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.servlet.Filter;

@Configuration              // 이건 설정 클래스예요
@EnableWebSecurity          // 우리는 웹 시큐리티를 사용해요
public class SecurityJavaConfig extends WebSecurityConfigurerAdapter {

    // security 설정 클래스!
    // 어떤 부분을 어떻게 보호할건지 설정해주기
    // 자동으로 만들어지는 login 화면을 없애줄것.

    @Value("${jwt.secret}")     // application.yml에 값을 넣어주고, 여기서 불러옴!!
    private String secret;


    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // 필터 만들어서 인증된 사용자 누구인지 보여주기
        Filter filter = new JwtAuthenticationFilter(authenticationManager(), jwtUtil());

        http
                .formLogin().disable()                  // 화면에 로그인화면 안나오게 하기
                .cors().disable()                       // 함부로 접근 못하게 하는거
                .csrf().disable()
                .headers().frameOptions().disable()     // h2콘솔화면 프레임 깨지게해서 접근못하게 하는거
                .and()      // 바로 위에껀 header에 관한 설정이므로 다시 httpSecurity로 와서 filter처리하기 위함
                .addFilter(filter)                      // 사용자 필터링하는거 넣어주기
                .sessionManagement()                    // 세션 설정하는걸로 들어옴
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);    // 우리가 세션 관리 안하겠다

    }

    // 비번 암호화
    @Bean        // 강제로 빈을 만들어줌 
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JwtUtil jwtUtil(){       // 시크릿 비밀번호 만들기

        return new JwtUtil(secret);
    }
}
