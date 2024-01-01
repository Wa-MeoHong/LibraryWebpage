/*
 *	File : ReserveDTO.java
 *	Name : Shin Dae Hong
 *	Data : 2024 / 01 / 02
 *	objective : Class ReserveDTO
 *			Reserve DTO, Used in WEB, Back Server Only Use in RentalListDTO Transfer
 *
 *	Modified
 *	=============================================================
 *		1	|	01/02	|	First Write
 *		2	|	01/02	|	...
 *	=============================================================
 */

package LibraryWebpage_Demo.LibraryWebpage.dto;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@NoArgsConstructor
public class ReserveDTO {
    private Long rentalid;
    private String booktitle;       // 책 id 대신, 책 제목을 보여줌
    private String Loginid;         // 회원번호 대신, 회원 아이디를 보여줌
    private String rentalstate;
    private LocalDate rentaldate;
    private LocalDate returndate;
    private int extending;

    // Builder를 통한 DTO 생성기
    @Builder
    public ReserveDTO(Long rentalid, String booktitle, String loginid, String rentalstate,
                      LocalDate rentaldate, LocalDate returndate, int extending){
        this.rentalid = rentalid;
        this.booktitle = booktitle;
        this.Loginid = loginid;
        this.rentaldate = rentaldate;
        this.returndate = returndate;
        this.rentalstate = rentalstate;
        this.extending = extending;
    }
}
