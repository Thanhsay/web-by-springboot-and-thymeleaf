package com.example.ndt.projectnumber2.Jpa;

import com.example.ndt.projectnumber2.Model.Series;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeriesJpa extends CrudRepository<Series, Long>, PagingAndSortingRepository<Series, Long> {
    @Query("SELECT u from Series u WHERE u.series_brand_id.brand_id =?1")
    List<Series> findSeriesBySeries_brand_id(Long id);
}
