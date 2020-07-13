package com.vb.lsb.california.tour.service;

import com.vb.lsb.california.tour.model.TourPackage;
import org.springframework.stereotype.Service;

/**
 * Tour Package Service
 *
 * @author Vaibhav Gupta
 */
public interface TourPackageService {
    /**
     * Create a Tour Package
     *
     * @param code code of the package
     * @param name name of the package
     *
     * @return new or existing tour package
     */
    TourPackage createTourPackage(String code, String name);

    /**
     * Lookup All Tour packages
     *
     * @return
     */
    Iterable<TourPackage> lookup();

    long total();
}
