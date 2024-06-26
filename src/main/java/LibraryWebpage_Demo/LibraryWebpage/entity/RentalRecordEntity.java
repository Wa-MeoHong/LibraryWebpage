/*
 *	File : RentalRecordEntity.java
 *	Name : Shin Dae Hong
 *	Data : 2024 / 01 / 01
 *	objective : Class RentalRecordEntity
 *			RentalRecord Table Entity, DB <-> Web..
 *
 *	Modified
 *	=============================================================
 *		1	|	01/01	|	First Write
 *		2	|	01/01	|	...
 *	=============================================================
 */

package LibraryWebpage_Demo.LibraryWebpage.entity;

import jakarta.persistence.*;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import java.time.LocalDate;

@Entity
@EntityListeners(AutoCloseable.class)
@NoArgsConstructor
@Data
@Table(name = "rentalrecordlist")
public class RentalRecordEntity {
    /*
        Rental Record list Table

        Rental ID = 메인 기본키, 예약번호로도 쓰이며, 대출번호로도 바뀌기도 한다. 예약리스트를 그대로 대출로 업데이트 하기 때문
        Book ID = 외래키 1, 책 아이디를 참조, 책의 고유번호이기 때문에, 이걸로 ISBN, 책정보까지 전부 찾을 수 있음
        User ID = 외래키 2, 유저 아이디를 참조, 유저 고유번호
        Rental State : "예약중, 대출중, 연체, 반납완료"로 구분할 계획
        Rental Date : 예약한 날짜가 될 수 있고, 대출을 하였으면, 대출날짜로 갱신한다.
        Return Date : 대출받은 날짜에서, 1주일 뒤의 날짜를 반납날짜로 한다. 만약, 연장을 하게 된다면, 거기서 1주일 더 연장한다.
        extending : 연장한 횟수를 가리킨다. 이 때, 처음은 무조건 0.
        rentalDeadline : 얼마나 남았는가를 재고 있음.
     */

    @Id
    @Column(unique = true, name = "recordnum")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long recordnum;

    @Column(name = "rentalid")
    private Long rentalid;

    @Column(name = "bookid")
    private Long bookid;
    @Column(name = "userid")
    private Long userid;
    @Column(length = 13, name = "rentalstate")
    private String rentalstate;

    @Column(name = "rentaldate")
    private LocalDate rentaldate;
    @Column(name = "returndate")
    private LocalDate returndate;
    @Column(name = "extending")
    @ColumnDefault("0")
    private int extending;

    @Column(name = "rentaldeadline")
    private int rentaldeadline;

    // Builder Function, RentalRecordEntity Build
    @Builder
    public RentalRecordEntity(Long recordnum, Long rentalid, Long bookid, Long userid, String rentalstate,
                              LocalDate rentaldate, LocalDate returndate, int rentaldeadline, int extending){
        this.recordnum = recordnum;
        this.rentalid = rentalid;
        this.bookid = bookid;
        this.userid = userid;
        this.rentalstate = rentalstate;
        this.rentaldate = rentaldate;
        this.returndate = returndate;
        this.extending = extending;
        this.rentaldeadline = rentaldeadline;
    }
}
