package com.tsm.repository;

import com.tsm.entity.TsmScan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
@Repository

public interface TsmScanRepository extends JpaRepository<TsmScan, Long> {


    /**
     * 寻找区内是否是最新数据
     * 大于0 则不是最新的
     *
     * @param Q
     * @param time
     * @return
     */
    @Query(nativeQuery = true, value = "select COALESCE(max(lastScan),0) < ?2 FROM TsmScan  where Q=?1 ")
    int q_insert_isLast(String Q, String time);

    @Modifying
    @Transactional
    @Query(value = "UPDATE TsmScan e SET e.isLast = 0 WHERE e.q = ?1")
    int update_Last(String Q);
}
