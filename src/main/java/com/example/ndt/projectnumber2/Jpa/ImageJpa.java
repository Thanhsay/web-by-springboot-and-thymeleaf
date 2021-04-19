package com.example.ndt.projectnumber2.Jpa;

import com.example.ndt.projectnumber2.Model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageJpa extends JpaRepository<Image, Long> {
}
