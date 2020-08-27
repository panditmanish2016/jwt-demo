package com.jwt.demo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.jwt.demo.dao.RestaurentDaoRepository;
import com.jwt.demo.exception.ServiceException;
import com.jwt.demo.model.Restaurent;

import org.junit.jupiter.api.BeforeEach;
/* import org.junit.jupiter.api.Disabled; */
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class RestaurentServiceTests {

  @Mock
  private RestaurentDaoRepository restaurentDaoRepository;

  @InjectMocks
  private RestaurentServiceRepository restaurentServiceRepository;

  @BeforeEach
  void setUp() {
    /*
     * MockitoAnnotations.initMocks(this); setting the expected restaurent list and
     * using mocking the RestaurentDaoRepository it does not make actual call to db
     * to get list of restaurents that is mock which by pass the making db call
     * since this mocked object itself consider as db and now if we use method
     * restaurentServiceRepository.listAllRestaurents() then it will call
     * restaurentDaoRepository.findAll() but moving forward this repository never
     * call the db to get list of restaurents so what ever is set in setUp() in
     * thenReturn(createRestaurent() will be returned means excpected is getting
     * returned. Basically uisng junit test we will check the functionality of the
     * code. However, we can make actual db call by using integration test which
     * tests the each modules or layers in this context and make db call also. So,
     * to achieve this we will use one annotaion above the class is @DataJpaTest
     * which is bit parallel with integration test.
     */
    when(restaurentDaoRepository.findAll()).thenReturn(createRestaurent());
    when(restaurentDaoRepository.retrieveByName(createRestaurent().get(0).getRestaurentName()))
        .thenReturn(createRestaurent().get(0));
    when(restaurentDaoRepository.findById(createRestaurent().get(0).getRestaurentId()))
        .thenReturn(Optional.of(createRestaurent().get(0)));
  }

  @Test
  @DisplayName("Test listAllRestaurents  success")
  void getAllRestuarentTest() throws ServiceException {
    assertEquals(2, restaurentServiceRepository.listAllRestaurents().size());
  }

  @Test
  /*
   * @Disabled("disabled because restaurentDaoRepository.retrieveByName() is not
   * working")
   */
  @DisplayName("Test retrieve Restaurents retrieveByName success")
  void getRestuarentByNameTest() throws ServiceException {
    assertEquals("Bihar Bhojnalaya",
        restaurentServiceRepository.retrieveByName(createRestaurent().get(0).getRestaurentName()).getRestaurentName());
  }

  @Test
  @DisplayName("Test retrieve Restaurents retrieveById success")
  void getAllRestuarentTestById() throws ServiceException {
    assertEquals("Bihar Bhojnalaya",
        restaurentServiceRepository.retrieveById(createRestaurent().get(0).getRestaurentId()).getRestaurentName());
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