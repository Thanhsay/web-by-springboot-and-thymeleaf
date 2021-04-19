package com.example.ndt.projectnumber2.Model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "image")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long image_id;

    @Column(name = "image_path")
    private String image_path;

    @ManyToOne
    @JoinColumn(name = "model_id", nullable = false)
    private Modell image_model_id;
}
