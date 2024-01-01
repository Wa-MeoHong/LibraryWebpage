/*
 *	File : BookListRepository.java
 *	Name : Shin Dae Hong
 *	Data : 2024 / 01 / 01
 *	objective : Interface BookRepository
 *			BookList Repository, To create SQL statements that actually interface with the database
 *
 *	Modified
 *	=============================================================
 *		1	|	01/01	|	First Write
 *		2	|	01/01	|	...
 *	=============================================================
 */

package LibraryWebpage_Demo.LibraryWebpage.repository;

import LibraryWebpage_Demo.LibraryWebpage.entity.BookListEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookListRepository extends JpaRepository<BookListEntity, Long> {
    List<BookListEntity> findByisbn(Long isbn);     // Find Book Using ISBN
    void deleteByisbn(Long isbn);       // Delete Book Using ISBN
}

