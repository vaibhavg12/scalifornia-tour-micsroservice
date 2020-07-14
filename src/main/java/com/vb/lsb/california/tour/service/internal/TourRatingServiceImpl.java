package com.vb.lsb.california.tour.service.internal;

import com.vb.lsb.california.tour.model.Tour;
import com.vb.lsb.california.tour.model.TourRating;
import com.vb.lsb.california.tour.repository.TourRatingRepository;
import com.vb.lsb.california.tour.repository.TourRepository;
import com.vb.lsb.california.tour.service.TourRatingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Tour Package Service impl
 *
 * @author Vaibhav Gupta
 */
@Service
public class TourRatingServiceImpl implements TourRatingService {
    private static final Logger LOGGER = LoggerFactory.getLogger(TourRatingService.class);
    private TourRatingRepository tourRatingRepository;
    private TourRepository tourRepository;

    /**
     * Construct TourRatingService
     *
     * @param tourRatingRepository Tour Rating Repository
     * @param tourRepository       Tour Repository
     */
    @Autowired
    public TourRatingServiceImpl(TourRatingRepository tourRatingRepository, TourRepository tourRepository) {
        this.tourRatingRepository = tourRatingRepository;
        this.tourRepository = tourRepository;
    }

    @Override
    public void createNew(int tourId, Integer customerId, Integer score, String comment) throws NoSuchElementException {
        LOGGER.info("Create Rating for tour {} of customers {}", tourId, customerId);
        tourRatingRepository.save(new TourRating(verifyTour(tourId), customerId,
                score, comment));
    }

    @Override
    public Optional<TourRating> lookupRatingById(int id) {
        return tourRatingRepository.findById(id);
    }

    @Override
    public List<TourRating> lookupAll() {
        LOGGER.info("Lookup all Ratings");
        return tourRatingRepository.findAll();
    }

    @Override
    public Page<TourRating> lookupRatings(int tourId, Pageable pageable) throws NoSuchElementException {
        LOGGER.info("Lookup Rating for tour {}", tourId);
        return tourRatingRepository.findByTourId(verifyTour(tourId).getId(), pageable);
    }

    @Override
    public TourRating update(int tourId, Integer customerId, Integer score, String comment) throws NoSuchElementException {
        LOGGER.info("Update all of Rating for tour {} of customers {}", tourId, customerId);
        TourRating rating = verifyTourRating(tourId, customerId);
        rating.setScore(score);
        rating.setComment(comment);
        return tourRatingRepository.save(rating);
    }

    @Override
    public TourRating updateSome(int tourId, Integer customerId, Integer score, String comment)
            throws NoSuchElementException {
        LOGGER.info("Update some of Rating for tour {} of customers {}", tourId, customerId);
        TourRating rating = verifyTourRating(tourId, customerId);
        if (score != null) {
            rating.setScore(score);
        }
        if (comment != null) {
            rating.setComment(comment);
        }
        return tourRatingRepository.save(rating);
    }

    @Override
    public void delete(int tourId, Integer customerId) throws NoSuchElementException {
        LOGGER.info("Delete Rating for tour {} and customer {}", tourId, customerId);
        TourRating rating = verifyTourRating(tourId, customerId);
        tourRatingRepository.delete(rating);
    }

    @Override
    public Double getAverageScore(int tourId) throws NoSuchElementException {
        LOGGER.info("Get average score of tour {} by customers {}", tourId);
        List<TourRating> ratings = tourRatingRepository.findByTourId(verifyTour(tourId).getId());
        OptionalDouble average = ratings.stream().mapToInt((rating) -> rating.getScore()).average();
        return average.isPresent() ? average.getAsDouble() : null;
    }

    @Override
    public void rateMany(int tourId, int score, Integer[] customers) {
        LOGGER.info("Rate tour {} by customers {}", tourId, Arrays.asList(customers).toString());
        tourRepository.findById(tourId).ifPresent(tour -> {
            for (Integer c : customers) {
                tourRatingRepository.save(new TourRating(tour, c, score));
            }
        });
    }

    /**
     * Verify and return the Tour given a tourId.
     *
     * @param tourId
     * @return the found Tour
     * @throws NoSuchElementException if no Tour found.
     */
    private Tour verifyTour(int tourId) throws NoSuchElementException {
        return tourRepository.findById(tourId).orElseThrow(() ->
                new NoSuchElementException("Tour does not exist " + tourId)
        );
    }


    @Override
    public TourRating verifyTourRating(int tourId, int customerId) throws NoSuchElementException {
        return tourRatingRepository.findByTourIdAndCustomerId(tourId, customerId).orElseThrow(() ->
                new NoSuchElementException("Tour-Rating pair for request("
                        + tourId + " for customer" + customerId));
    }


}
