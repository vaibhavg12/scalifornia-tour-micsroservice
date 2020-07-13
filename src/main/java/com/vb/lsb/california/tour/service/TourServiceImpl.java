package com.vb.lsb.california.tour.service;

import com.vb.lsb.california.tour.model.Difficulty;
import com.vb.lsb.california.tour.model.Region;
import com.vb.lsb.california.tour.model.Tour;
import com.vb.lsb.california.tour.model.TourPackage;
import com.vb.lsb.california.tour.repository.TourPackageRepository;
import com.vb.lsb.california.tour.repository.TourRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Tour Service impl
 *
 * @author Vaibhav Gupta
 */
@Service
public class TourServiceImpl implements TourService {
    private TourRepository tourRepository;
    private TourPackageRepository tourPackageRepository;

    @Autowired
    public TourServiceImpl(TourRepository tourRepository, TourPackageRepository tourPackageRepository) {
        this.tourRepository = tourRepository;
        this.tourPackageRepository = tourPackageRepository;
    }


    @Override
    public Tour createTour(String title, String description, String blurb, Integer price,
            String duration, String bullets,
            String keywords, String tourPackageName, Difficulty difficulty, Region region) {
        TourPackage tourPackage = tourPackageRepository.findByName(tourPackageName).orElseThrow(()->
             new RuntimeException("Tour package does not exist: " + tourPackageName));

        return tourRepository.save(new Tour(title, description,blurb, price, duration,
                bullets, keywords, tourPackage, difficulty, region));
    }

    @Override
    public long total() {
        return tourRepository.count();
    }
}

