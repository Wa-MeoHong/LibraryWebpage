/*
 *	File : RentalRecordDTO.java
 *	Name : Shin Dae Hong
 *	Data : 2024 / 01 / 01
 *	objective : Class RentalRecordDTO
 *			RentalRecord Table DTO, Used in WEB, Back Server
 *
 *	Modified
 *	=============================================================
 *		1	|	01/01	|	First Write
 *		2	|	01/01	|	...
 *	=============================================================
 */

package LibraryWebpage_Demo.LibraryWebpage.dto;

import LibraryWebpage_Demo.LibraryWebpage.entity.RentalRecordEntity;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@NoArgsConstructor
public class RentalRecordDTO {
    private Long recordnum;
    private Long rentalid;
    private Long bookid;
    private Long userid;
    private String rentalstate;
    private LocalDate rentaldate;
    private LocalDate returndate;
    private int rentaldeadline;
    private int extending;

    // DTO -> Entity Transform Function
    public RentalRecordEntity toEntity(){
        RentalRecordEntity build = RentalRecordEntity.builder()
                .recordnum(recordnum)
                .rentalid(rentalid)
                .bookid(bookid)
                .userid(userid)
                .rentalstate(rentalstate)
                .rentaldate(rentaldate)
                .returndate(returndate)
                .extending(extending)
                .rentaldeadline(rentaldeadline)
                .build();

        return build;
    }

    // Entity -> DTO Transform Function
    public static RentalRecordDTO toRecordDTO(RentalRecordEntity rentalRecordEntity){
        RentalRecordDTO dto = new RentalRecordDTO();
        dto.setRecordnum(rentalRecordEntity.getRecordnum());
        dto.setRentalid(rentalRecordEntity.getRentalid());
        dto.setBookid(rentalRecordEntity.getBookid());
        dto.setUserid(rentalRecordEntity.getUserid());
        dto.setRentalstate(rentalRecordEntity.getRentalstate());
        dto.setRentaldate(rentalRecordEntity.getRentaldate());
        dto.setReturndate(rentalRecordEntity.getReturndate());
        dto.setExtending(rentalRecordEntity.getExtending());
        dto.setRentaldeadline(rentalRecordEntity.getRentaldeadline());
        return dto;
    }

    // Builder를 통한 DTO 생성기
    @Builder
    public RentalRecordDTO(Long recordnum, Long rentalid, Long bookid, Long userid, String rentalstate,
                           LocalDate rentalDate, LocalDate returndate, int exteding, int rentaldeadline){
        this.recordnum = recordnum;
        this.rentalid = rentalid;
        this.bookid = bookid;
        this.userid = userid;
        this.rentalstate = rentalstate;
        this.rentaldate = rentalDate;
        this.returndate  = returndate;
        this.extending = exteding;
        this.rentaldeadline = rentaldeadline;
    }

}
