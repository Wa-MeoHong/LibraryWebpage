/*
 *	File : UserEntity.java
 *	Name : Shin Dae Hong
 *	Data : 2024 / 01 / 01
 *	objective : Class UserEntity
 *			User Table Entity, DB <-> Web..
 *
 *	Modified
 *	=============================================================
 *		1	|	01/01	|	First Write
 *		2	|	01/01	|	Add Logger
 *	=============================================================
 */


package LibraryWebpage_Demo.LibraryWebpage.entity;


import LibraryWebpage_Demo.LibraryWebpage.dto.UserDTO;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Table(name="user")
public class UserEntity {
    /*
     * User Table
     *
     * ID : 기본키, 회원 구별용도, 인덱싱 개념. BIGINT, Auto increse
     * loginid :  Unique, 45자 제한
     * Password : 비밀번호, 255자 제한, 아직 암호화는 진행하지 못함.
     * name : 이름, 한글입력 가능, 45자 제한
     * phone : 전화번호, 010xxxxoooo 13자 제한
     * email : 이메일, 이메일양식에 맞게 입력해야함, 45자 제한
     * reginum : 주민번호, 000000-xxxxxxx 즉 14자로 제한
     * member_date : 회원가입 날짜, 날짜만 찍힘. DATE자료형 @CreateDate와 @EnableJpaAuditing 필요
     * Resign : Boolean, 기본값은 False로 설정해야함.
     * */

    @Id // 기본키
    @Column(unique = true, name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;

    @Column(length = 45, unique = true,  name = "loginid")
    private String loginid;
    @Column(length = 255, name = "password")
    private String Password;
    @Column(length = 45, name = "name")
    private String name;
    @Column(length = 13, name = "phone")
    private String phone;
    @Column(length = 45, name = "email")
    private String email;
    @Column(length = 255, name = "address")
    private String address;
    @Column(length= 14, name = "reginum")
    private String reginum;

    @Column(name = "member_date")
    @CreatedDate
    @NonNull
    private LocalDate MemberDate;

    @Column(name = "nosign")
    private Boolean nosign;

    // Builder Function, UserEntity Build
    @Builder
    public UserEntity(Long id, String loginid, String PW, String Name,
                      String phone, String email, String address,
                      String reginum, LocalDate memberdate){
        this.ID = id;
        this.loginid = loginid;
        this.Password = PW;
        this.name = Name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.reginum = reginum;
        this.MemberDate = memberdate;
        this.nosign = false;
    }

    // renew UserEntity for Password Encode
    public static UserEntity createUser(UserDTO userDTO, PasswordEncoder passwordEncoder){
        // 암호화된 PW로 새롭게 Entity 생성
        return new UserEntity(
                null,
                userDTO.getLoginid(),
                passwordEncoder.encode(userDTO.getPassword()),  // Password Encoding
                userDTO.getName(),
                userDTO.getPhone(),
                userDTO.getEmail(),
                userDTO.getAddress(),
                userDTO.getReginum(),
                userDTO.getMemberDate()
        );
    }

    // User Data Updated, Entity Update
    public static UserEntity updateUser(UserEntity olddata, UserDTO newdto, PasswordEncoder passwordEncoder) {
        olddata.setPassword(passwordEncoder.encode(newdto.getPassword()));  // Password Encoding
        olddata.setName(newdto.getName());
        olddata.setEmail(newdto.getEmail());
        olddata.setPhone(newdto.getPhone());
        olddata.setAddress(newdto.getAddress());
        olddata.setReginum(newdto.getReginum());

        return olddata;
    }
}
