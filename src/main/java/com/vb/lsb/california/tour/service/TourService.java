package com.vb.lsb.california.tour.service;

import com.vb.lsb.california.tour.model.Tour;

import java.util.Map;

/**
 * Tour Service
 *
 * @author Vaibhav Gupta
 */
public interface TourService {

    /**
     * Create a new Tour Object and persist it to the Database
     *
     * @param title Title of the tour
     * @param tourPackageName tour Package of the tour
     * @param details Extra details about the tour
     * @return Tour
     */
    Tour createTour(String title, String tourPackageName, Map<String, String> details);

    /**
     * Calculate the number of Tours in the Database.
     *
     * @return the total.
     */
    long total();
}
