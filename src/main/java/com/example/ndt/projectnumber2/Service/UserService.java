package com.example.ndt.projectnumber2.Service;

import com.example.ndt.projectnumber2.Jpa.*;
import com.example.ndt.projectnumber2.Model.Brand;
import com.example.ndt.projectnumber2.Model.Image;
import com.example.ndt.projectnumber2.Model.Modell;
import com.example.ndt.projectnumber2.Model.Series;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    ModelJpa modelJpa;
    @Autowired
    BrandJpa brandJpa;
    @Autowired
    SeriesJpa seriesJpa;
    @Autowired
    ImageJpa imageJpa;

    //Paginate all
    public Page<Modell> modelFindAll(Pageable pageable){
       return (Page<Modell>) modelJpa.findAll(pageable);
    }

    //Paginate and Sort all
    public Page<Modell> modelFindAndSort(int page, int offset){
        //Sort sort = Sort.by(Sort.Direction.ASC, "modelPrice").descending();
        Pageable pageable = PageRequest.of(page - 1, offset, Sort.by("modelPrice").ascending());
        return modelJpa.findAll(pageable);
    }

    //Search by string
    public List<Modell> modelFindByString(String name){
        return modelJpa.findModellByModel_name("%"+name+"%");
    }

    public List<Brand> brandFindAll(){
        return brandJpa.findAll();
    }
    public List<Series> seriesFindAll(){
        return (List<Series>) seriesJpa.findAll();
    }
    public List<Image> imageFindAll(){
        return imageJpa.findAll();
    }
    public Modell modelFindById(Long id){
        return modelJpa.findById(id).get();
    }
    public Brand brandFindById(Long id){
        return brandJpa.findById(id).get();
    }

    public Series seriesFindById(Long id){
        return seriesJpa.findById(id).get();
    }
    public List<Series> seriesFindByBrand(Long id){
        return seriesJpa.findSeriesBySeries_brand_id(id);
    }
    public List<Modell> modellFindBySeries(Long id){
        return modelJpa.findAllBySeries_id(id);
    }
    public Long modelTotal(){
        return modelJpa.getAllModell();
    }
}
