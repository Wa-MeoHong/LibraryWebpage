/*
 *	File : RentalListService.java
 *	Name : Shin Dae Hong
 *	Data : 2024 / 01 / 02
 *	objective : Class RentalListService
 *			RentalList Service, Function class that actually provides the service
 *
 *	Modified
 *	=============================================================
 *		1	|	01/02	|	First Write
 *		2	|	01/02	|	...
 *	==============================================================
 */

package LibraryWebpage_Demo.LibraryWebpage.service;

import LibraryWebpage_Demo.LibraryWebpage.dto.RentalListDTO;
import LibraryWebpage_Demo.LibraryWebpage.dto.ReserveDTO;
import LibraryWebpage_Demo.LibraryWebpage.entity.RentalListEntity;
import LibraryWebpage_Demo.LibraryWebpage.entity.UserEntity;
import LibraryWebpage_Demo.LibraryWebpage.repository.RentalListRepository;
import LibraryWebpage_Demo.LibraryWebpage.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RentalListService {
    private final RentalListRepository rentalListRepository;
    private final UserRepository userRepository;

    // Save In DataBase
    public Long save (RentalListDTO rentalListDTO){
        RentalListEntity rentalListEntity = rentalListDTO.toEntity();   // Create Entity
        rentalListRepository.save(rentalListEntity);                    // Save method call

        return rentalListEntity.getRentalid();          // return rental ID
    }

    // Update RentalState (Booked..)
    public Long save(Long bookid, Long userid){
        RentalListEntity rentalListEntity = RentalListEntity.builder()
                .userid(userid)
                .bookid(bookid)
                .rentalstate("예약")      // update Rental State (Booked)
                .returndate(LocalDate.now().plusDays(14))       // update return Date
                .build();
        rentalListRepository.save(rentalListEntity);        // Save method call
        return rentalListEntity.getBookid();        // return Book ID
    }

    // Find All Rental Tuple List Function
    public List<RentalListDTO> findAll() {
        List<RentalListEntity> rentalEntityList = rentalListRepository.findAll();   // method Call
        List<RentalListDTO> rentalListDTOList = new ArrayList<>();      // new Array
        // Entity를 DTO로 변환
        for(RentalListEntity rentalListEntity : rentalEntityList){
            rentalListDTOList.add(RentalListDTO.toRentalListDTO(rentalListEntity));     // Add DTO in List
        }
        // 리스트 반환
        return rentalListDTOList;       // Return Rental Tuple List
    }


    // Delete Data Using rentalID
    public void delete(Long rentalid){
        rentalListRepository.deleteById(rentalid);      // Delete method call
    }

    // Search Rental Tuple Using LoginID
    public List<RentalListDTO> search(String keyword){
        List<RentalListDTO> lists = new ArrayList<>();
        List<UserEntity> byID = userRepository.findByloginidContaining(keyword);        // find method Call

        // if Login ID is Null
        if(byID.isEmpty()) {
            return null;        // return null
        }
        // All List Transfer DTO
        for ( UserEntity user : byID){
            List<RentalListEntity> rentals = rentalListRepository.findByuserid(user.getID());   // find method call
            for (RentalListEntity rental : rentals){
                lists.add(RentalListDTO.toRentalListDTO(rental));       // Add DTO in List
            }
        }
        return lists;       // Return Rental List
    }

    // Find Rental Tuple Using RentalID
    public RentalListDTO findByid(Long rentalid){
        Optional<RentalListEntity> byrentalid = rentalListRepository.findById(rentalid);  // Rental Tuple Entity Get
        if(byrentalid.isPresent()){
            RentalListDTO dto = RentalListDTO.toRentalListDTO(byrentalid.get());        // Rental DTO Transfer
            return dto;     // return DTO
        }
        return null;        // If data is Null, return null
    }

    @Transactional
    // Rental 상태 변경, 대출 혹은 반납
    public String update(Long rentalid, String rentalState){
        RentalListEntity rental = rentalListRepository.findById(rentalid).get();        // Rental Tuple Get

        // State 대출,
        if (rentalState.equals("대출")) {
            rental.setRentalstate(rentalState);
            rental.setRentaldate(LocalDate.now());
            rental.setReturndate(LocalDate.now().plusDays(7));
            return "대출 완료";
        }
        // 연장인 경우, 반납기한을 일주일 더 연장.
        else if (rentalState.equals("대출(연장)")){
            if (rental.getExtending() < 2){
                rental.setRentalstate(rentalState);
                rental.setReturndate(rentalListRepository.findById(rentalid).get().getReturndate().plusDays(7));
                rental.setExtending(rental.getExtending()+1);
                return "연장 완료";
            }
            return "연장횟수 초과로 연장 불가능";
        }
        // 마지막, 반납일 경우
        else if (rentalState.equals("반납")){
            rental.setRentalstate(rentalState);
            rental.setReturndate(LocalDate.now());      // 반납한 시점의 날짜로 기록한다.
            return "반납 완료";
        }
        return "오류";        // if Error, return Error
    }


    // RentalList의 내용들을 bookid -> booktitle, userid -> loginid로 새로 교체
    public ReserveDTO createReserve(RentalListDTO rentalDTO, String title, String loginid) {
        ReserveDTO build =  ReserveDTO.builder()
                .rentalid( rentalDTO.getRentalid())
                .booktitle(title)
                .loginid(loginid)
                .rentalstate( rentalDTO.getRentalstate())
                .rentaldate( rentalDTO.getRentaldate())
                .returndate( rentalDTO.getReturndate())
                .extending( rentalDTO.getExtending())
                .build();
        return build;
    }
}
