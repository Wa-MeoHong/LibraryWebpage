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

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BookListDTO {
    private Long bookid;
    private Long isbn;

    public BookListEntity toEntity() {
        BookListEntity build = BookListEntity.builder()
                .bookid(bookid)
                .isbn(isbn)
                .build();
        return build;
    }

    public static BookListDTO toBookListDTO(BookListEntity bookListEntity){
        BookListDTO dto = new BookListDTO();

        dto.setBookid(bookListEntity.getBookid());
        dto.setIsbn(bookListEntity.getIsbn());

        return dto;
    }

    @Builder
    public BookListDTO (Long bookid, Long isbn) {
        this.bookid = bookid;
        this.isbn = isbn;
    }
}
