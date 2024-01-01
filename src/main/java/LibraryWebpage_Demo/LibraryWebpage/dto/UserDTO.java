/*
 *	File : UserDTO.java
 *	Name : Shin Dae Hong
 *	Data : 2024 / 01 / 01
 *	objective : Class UserDTO
 *			User Table DTO, Used in WEB, Back Server
 *
 *	Modified
 *	=============================================================
 *		1	|	01/01	|	First Write
 *	=============================================================
 */


package LibraryWebpage_Demo.LibraryWebpage.dto;

import LibraryWebpage_Demo.LibraryWebpage.entity.UserEntity;
import lombok.*;
import java.time.LocalDate;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserDTO {
    // User Table Attributes
    private Long ID;
    private String loginid;
    private String Password;
    private String name;
    private String phone;
    private String email;
    private String address;
    private LocalDate MemberDate;
    private String reginum;
    private Boolean nosign;

    // DTO -> Entity Transform Function
    public UserEntity toEntity() {
        UserEntity build = UserEntity.builder()
                .id(ID)
                .loginid(loginid)
                .PW(Password)
                .Name(name)
                .phone(phone)
                .email(email)
                .address(address)
                .memberdate(MemberDate)
                .reginum(reginum)
                .build();
        return build;
    }

    // Entity -> DTO Transform Function
    public static UserDTO toUserDTO(UserEntity userEntity){
        UserDTO dto = new UserDTO();
        dto.setID(userEntity.getID());
        dto.setLoginid(userEntity.getLoginid());
        dto.setPassword(userEntity.getPassword());
        dto.setName(userEntity.getName());
        dto.setEmail(userEntity.getEmail());
        dto.setPhone(userEntity.getPhone());
        dto.setAddress(userEntity.getAddress());
        dto.setMemberDate(userEntity.getMemberDate());
        dto.setReginum(userEntity.getReginum());
        dto.setNosign(userEntity.getNosign());

        return dto;
    }

    // Builder를 통한 DTO 생성기
    @Builder
    public UserDTO(Long id, String loginid, String PW, String name,
                   String phone, String email, String address, LocalDate MemberDate, String reginum) {
        this.ID = id;
        this.loginid = loginid;
        this.Password = PW;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.MemberDate = MemberDate;
        this.reginum = reginum;
        this.nosign = false;
    }
}
