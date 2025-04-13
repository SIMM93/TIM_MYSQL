package com.tsm.repository;

import com.tsm.entity.ItemStrEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.HashSet;

public interface ItemRepository extends JpaRepository<ItemStrEntity, Long> {


    @Query(nativeQuery = true, value = " SELECT count(*) FROM itemStr where itemNum=?1")
    int isHaveItem(String item);

    @Query(nativeQuery = true, value = " SELECT itemNum FROM itemStr   ")
    HashSet<String> ItemNumListInDB();


}
