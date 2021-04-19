package com.example.ndt.projectnumber2.Model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "model")
public class Modell {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long model_id;

    @Column(name = "model_name")
    private String model_name;

    @Column(name = "model_description")
    private String model_description;

    @Column(name = "model_status")
    private Long model_status;

    @Column(name = "model_price")
    private float modelPrice;

    @Column(name = "model_time")
    private String model_time;

    @Column(name = "model_image")
    private String model_image;

    @ManyToOne
    @JoinColumn(name = "series_id", nullable = false)
    private Series model_series_id;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category model_category_id;

    @OneToMany(mappedBy = "image_model_id", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Image> listModelImage;
}
