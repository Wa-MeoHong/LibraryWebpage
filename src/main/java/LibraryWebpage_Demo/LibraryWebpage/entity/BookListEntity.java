/*
 *	File : BookListEntity.java
 *	Name : Shin Dae Hong
 *	Data : 2024 / 01 / 01
 *	objective : Class BookListEntity
 *			Book Table Entity, DB <-> Web..
 *
 *	Modified
 *	=============================================================
 *		1	|	01/01	|	First Write
 *		2	|	01/01	|	Add Logger
 *	=============================================================
 */


package LibraryWebpage_Demo.LibraryWebpage.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@EntityListeners(AutoCloseable.class)
@Table(name = "booklist")
public class BookListEntity {
    /*
     * BookList Table
     *
     * bookid : 책마다 부여된 번호 ( 도서관에서 사용 ) , 기본키
     * ISBN : 책 고유 번호, 13자리숫자
     * */

    @Id
    @Column(unique = true, name = "bookid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookid;

    @Column(name = "isbn")
    private Long isbn;

    // Builder Function, BookListEntity Build
    @Builder
    public BookListEntity(Long bookid, Long isbn){
        this.bookid = bookid;
        this.isbn = isbn;
    }
}
