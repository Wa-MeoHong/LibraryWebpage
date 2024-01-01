package LibraryWebpage_Demo.LibraryWebpage.service;

import LibraryWebpage_Demo.LibraryWebpage.dto.BookDTO;
import LibraryWebpage_Demo.LibraryWebpage.entity.BookEntity;
import LibraryWebpage_Demo.LibraryWebpage.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    // Save In DataBase
    public Long save (BookDTO bookDTO) {
        BookEntity bookEntity = bookDTO.toEntity();     // Create Entity
        bookRepository.save(bookEntity);        // Repository의 save 매서드 호출함.
        return bookEntity.getIsbn();        // Return ISBN
    }

    // Delete in Database
    @Transactional
    public void deleteByisbn(Long isbn){
        bookRepository.deleteByisbn(isbn);      // Delete Method call
    }

    // BookList Find All Data
    public List<BookDTO> findAll() {
        List<BookEntity> bookEntitylist = bookRepository.findAll();
        // Entity를 DTO로 변환하여 전해야함. 이 때, for문을 활용해 전부 바꾼다. (개수 상관 없이)
        List<BookDTO> bookDTOList = new ArrayList<>();          // ArrayList로 전달.
        for (BookEntity bookEntity : bookEntitylist) {
            bookDTOList.add(BookDTO.toBookDTO(bookEntity));     // ArrayList에 저장
        }
        return bookDTOList;     // Return BookDTO List
    }

    // 타이틀 키워드에 맞춰서 키워드를 포함하는 제목을 가진 책 종류들을 가져온다.
    // 참고로, ISBN이 다른 같은 책들도 가져오게 된다.
    @Transactional
    public List<BookDTO> search(String keyword){
        List<BookEntity> bytitleContaining = bookRepository.findBytitleContaining(keyword);     // Find Using title method Call
        if(bytitleContaining.isEmpty()){
            return null;        // if Null, return null
        }
        List<BookDTO> bookDTOList = new ArrayList<>();      // make ArrayList
        for (BookEntity bookEntity : bytitleContaining) {
            bookDTOList.add(BookDTO.toBookDTO(bookEntity));     // add DTO in List
        }
        return bookDTOList;     // Return BookDTO List
    }

    // Find Book Using ISBN
    public BookDTO findByisbn(Long isbn) {
        Optional<BookEntity> byisbn = bookRepository.findById(isbn);
        if ( byisbn.isPresent()){
            BookEntity bookEntity = byisbn.get();               // Get Tuple using isbn
            BookDTO dto = BookDTO.toBookDTO(bookEntity);        // Entity -> dto
            return dto;             // Return Book DTO
        }else {
            return null;            // if null, return null
        }
    }
}
