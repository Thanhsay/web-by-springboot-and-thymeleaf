package com.example.ndt.projectnumber2.Jpa;

import com.example.ndt.projectnumber2.Model.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandJpa extends JpaRepository<Brand, Long> {
}
