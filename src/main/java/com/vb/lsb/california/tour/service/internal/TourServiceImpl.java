package com.vb.lsb.california.tour.service.internal;

import com.vb.lsb.california.tour.model.Tour;
import com.vb.lsb.california.tour.model.TourPackage;
import com.vb.lsb.california.tour.repository.TourPackageRepository;
import com.vb.lsb.california.tour.repository.TourRepository;
import com.vb.lsb.california.tour.service.TourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

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
    public Tour createTour(String title, String tourPackageName, Map<String, String> details) {
        TourPackage tourPackage = tourPackageRepository.findByName(tourPackageName).orElseThrow(() ->
                new RuntimeException("Tour package does not exist: " + tourPackageName));
        return tourRepository.save(new Tour(title, tourPackage, details));
    }

    @Override
    public long total() {
        return tourRepository.count();
    }
}

