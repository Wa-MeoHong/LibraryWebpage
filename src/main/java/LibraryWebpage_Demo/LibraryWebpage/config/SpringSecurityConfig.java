/*
 *	File : SpringSecurityConfig.java
 *	Name : Shin Dae Hong
 *	Data : 2024 / 01 / 02
 *	objective : Class SpringSecurityConfig
 *			SpringSecurity Configuration, Set required configuration for login using SpringSecurity
 *
 *	Modified
 *	=============================================================
 *		1	|	01/02	|	First Write
 *		2	|	01/02	|	...
 *	==============================================================
 */

/*
 * 로그인을 구현하는데 필요한 정보를 담고있음
 * 따라서, 필수적으로 구현해야할 필요 있음
 * */

package LibraryWebpage_Demo.LibraryWebpage.config;
import jakarta.servlet.DispatcherType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {

    // Password Encoder
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Login Chain
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .cors().disable()
                .authorizeHttpRequests(request -> request
                        .dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll()
                        .requestMatchers("/", "/user/**", "/css/**", "/js/**", "/images/**").permitAll()  // 로그인 안한 Home, 회원가입만이 인증을 거치지 않고 간다.
                        .anyRequest().authenticated()       // 나머지는 인증을 거친 후에 간다.
                )
                .formLogin(login -> login
                        .loginPage("/")       // 메인 화면으로 나오는곳
                        .loginProcessingUrl("/login-process")      // @Post("/user/login/")의 역할을 한다.
                        .usernameParameter("loginid")    // Username 칸에 입력되는 곳(ID 입력창에 입력되는 변수이름은 login id로 설정
                        .passwordParameter("password")  // Password 칸에 입력되는 곳 변수 이름이 "password"로 설정되어있음)
                        .defaultSuccessUrl("/logined", true)  // 로그인 성공시 들어가는 페이지.
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/user/logout")
                );

        return http.build();
    }
}
