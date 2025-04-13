package com.tsm.repository;

import com.tsm.entity.Dirnew;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DirnewRepository extends JpaRepository<Dirnew, Long> {


/*
    @Query(nativeQuery = true, value = " SELECT count(*) FROM foceItem where ver=?1 and checktt=?2")
    int hasCheckRetId(String ver, String hasCode);
*/

/*    @Transactional
    @Modifying
    @Query("update HasCEntity h set h.checktt = ?1 where h.ver = ?2")
    int updateIdByIdAllIgnoreCase(String checktt, String ver);*/

    @Query(nativeQuery = true, value = "SELECT 1 FROM dirnew WHERE pathhash = ?1 and lastfixhash= ?2 LIMIT 1")
    String countByHashCode(String pathhash, String lastfixhash);


}
