/*
 *	File : BookEntity.java
 *	Name : Shin Dae Hong
 *	Data : 2024 / 01 / 01
 *	objective : Class BookEntity
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
import java.time.LocalDate;


@Entity
@NoArgsConstructor
@EntityListeners(AutoCloseable.class)
@Table(name = "book")
@Data
public class BookEntity {
    /*
     * Book Table
     *
     * ISBN : 기본키, 책마다 부여된 번호 (완전 똑같은 책은 똑같음) ,13자리이기 때문에, LONG(BIGINT)
     * author :  작가, 45자 제한
     * PublishDate : 출판날짜, Date
     * Publisher : 출판사, 한글입력 가능, 45자 제한
     * Title : 제목, 80자 제한
     *
     * */

    @Id
    @Column(name = "isbn")
    private Long isbn;

    @Column(length = 45, name = "author")
    private String author;
    @Column(length = 80, name = "title")
    private String title;
    @Column(length = 45, name = "publisher")
    private String publisher;
    @Column(name = "publishdate")
    private LocalDate publishdate;

    // Builder Function, BookEntity Build
    @Builder
    public BookEntity(Long isbn, String author, String title,
                      String publisher, LocalDate publishdate){
        this.isbn = isbn;
        this.author = author;
        this.title = title;
        this.publisher = publisher;
        this.publishdate = publishdate;
    }
}
