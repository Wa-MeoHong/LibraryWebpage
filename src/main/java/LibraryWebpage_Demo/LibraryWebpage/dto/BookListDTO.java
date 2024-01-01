/*
 *	File : BookListDTO.java
 *	Name : Shin Dae Hong
 *	Data : 2024 / 01 / 01
 *	objective : Class BookListDTO
 *			BookList Table DTO, Used in WEB, Back Server
 *
 *	Modified
 *	=============================================================
 *		1	|	01/01	|	First Write
 *		2	|	01/01	|	...
 *	=============================================================
 */

package LibraryWebpage_Demo.LibraryWebpage.dto;

import LibraryWebpage_Demo.LibraryWebpage.entity.BookListEntity;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BookListDTO {
    private Long bookid;
    private Long isbn;

    // DTO -> Entity Transform Function
    public BookListEntity toEntity() {
        BookListEntity build = BookListEntity.builder()
                .bookid(bookid)
                .isbn(isbn)
                .build();
        return build;
    }

    // Entity -> DTO Transform Function
    public static BookListDTO toBookListDTO(BookListEntity bookListEntity){
        BookListDTO dto = new BookListDTO();

        dto.setBookid(bookListEntity.getBookid());
        dto.setIsbn(bookListEntity.getIsbn());

        return dto;
    }

    // Builder를 통한 DTO 생성기
    @Builder
    public BookListDTO (Long bookid, Long isbn) {
        this.bookid = bookid;
        this.isbn = isbn;
    }
}
