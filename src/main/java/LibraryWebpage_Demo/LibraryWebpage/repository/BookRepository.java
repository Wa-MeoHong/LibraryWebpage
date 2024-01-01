/*
 *	File : BookRepository.java
 *	Name : Shin Dae Hong
 *	Data : 2024 / 01 / 01
 *	objective : Interface BookRepository
 *			Book Repository, To create SQL statements that actually interface with the database
 *
 *	Modified
 *	=============================================================
 *		1	|	01/01	|	First Write
 *		2	|	01/01	|	...
 *	=============================================================
 */

package LibraryWebpage_Demo.LibraryWebpage.repository;

import LibraryWebpage_Demo.LibraryWebpage.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<BookEntity, Long> {
    // 각각 ISBN을 제외한, 작가명, 제목, 출판사명으로 검색(리스트 전체)
    List<BookEntity> findByauthor(String author);
    List<BookEntity> findBytitle(String title);
    List<BookEntity> findBytitleContaining(String title_keyword);
    List<BookEntity> findBypublisher(String publisher);

    void deleteByisbn(Long isbn);

}
