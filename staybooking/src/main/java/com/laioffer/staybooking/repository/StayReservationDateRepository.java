package com.laioffer.staybooking.repository;

import com.laioffer.staybooking.model.StayReservedDate;
import com.laioffer.staybooking.model.StayReservedDateKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

//从elasticsearch返回的list里搜索给定的时间段内有没有合适的
@Repository
public interface StayReservationDateRepository extends JpaRepository<StayReservedDate, StayReservedDateKey> {
    //Group by 可是让重复的只返回一条
    @Query(value = "SELECT srd.id.stay_id FROM StayReservedDate srd WHERE srd.id.stay_id IN ?1 AND srd.id.date BETWEEN ?2 AND ?3 GROUP BY srd.id.stay_id")
    Set<Long> findByIdInAndDateBetween(List<Long> stayIds, LocalDate startDate, LocalDate endDate);

}

