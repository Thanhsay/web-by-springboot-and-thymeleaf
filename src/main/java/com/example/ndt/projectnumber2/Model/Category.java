package com.example.ndt.projectnumber2.Model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long category_id;

    @Column(name = "category_name")
    private String category_name;

    @OneToMany(mappedBy = "model_category_id", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Modell> listCategoryModel;

}
