package LibraryWebpage_Demo.LibraryWebpage.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

public class HomeController {
    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("HeaderNavigation", "login");
        return "/home/mainhome.html";
    }

    @GetMapping("/logined")
    public String index_login(@AuthenticationPrincipal UserDetails user, Model model){
        model.addAttribute("Loginid", user.getUsername());
        model.addAttribute("HeaderNavigation", "logout");
        System.out.println("접속 아이디 : " + user.getUsername());
        return "home/mainhome.html";
    }

    /*
    // 메세지 출력
    private String showMessageAndRedirect(final MessageDTO params, Model model) {
        model.addAttribute("params", params); // Thymeleaf를 이용하기 때문에, 변수 넘겨주기 해야함.
        return "common/messageRedirect";
    }
     */


}
