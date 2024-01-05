package LibraryWebpage_Demo.LibraryWebpage.controller;

import LibraryWebpage_Demo.LibraryWebpage.dto.UserDTO;
import LibraryWebpage_Demo.LibraryWebpage.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // 회원가입 페이지를 출력 요청함!
    @GetMapping("/user/signup")
    public String saveform(Model model){
        model.addAttribute("userForm",new UserDTO());
        return "/user/signup.html";
    }

    // 로그인 페이지
    @GetMapping("/user/login")
    public String loginform(Model model){
        model.addAttribute("loginform",new UserDTO());
        return "/user/login.html";
    }

    @GetMapping("/user/pwconfirm")
    public String passwordconfirm(@AuthenticationPrincipal UserDetails user, Model model){
        return "/user/pwconfirm.html";
    }

    // 회원가입 요청
    @PostMapping("/user/signup")
    public String signup(@Validated UserDTO userDTO, BindingResult result)
    {
        if(result.hasErrors())
            return "redirect:/user/signup";
        userService.save(userDTO);
        return "redirect:/user/login";
    }

    // 회원 수정하기
    @PostMapping("/user/useredit")
    public String edit(@Validated UserDTO userDTO, BindingResult result) {
        if (result.hasErrors())
            return "redirect:/user/useredit";   // 만약 에러가 난다면, 회원수정화면 그대로
        userService.update(userDTO);
        return "redirect:/logined";     // 회원 수정이 완료되면, 다시 메인화면으로 이동
    }

    // 비밀번호 확인
    @PostMapping("/user/pwconfirm")
    public String confirm(@Validated UserDTO userDTO, @AuthenticationPrincipal UserDetails user,  Model model){
        UserDTO userdata = UserDTO.toUserDTO(userService.findByloginid(user.getUsername()).get());  // UserData를 얻어옴
        String message = userService.passwordconfirm(userDTO, userdata);        // 비밀번호 확인 작업
        if (message.equals("인증 성공"))
            return "redirect:/user/useredit";
        return "redirect:/user/pwconfirm";
    }

    // ajox로 사용하기 때문에 ResponseBody가 필요함
    @PostMapping("/user/loginID-check")
    public @ResponseBody String loginIDCheck(@RequestParam("loginID") String loginID){
        System.out.println("loginID = " + loginID);
        // ID 중복체크는 서비스에서 진행한다.
        String checkResult = userService.loginIDCheck(loginID);
        // ID 중복 결과에 따라 정해진 로그를 전송
        return checkResult;
    }

    @PostMapping("/user/password-check")
    public @ResponseBody String PasswordCheck(@RequestParam("PW") String Password, @RequestParam("PWcheck") String Passwordcheck){
        String checkResult = userService.PasswordCheck(Password, Passwordcheck);
        // 비밀번호 중복의 결과에 따라 로그를 전송
        return checkResult;
    }

}
