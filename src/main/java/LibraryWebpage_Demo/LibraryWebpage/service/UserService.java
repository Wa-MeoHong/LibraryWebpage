/*
 *	File : UserService.java
 *	Name : Shin Dae Hong
 *	Data : 2024 / 01 / 01
 *	objective : Class UserService
 *			User Service, Function class that actually provides the service
 *
 *	Modified
 *	=============================================================
 *		1	|	01/01	|	First Write
 *		2	|	01/01	|	...
 *	=============================================================
 */

package LibraryWebpage_Demo.LibraryWebpage.service;

import LibraryWebpage_Demo.LibraryWebpage.dto.UserDTO;
import LibraryWebpage_Demo.LibraryWebpage.entity.UserEntity;
import LibraryWebpage_Demo.LibraryWebpage.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;        // User Repository
    private final PasswordEncoder passwordEncoder;      // Password Encoder

    // Repository Initalize Function
    @Autowired
    protected UserService(UserRepository userRepository, PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // Find Tuple using UserID
    public String findLoginid(Long userid){
        Optional<UserEntity> byID = userRepository.findById(userid);        // find Tuple (Optional object)
        if (byID.isPresent())
            return byID.get().getLoginid();         // if present, return Login ID
        return null;        // if Not present, return null
    }

    // Save In DataBase
    public Long save(UserDTO userDTO) {
        // 암호화를 시킨 버전으로 저장한다.
        UserEntity userEntity = UserEntity.createUser(userDTO, passwordEncoder);        // Create UserEntity with EncodedPassword
        // Repository의 save 매서드 호출, 조건, entity 객체를 넘겨야함
        userRepository.save(userEntity);        // Save Method Call
        return userEntity.getID();      // return ID
    }

    // Update User Data Using @Transactional
    @Transactional
    public String update(UserDTO neweditform){
        Optional<UserEntity> modifying = userRepository.findByloginid(neweditform.getLoginid());
        if(modifying.isPresent()){
            String rawpassword = neweditform.getPassword();     // new Password(Unencoded)
            String encpassword = passwordEncoder.encode(rawpassword);   //Encoding NewPassword

            UserEntity modify = modifying.get();            // userEntity Get

            // Update User Data using Setter
            modify.setPassword(encpassword);
            modify.setName(neweditform.getName());
            modify.setEmail(neweditform.getEmail());
            modify.setPhone(neweditform.getPhone());
            modify.setAddress(neweditform.getAddress());
            modify.setReginum(neweditform.getReginum());

            return "수정 완료";         // Return String Message, Auto Update
        }
        return null;        // if Not Update, return null
    }

    // Password Confirm function
    public String passwordconfirm(UserDTO inputdto, UserDTO userdata){
        // 스프링 시큐리티가 단방향 암호화 기법인 Hash기법을 사용하기 때문에 같은 정보 = 같은 결과가 나온다는 가정

        String data_pw = userdata.getPassword();
        String input_pw = inputdto.getPassword();
        // Password Encoder의 도움을 받아 확인한다.
        if (passwordEncoder.matches(input_pw, data_pw))     // Using PasswordEncoder
            return "인증 성공";     // Return Success message
        return "인증 실패";         // Return Fail Message
    }

    // 유저 아이디 찾는 것을 서비스로 가져옴
    public Optional<UserEntity> findByloginid(String loginid){
        return userRepository.findByloginid(loginid);       // Find method Call
    }

    // 아이디 중복체크 서비스 함수
    public String loginIDCheck(String loginID) {
        Optional<UserEntity> byloginID= userRepository.findByloginid(loginID);      // Find Method Call

        if(byloginID.isPresent()) {
            // -> 조회결과 존재 : 사용불가능
            System.out.println("존재중!");
            System.out.println(byloginID);
            return null;
        }
        else {
            // -> 조회결과 존재 X : 사용가능
            return "ok";
        }
    }

    // 비밀번호 중복 확인 서비스 함수
    public String PasswordCheck(String Password, String Passwordcheck) {
        System.out.println("입력한 비밀번호 : " + Password);
        System.out.println("확인 비밀번호 : " + Passwordcheck);

        if (Password.equals(Passwordcheck))     // Password String Compare
            return "ok";        // 비밀번호가 일치한다면 OK를 전송
        else
            return null;        // 아니면 null을 보냄
    }
}
