/*
 *	File : BookListService.java
 *	Name : Shin Dae Hong
 *	Data : 2024 / 01 / 02
 *	objective : Class BookListService
 *			BookList Service, Function class that actually provides the service
 *
 *	Modified
 *	=============================================================
 *		1	|	01/02	|	First Write
 *		2	|	01/02	|	...
 *	=============================================================
 */

package LibraryWebpage_Demo.LibraryWebpage.service;

import LibraryWebpage_Demo.LibraryWebpage.dto.BookDTO;
import LibraryWebpage_Demo.LibraryWebpage.dto.BookListDTO;
import LibraryWebpage_Demo.LibraryWebpage.entity.BookListEntity;
import LibraryWebpage_Demo.LibraryWebpage.repository.BookListRepository;
import LibraryWebpage_Demo.LibraryWebpage.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookListService {
    private final BookListRepository bookListRepository;
    private final BookRepository bookRepository;

    // Save In DataBase
    public Long save (BookListDTO bookListDTO){
        BookListEntity bookListEntity = bookListDTO.toEntity();     // Create Entity
        bookListRepository.save(bookListEntity);            // Save method call

        return bookListEntity.getBookid();      // Return Bookid
    }

    // Delete Data Using method call
    @Transactional
    public void deleteByisbn(Long isbn) {bookListRepository.deleteByisbn(isbn);}

    // 책 데이터 모두를 출력하는 기능
    //  Repository에서 모든 기능을 가져오는 findAll() api를 활용함
    public List<BookListDTO> findAll() {
        List<BookListEntity> bookListEntityList = bookListRepository.findAll();
        List<BookListDTO> bookListDTOList = new ArrayList<>();
        // Entity로 되어있는 데이터를  DTO로 변환
        for(BookListEntity bookListEntity : bookListEntityList) {
            bookListDTOList.add(BookListDTO.toBookListDTO(bookListEntity));     // Add DTO in List
        }
        return bookListDTOList;     // Return DTO List
    }

    // Find BookDTO List Using ISBN
    @Transactional
    public List<BookListDTO> findList(List<BookDTO> bookDTOList) {
        List<BookListDTO> bookListDTOList = new ArrayList<>();

        for (BookDTO bookDTO : bookDTOList){
            // 같은 ISBN을 가진 책 리스트를 가져오게 되므로, 중복 for문을 사용할 것같음.
            List<BookListEntity> byisbn = bookListRepository.findByisbn(bookDTO.getIsbn());     // Find Using ISBN method call

            // 이렇게 나온 같은 ISBN을 가진 책 리스트를 한곳에 모아준다.
            for (BookListEntity bookListEntity : byisbn){
                bookListDTOList.add(BookListDTO.toBookListDTO(bookListEntity));     // Add DTO in List
            }
        }
        return bookListDTOList;     // return BookList DTO List
    }

    // Find Book using Bookid Function
    public BookListDTO findBookBybookid(Long bookid){
        Optional<BookListEntity> byBookid = bookListRepository.findById(bookid);        // Find method Call

        if (byBookid.isPresent()) {
            BookListEntity bookListEntity = byBookid.get();     // Get Data
            BookListDTO dto = BookListDTO.toBookListDTO(bookListEntity);    // DTO Transfer

            return dto;     // Return DTO
        }
        return null;        // if Null, return null
    }

    // Find Book using Title Function
    public String findtitle(Long bookid){
        Optional<BookListEntity> byID = bookListRepository.findById(bookid);        // Find method Call
        if (byID.isPresent())
            return (bookRepository.findById(byID.get().getIsbn())).get().getTitle();    // Return title Using find method Call
        return null;        // If null, Return null
    }
}
