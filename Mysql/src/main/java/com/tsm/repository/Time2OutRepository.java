package com.tsm.repository;

import com.tsm.entity.Timt2out;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface Time2OutRepository extends JpaRepository<Timt2out, Long> {


    @Query(nativeQuery = true, value = "SELECT s.* FROM Timt2out s WHERE s.q = ?1")
    List<Timt2out> gettimeOut(String Q);
}
