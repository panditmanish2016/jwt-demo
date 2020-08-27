package com.jwt.demo.service;

import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import com.jwt.demo.dao.RestaurentDaoRepository;
import com.jwt.demo.exception.ServiceException;
import com.jwt.demo.model.Restaurent;

@ExtendWith(MockitoExtension.class)
@AutoConfigureTestDatabase(replace = Replace.NONE)
@DataJpaTest
public class RestaurentServiceRepositoryTests {
    /*
     * As Test class can not have @Bean annotation directly so we need some static
     * or instance class (with @TestConfiguration annotation and name is
     * RestaurentServiceRepositoryTestsContextConfiguration or any name) to
     * use @Bean annotation above any method inside that class. Since we are not
     * having @Componnet annotation to scan the package so we can not write
     * directly @Autowired annotation on the RestaurentServiceRepository Interface
     * because @Autowired is cunsumer annotation which never get the reference of
     * RestaurentServiceRepository because that still not got initialized. Inside
     * this interface RestaurentDaoRepository is already with the
     * 
     * @Autowired annotation so there is no need of injecting that as it
     * automatically gets initialized because of
     * 
     * @Autowired annonation. Here we are not using @Mock because we do not want to
     * mock them with some pre-defined value, need some actual DB call. sometimes it
     * is called INTEGARTION TEST
     * 
     * @AutoConfigureTestDatabase(replace = Replace.NONE) we are making this class
     * to use db configuraion from application.yaml file.
     */
    @TestConfiguration
    static class RestaurentServiceRepositoryTestsContextConfiguration {

        @Bean
        public RestaurentServiceRepository employeeService() {
            return new RestaurentServiceRepository();
        }
    }

    @Autowired
    private RestaurentServiceRepository restaurentServiceRepository;
    /*
     * this we are doing autowiring RestaurentDaoRepository reference if we do this
     * only then there won't be any need of injecting RestaurentServiceRepository
     * here. All the methods we can access from this RestaurentDaoRepository and
     * which also make actaul DB call. Here we are using either
     * RestaurentDaoRepository or RestaurentServiceRepository to perform intgeration
     * test
     */
    @Autowired
    private RestaurentDaoRepository restaurentDaoRepository;

    @Test
    @DisplayName("Test listAllRestaurents  success")
    void getAllRestuarentTest() throws ServiceException {
        assertNotNull(restaurentServiceRepository.listAllRestaurents());
    }

    @Test
    /*
     * @Disabled("disabled because restaurentDaoRepository.retrieveByName() is not
     * working")
     */
    @DisplayName("Test retrieve Restaurents retrieveByName success")
    void getRestuarentByNameTest() throws ServiceException {
        assertEquals("Bihar Bhojnalaya", restaurentServiceRepository
                .retrieveByName(createRestaurent().get(0).getRestaurentName()).getRestaurentName());
    }

    @Test
    @DisplayName("Test listAllRestaurents using DAO interface success")
    @Disabled("RestaurentDaoRepository injection is not working")
    void getAllRestuarentTestUsingDao() throws ServiceException {
        assertEquals(3, restaurentDaoRepository.findAll());
    }

    List<Restaurent> createRestaurent() {
        List<Restaurent> rList = new ArrayList<>();
        Restaurent r1 = new Restaurent();
        r1.setRestaurentId(101);
        r1.setAggregateRating(3.9f);
        r1.setAverageCostForTwo(570);
        r1.setCuisines("rice, dal, samosa, chai, litti, jalebi,fish, curry, chicken, chat, masala, chat masala");
        r1.setCurrency("INR");
        r1.setHasOnlineDelivery(true);
        r1.setHasTableBooking(true);
        r1.setRatingColor("red");
        r1.setRatingText("North Indian restaurant");
        r1.setRestaurentName("Bihar Bhojnalaya");
        r1.setVotes(518);
        Restaurent r2 = new Restaurent();
        r2.setRestaurentId(102);
        r2.setAggregateRating(4.7f);
        r2.setAverageCostForTwo(570);
        r2.setCuisines(
                "rice, dal, samosa, chai, litti, jalebi,fish, curry, chicken, chat, masala, chat masala, mamos, french fry, ildi, dosa, chapati,pizza");
        r2.setCurrency("INR");
        r2.setHasOnlineDelivery(true);
        r2.setHasTableBooking(true);
        r2.setRatingColor("red");
        r2.setRatingText("restaurent");
        r2.setRestaurentName("Cheesy Bites");
        r2.setVotes(38);
        rList.add(r1);
        rList.add(r2);
        return rList;
    }
}