package kr.co.study.delivery;

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

        http
                .formLogin().disable()                  // 화면에 로그인화면 안나오게 하기
                .cors().disable()                       // 함부로 접근 못하게 하는거
                .csrf().disable()
                .headers().frameOptions().disable();     // h2콘솔화면 프레임 깨지게해서 접근못하게 하는거

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
