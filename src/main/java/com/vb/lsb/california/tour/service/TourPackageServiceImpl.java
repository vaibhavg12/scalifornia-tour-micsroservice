package com.vb.lsb.california.tour.service;

import com.vb.lsb.california.tour.model.TourPackage;
import com.vb.lsb.california.tour.repository.TourPackageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Tour Package Service impl
 *
 * @author Vaibhav Gupta
 */
@Service
public class TourPackageServiceImpl implements TourPackageService {
    private TourPackageRepository tourPackageRepository;

    @Autowired
    public TourPackageServiceImpl(TourPackageRepository tourPackageRepository) {
        this.tourPackageRepository = tourPackageRepository;
    }


    @Override
    public TourPackage createTourPackage(String code, String name) {
        return tourPackageRepository.findById(code).orElse(tourPackageRepository.save(new TourPackage(code, name)));
    }


    @Override
    public Iterable<TourPackage> lookup(){
        return tourPackageRepository.findAll();
    }

    @Override
    public long total() {
        return tourPackageRepository.count();
    }
}

