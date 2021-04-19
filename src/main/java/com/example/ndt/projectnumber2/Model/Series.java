package com.example.ndt.projectnumber2.Model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "series")
public class Series {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long series_id;

    @Column(name = "series_name")
    private String series_name;

    @ManyToOne
    @JoinColumn(name = "brand_id", nullable = false)
    private Brand series_brand_id;

    @OneToMany(mappedBy = "model_series_id", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Modell> listSeriesModel;

}
