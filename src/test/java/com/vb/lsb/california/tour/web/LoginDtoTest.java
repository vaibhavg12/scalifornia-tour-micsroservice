package com.vb.lsb.california.tour.web;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class LoginDtoTest {

    @Test
    public void testAll() {
        LoginDto dto = new LoginDto("user", "pwd");
        assertThat(dto.getUsername(), is("user"));
        assertThat(dto.getPassword(), is("pwd"));
    }
}