package com.example.ndt.projectnumber2.Model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "brand")
public class Brand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long brand_id;

    @Column(name = "brand_name")
    private String brand_name;

    @OneToMany(mappedBy = "series_brand_id", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Series> listBrandSeries;



}
