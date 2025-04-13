package com.tsm.repository;

import com.tsm.entity.ItemC;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;

public interface ItemCRepository extends JpaRepository<ItemC, Long> {

    @Transactional
    @Modifying
    @Query( " delete FROM ItemC c where c.q=?1 ")
    int deleteByQ(String Q);

    @Query(nativeQuery = true, value = " SELECT DISTINCT(ItemNum) FROM ItemC  where q=?1 ")
    HashSet<String> getHaveItemInDBList(String Q);
/*
    @Query(nativeQuery = true, value = " SELECT count(*) FROM foceItem where ver=?1 and checktt=?2")
    int hasCheckRetId(String ver, String hasCode);
*/

/*    @Transactional
    @Modifying
    @Query("update HasCEntity h set h.checktt = ?1 where h.ver = ?2")
    int updateIdByIdAllIgnoreCase(String checktt, String ver);*/

}
