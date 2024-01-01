/*
 *	File : BookDTO.java
 *	Name : Shin Dae Hong
 *	Data : 2024 / 01 / 01
 *	objective : Class BookDTO
 *			Book Table DTO, Used in WEB, Back Server
 *
 *	Modified
 *	=============================================================
 *		1	|	01/01	|	First Write
 *		2	|	01/01	|	Add Logger
 *	=============================================================
 */

package LibraryWebpage_Demo.LibraryWebpage.dto;

import LibraryWebpage_Demo.LibraryWebpage.entity.BookEntity;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class BookDTO {

    private Long isbn;
    private String author;
    private String title;
    private String publisher;
    private LocalDate publishdate;

    // DTO -> Entity Transform Function
    public BookEntity toEntity(){
        BookEntity building = BookEntity.builder()
                .isbn(isbn)
                .author(author)
                .title(title)
                .publisher(publisher)
                .publishdate(publishdate)
                .build();

        return building;
    }


    // Entity -> DTO Transform Function
    public static BookDTO toBookDTO(BookEntity bookEntity){
        BookDTO dto = new BookDTO();

        dto.setIsbn(bookEntity.getIsbn());
        dto.setAuthor(bookEntity.getAuthor());
        dto.setTitle(bookEntity.getTitle());
        dto.setPublisher(bookEntity.getPublisher());
        dto.setPublishdate(bookEntity.getPublishdate());

        return dto;
    }

    // Builder를 통한 DTO 생성기
    @Builder
    public BookDTO (Long isbn, String author, String title,
                    String publisher, LocalDate publishdate){
        this.isbn = isbn;
        this.author = author;
        this.title = title;
        this.publisher = publisher;
        this.publishdate = publishdate;
    }

}
