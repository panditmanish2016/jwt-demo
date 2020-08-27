package com.jwt.demo.controller;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import com.jwt.demo.exception.ServiceException;
import com.jwt.demo.model.Restaurent;
import com.jwt.demo.service.RestaurentService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class RestaurentControllerTest {

    private MockMvc mockMvc;

    @Mock
    private RestaurentService restaurentService;

    @InjectMocks
    private RestaurentController restaurentController;

    @BeforeEach
    void setUp() throws ServiceException {
        mockMvc = MockMvcBuilders.standaloneSetup(restaurentController).build();
        when(restaurentService.filterRestuarent(ArgumentMatchers.any())).thenReturn(createRestaurent());
    }

    @Test
    @DisplayName("Test filterRestuarentTest method in  RestaurentController class success")
    public void filterRestuarentTest() throws Exception {
        String cuisine = "chicken";
        String URI = "/restaurent/getFilterRestuarent";
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(URI).contentType(MediaType.APPLICATION_JSON)
                .queryParam("cuisine", cuisine);
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
        String response = mvcResult.getResponse().getContentAsString();
        assertNotNull(response);
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