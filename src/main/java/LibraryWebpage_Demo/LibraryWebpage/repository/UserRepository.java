/*
 *	File : UserRepository.java
 *	Name : Shin Dae Hong
 *	Data : 2024 / 01 / 01
 *	objective : Interface UserRepository
 *			User Repository, To create SQL statements that actually interface with the database
 *
 *	Modified
 *	=============================================================
 *		1	|	01/01	|	First Write
 *		2	|	01/01	|	...
 *	=============================================================
 */

package LibraryWebpage_Demo.LibraryWebpage.repository;

import LibraryWebpage_Demo.LibraryWebpage.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByloginid(String loginID);     // 로그인 ID로 조회

    List<UserEntity> findByloginidContaining(String loginID);   // 나머지 다 찾음
    Optional<UserEntity> findByemail(String email);     // 이메일로 조회
    Optional<UserEntity> findByname(String name);       // 이름으로 조회

    void deleteByloginid(String loginid);           // 로그인아이디으로 삭제
    boolean existsByloginid(String loginid);        // 로그인 아이디 중복 존재확인

}
