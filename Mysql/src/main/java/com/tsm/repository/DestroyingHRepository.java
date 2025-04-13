package com.tsm.repository;

import com.tsm.entity.DestroyingH;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.HashSet;

public interface DestroyingHRepository extends JpaRepository<DestroyingH, Long> {

    @Query(nativeQuery = true, value = " SELECT hashCode FROM destroyingH   ")
    HashSet<String> getAllHashCode();
/*
    @Query(nativeQuery = true, value = " SELECT count(*) FROM foceItem where ver=?1 and checktt=?2")
    int hasCheckRetId(String ver, String hasCode);
*/

/*    @Transactional
    @Modifying
    @Query("update HasCEntity h set h.checktt = ?1 where h.ver = ?2")
    int updateIdByIdAllIgnoreCase(String checktt, String ver);*/

}
