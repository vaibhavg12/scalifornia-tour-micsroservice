package com.vb.lsb.california.tour.service;

import com.vb.lsb.california.tour.model.TourRating;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * Tour Package Service
 *
 * @author Vaibhav Gupta
 */
public interface TourRatingService {
    /**
     * Create a new Tour Rating in the database
     *
     * @param tourId     tour identifier
     * @param customerId customer identifier
     * @param score      score of the tour rating
     * @param comment    additional comment
     * @throws NoSuchElementException if no Tour found.
     */
    void createNew(int tourId, Integer customerId, Integer score, String comment) throws NoSuchElementException;

    /**
     * Get a ratings by id.
     *
     * @param id rating identifier
     * @return TourRatings
     */
    Optional<TourRating> lookupRatingById(int id);

    /**
     * Get All Ratings.
     *
     * @return List of TourRatings
     */
    List<TourRating> lookupAll();

    /**
     * Get a page of tour ratings for a tour.
     *
     * @param tourId   tour identifier
     * @param pageable page parameters to determine which elements to fetch
     * @return Page of TourRatings
     * @throws NoSuchElementException if no Tour found.
     */
    Page<TourRating> lookupRatings(int tourId, Pageable pageable) throws NoSuchElementException;

    /**
     * Update some of the elements of a Tour Rating.
     *
     * @param tourId  tour identifier
     * @param score   score of the tour rating
     * @param comment additional comment
     * @return Tour Rating Domain Object
     * @throws NoSuchElementException if no Tour found.
     */
    TourRating update(int tourId, Integer customerId, Integer score, String comment) throws NoSuchElementException;

    /**
     * Update all of the elements of a Tour Rating.
     *
     * @param tourId     tour identifier
     * @param customerId customer identifier
     * @param score      score of the tour rating
     * @param comment    additional comment
     * @return Tour Rating Domain Object
     * @throws NoSuchElementException if no Tour found.
     */
    TourRating updateSome(int tourId, Integer customerId, Integer score, String comment)
            throws NoSuchElementException;

    /**
     * Delete a Tour Rating.
     *
     * @param tourId     tour identifier
     * @param customerId customer identifier
     * @throws NoSuchElementException if no Tour found.
     */
    void delete(int tourId, Integer customerId) throws NoSuchElementException;

    /**
     * Get the average score of a tour.
     *
     * @param tourId tour identifier
     * @return average score as a Double.
     * @throws NoSuchElementException
     */
    Double getAverageScore(int tourId) throws NoSuchElementException;

    /**
     * Service for many customers to give the same score for a service
     *
     * @param tourId
     * @param score
     * @param customers
     */
    void rateMany(int tourId, int score, Integer[] customers);

    /**
     * Verify and return the TourRating for a particular tourId and Customer
     *
     * @param tourId
     * @param customerId
     * @return the found TourRating
     * @throws NoSuchElementException if no TourRating found
     */
    TourRating verifyTourRating(int tourId, int customerId) throws NoSuchElementException;
}
