package com.vb.lsb.california.tour.repository;

import com.vb.lsb.california.tour.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;

/**
 * @author Vaibhav Gupta.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTest {
    @Autowired
    private UserRepository repository;

    @Test
    public void testFindByUsername() {
        Optional<User> user = repository.findByUsername("admin");
        assertTrue(user.isPresent());

        user = repository.findByUsername("nobody");
        assertFalse(user.isPresent());
    }
}