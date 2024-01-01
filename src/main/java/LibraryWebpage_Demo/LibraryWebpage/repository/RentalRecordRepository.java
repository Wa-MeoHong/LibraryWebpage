/*
 *	File : RentalRecordRepository.java
 *	Name : Shin Dae Hong
 *	Data : 2024 / 01 / 01
 *	objective : Interface BookRepository
 *			RentalRecord Repository, To create SQL statements that actually interface with the database
 *
 *	Modified
 *	=============================================================
 *		1	|	01/01	|	First Write
 *		2	|	01/01	|	...
 *	=============================================================
 */

package LibraryWebpage_Demo.LibraryWebpage.repository;

import LibraryWebpage_Demo.LibraryWebpage.entity.RentalRecordEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface RentalRecordRepository extends JpaRepository<RentalRecordEntity, Long> {
    Optional<RentalRecordEntity> findByuserid(Long userid);     // Find Using userID
}
