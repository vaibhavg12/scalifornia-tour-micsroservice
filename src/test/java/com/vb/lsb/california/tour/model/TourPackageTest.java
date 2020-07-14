package com.vb.lsb.california.tour.domain;

import com.vb.lsb.california.tour.model.TourPackage;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author Vaibhav Gupta
 */
public class TourPackageTest {
    @Test
    public void testConstructorAndGetters() throws Exception {
        TourPackage p = new TourPackage("CC", "name");
        assertThat(p.getName(), is("name"));
        assertThat(p.getCode(), is("CC"));
    }

    @Test
    public void equalsHashcodeVerify() {
        TourPackage p1 = new TourPackage("CC", "name");
        TourPackage p2 = new TourPackage("CC", "name");

        assertThat(p1, is(p2));
        assertThat(p1.hashCode(), is(p2.hashCode()));
    }
}