package LibraryWebpage_Demo.LibraryWebpage.config;

import LibraryWebpage_Demo.LibraryWebpage.entity.UserEntity;
import LibraryWebpage_Demo.LibraryWebpage.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class MyUserDetailService implements UserDetailsService {

    private final UserService userService;

    @Autowired
    public MyUserDetailService(UserService userService){
        this.userService = userService;
    }
    // userService를 주입받기위한 어노테이션, 생성자를 통한 의존성 주입이 이루어짐

    @Override
    public UserDetails loadUserByUsername(String insertedUserid){
        Optional<UserEntity> findOne = userService.findByloginid(insertedUserid);       // 엔티티를 찾아냄
        // 만약 이 유저가 없는 유저라면, 오류를 반환함.
        UserEntity user = findOne.orElseThrow(() -> new UsernameNotFoundException("없는 유저입니다."));

        return User.builder()
                .username(user.getLoginid())
                .password(user.getPassword())
                .roles("ADMIN")
                .build();
    }
}
