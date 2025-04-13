package com.tsm.repository;

import com.tsm.entity.QSale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.HashSet;

public interface QSaleRepository extends JpaRepository<QSale, Long> {

    @Query(nativeQuery = true, value = " SELECT hashCode FROM QSale  where Q=?1 ")
    HashSet<String> getAllHashCode(String q);

/*
    @Query(nativeQuery = true, value = " SELECT count(*) FROM foceItem where ver=?1 and checktt=?2")
    int hasCheckRetId(String ver, String hasCode);
*/

/*    @Transactional
    @Modifying
    @Query("update HasCEntity h set h.checktt = ?1 where h.ver = ?2")
    int updateIdByIdAllIgnoreCase(String checktt, String ver);*/

}
