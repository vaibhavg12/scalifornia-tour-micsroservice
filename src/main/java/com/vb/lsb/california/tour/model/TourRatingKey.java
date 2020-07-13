package com.vb.lsb.california.tour.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import java.io.Serializable;

/**
 * Composite Primary Key for tour rating containing a Tour and a Customer Identifier

 * @author Vaibhav Gupta
 */
@Embeddable
public class TourRatingKey implements Serializable {
    @ManyToOne
    private Tour tour;

    @Column(insertable = false, updatable = false,nullable = false)
    private Integer customerId;

    public TourRatingKey() {
    }

    /**
     * Fully initialize a Tour Rating Pk
     *
     * @param tour          the tour.
     * @param customerId    the customer identifier.
     */
    public TourRatingKey(Tour tour, Integer customerId) {
        this.tour = tour;
        this.customerId = customerId;
    }

    public Tour getTour() {
        return tour;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TourRatingKey that = (TourRatingKey) o;

        if (!tour.equals(that.tour)) return false;
        return customerId.equals(that.customerId);

    }

    @Override
    public int hashCode() {
        int result = tour.hashCode();
        result = 31 * result + customerId.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "TourRatingKey{" +
                "tour=" + tour +
                ", customerId=" + customerId +
                '}';
    }
}
