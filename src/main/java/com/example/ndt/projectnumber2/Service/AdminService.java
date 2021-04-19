package com.example.ndt.projectnumber2.Service;

import com.example.ndt.projectnumber2.Jpa.BrandJpa;
import com.example.ndt.projectnumber2.Jpa.ImageJpa;
import com.example.ndt.projectnumber2.Jpa.ModelJpa;
import com.example.ndt.projectnumber2.Jpa.SeriesJpa;
import com.example.ndt.projectnumber2.Model.Modell;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@Service
public class AdminService {

    @Autowired
    BrandJpa brandJpa;
    @Autowired
    ImageJpa imageJpa;
    @Autowired
    ModelJpa modelJpa;
    @Autowired
    SeriesJpa seriesJpa;

    public boolean addModel(Modell modell){
        modelJpa.save(modell);
        return true;
    }


}
