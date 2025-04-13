package com.tsm.repository;

import com.tsm.entity.hasC;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface HasCheckRepository extends JpaRepository<hasC, Long> {


    @Query(nativeQuery = true, value = " SELECT count(*) FROM HasC where ver=?1 and checktt=?2")
    int hasCheckRetId(String ver, String hasCode);

    @Transactional
    @Modifying
    @Query("update hasC h set h.checktt = ?1 where h.ver = ?2")
    int updateIdByIdAllIgnoreCase(String checktt, String ver);

}
