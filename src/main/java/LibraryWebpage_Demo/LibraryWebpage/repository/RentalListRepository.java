/*
 *	File : RentalListRepository.java
 *	Name : Shin Dae Hong
 *	Data : 2024 / 01 / 01
 *	objective : Interface BookRepository
 *			RentalList Repository, To create SQL statements that actually interface with the database
 *
 *	Modified
 *	=============================================================
 *		1	|	01/01	|	First Write
 *		2	|	01/01	|	...
 *	=============================================================
 */

package LibraryWebpage_Demo.LibraryWebpage.repository;

import LibraryWebpage_Demo.LibraryWebpage.entity.RentalListEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface RentalListRepository extends JpaRepository<RentalListEntity, Long> {
    List<RentalListEntity> findByuserid(Long userid);       // Find Rental Tuple Using userID
    List<RentalListEntity> findBybookid(Long bookid);       // Find Rental Tuple Using bookID
}
