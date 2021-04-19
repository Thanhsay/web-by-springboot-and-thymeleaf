package com.example.ndt.projectnumber2.Jpa;

import com.example.ndt.projectnumber2.Model.Modell;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ModelJpa extends CrudRepository<Modell, Long>, PagingAndSortingRepository<Modell, Long> {
    @Query("SELECT u from Modell u WHERE u.model_series_id.series_id = ?1")
    List<Modell> findAllBySeries_id(Long id);

    @Query("SELECT count (u) FROM Modell u")
    public Long getAllModell();

    @Query("SELECT u from Modell u WHERE u.model_name like ?1")
    List<Modell> findModellByModel_name(String name);
}
