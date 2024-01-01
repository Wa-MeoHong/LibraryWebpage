/*
 *	File : RentalRecordService.java
 *	Name : Shin Dae Hong
 *	Data : 2024 / 01 / 02
 *	objective : Class RentalRecordService
 *			RentalRecord Service, Function class that actually provides the service
 *
 *	Modified
 *	=============================================================
 *		1	|	01/02	|	First Write
 *		2	|	01/02	|	...
 *	==============================================================
 */

package LibraryWebpage_Demo.LibraryWebpage.service;

import LibraryWebpage_Demo.LibraryWebpage.dto.RentalListDTO;
import LibraryWebpage_Demo.LibraryWebpage.dto.RentalRecordDTO;
import LibraryWebpage_Demo.LibraryWebpage.entity.RentalRecordEntity;
import LibraryWebpage_Demo.LibraryWebpage.repository.RentalRecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RentalRecordService {
    private final RentalRecordRepository rentalRecordRepository;

    // Save Data in RentalRecord
    public Long save (RentalRecordDTO rentalRecordDTO){
        RentalRecordEntity rentalRecordEntity = rentalRecordDTO.toEntity();     // DTO -> Entity
        rentalRecordRepository.save(rentalRecordEntity);        // Save Method call

        return rentalRecordEntity.getRecordnum();       // return Record Num
    }

    // Save DataBase Using RentalListDTO ( Return Book )
    public Long save(RentalListDTO rentalListDTO){
        int deadline = 0;
        // if 반납 상태, deadLine을 0로 설정한다. 아니면, deadline을 새로 계산함.
        if(rentalListDTO.getRentalstate().equals("반납")){
            deadline = 0;
        } else {
            long daysBetween = ChronoUnit.DAYS.between(rentalListDTO.getRentaldate(), rentalListDTO.getReturndate());
            deadline = (int)daysBetween;
        }

        // RenalRecordEntity Make, Using Builder
        RentalRecordEntity rentalRecordEntity = RentalRecordEntity.builder()
                .rentalid(rentalListDTO.getRentalid())
                .bookid(rentalListDTO.getBookid())
                .userid(rentalListDTO.getUserid())
                .rentalstate(rentalListDTO.getRentalstate())
                .rentaldate(rentalListDTO.getRentaldate())
                .returndate(rentalListDTO.getReturndate())
                .extending(rentalListDTO.getExtending())
                .rentaldeadline(deadline)
                .build();

        rentalRecordRepository.save(rentalRecordEntity);        // Save method call
        return rentalRecordEntity.getRecordnum();       // return RecordNum
    }

    // Find Record Using userID
    public RentalRecordDTO findByuserid(Long userid){
        Optional<RentalRecordEntity> byuserid = rentalRecordRepository.findByuserid(userid);    // Find Method
        if(byuserid.isPresent()){
            RentalRecordDTO dto = RentalRecordDTO.toRecordDTO(byuserid.get());      // DTO Transform
            return dto;     // Return DTO
        }
        return null;        // return Null
    }
}
