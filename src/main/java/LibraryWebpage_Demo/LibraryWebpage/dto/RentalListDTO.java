/*
 *	File : RentalListDTO.java
 *	Name : Shin Dae Hong
 *	Data : 2024 / 01 / 01
 *	objective : Class RentalListDTO
 *			RentalList Table DTO, Used in WEB, Back Server
 *
 *	Modified
 *	=============================================================
 *		1	|	01/01	|	First Write
 *		2	|	01/01	|	...
 *	=============================================================
 */

package LibraryWebpage_Demo.LibraryWebpage.dto;


import LibraryWebpage_Demo.LibraryWebpage.entity.RentalListEntity;
import lombok.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
public class RentalListDTO {
    private Long rentalid;
    private Long bookid;
    private Long userid;
    private String rentalstate;
    private LocalDate rentaldate;
    private LocalDate returndate;
    private int extending;

    // DTO -> Entity Transform Function
    public RentalListEntity toEntity() {
        RentalListEntity building = RentalListEntity.builder()
                .rentalid(rentalid)
                .bookid(bookid)
                .userid(userid)
                .rentalstate(rentalstate)
                .rentaldate(rentaldate)
                .returndate(returndate)
                .extending(extending)
                .build();

        return building;
    }

    // Entity -> DTO Transform Function
    public static RentalListDTO toRentalListDTO(RentalListEntity rentalListEntity){
        RentalListDTO dto = new RentalListDTO();
        dto.setRentalid(rentalListEntity.getRentalid());
        dto.setBookid(rentalListEntity.getBookid());
        dto.setUserid(rentalListEntity.getUserid());
        dto.setRentalstate(rentalListEntity.getRentalstate());
        dto.setRentaldate(rentalListEntity.getRentaldate());
        dto.setReturndate(rentalListEntity.getReturndate());
        dto.setExtending(rentalListEntity.getExtending());

        return dto;
    }

    // Builder를 통한 DTO 생성기
    @Builder
    public RentalListDTO(Long rentalid, Long bookid, Long userid, String rentalstate,
                         LocalDate rentaldate, LocalDate returndate, int extending){
        this.rentalid = rentalid;
        this.bookid = bookid;
        this.userid = userid;
        this.rentalstate = rentalstate;
        this.rentaldate = rentaldate;
        this.returndate = returndate;
        this.extending = extending;
    }

}
