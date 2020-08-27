package com.jwt.demo.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jwt.demo.base.model.BaseMessage;
import com.jwt.demo.base.model.BaseResponse;
import com.jwt.demo.exception.ServiceException;
import com.jwt.demo.model.Restaurent;
import com.jwt.demo.model.RestaurentRequest;
import com.jwt.demo.model.RestaurentResponse;
import com.jwt.demo.service.RestaurentService;
import com.jwt.demo.service.RestaurentServiceRepository;

@RestController
@RequestMapping("/restaurent")
@Validated
public class RestaurentController {

	private static final Logger LOGGER = LoggerFactory.getLogger(RestaurentController.class);

	@Autowired
	private RestaurentService restaurentService;

	@Autowired
	private RestaurentServiceRepository restaurentServiceRepository;

	@GetMapping("/getAllRestaurentJpa")
	public ResponseEntity<BaseResponse<RestaurentResponse>> listAllRestaurentsJpa() throws ServiceException {
		LOGGER.trace("inside listAllRestaurentsJpa()");
		RestaurentResponse restaurentResponse = new RestaurentResponse();
		try {
			List<Restaurent> restaurentList = restaurentServiceRepository.listAllRestaurents();
			restaurentResponse.setRestaurentsResponse(restaurentList);
			return new ResponseEntity<>(BaseResponse.<RestaurentResponse>builder().success(true).messages(null)
					.data(restaurentResponse).build(), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			BaseMessage error = BaseMessage.builder().code("SRVR404").message("record not found").type("E").build();
			ServiceException serviceException = new ServiceException();
			serviceException.setBaseMessage(error);
			throw serviceException;
		}
	}

	@GetMapping("/getAllRestaurent")
	public ResponseEntity<BaseResponse<RestaurentResponse>> listAllRestaurents() throws ServiceException {
		LOGGER.trace("inside listAllRestaurents()");
		RestaurentResponse restaurentResponse = new RestaurentResponse();
		try {
			List<Restaurent> restaurentList = restaurentService.listAllRestaurents();
			restaurentResponse.setRestaurentsResponse(restaurentList);
			return new ResponseEntity<>(BaseResponse.<RestaurentResponse>builder().success(true).messages(null)
					.data(restaurentResponse).build(), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			BaseMessage error = BaseMessage.builder().code("SRVR403").message("records not found").type("E").build();
			ServiceException serviceException = new ServiceException();
			serviceException.setBaseMessage(error);
			throw serviceException;
		}
	}

	@GetMapping("/getFilterRestuarent")
	public ResponseEntity<BaseResponse<RestaurentResponse>> filterRestuarent(@RequestParam("cuisine") String cuisine)
			throws ServiceException {
		LOGGER.trace("inside filterRestuarent()");
		RestaurentResponse restaurentResponse = new RestaurentResponse();
		List<Restaurent> restaurentList = restaurentService.filterRestuarent(cuisine);
		if (!restaurentList.isEmpty()) {
			restaurentResponse.setRestaurentsResponse(restaurentList);
			return new ResponseEntity<>(BaseResponse.<RestaurentResponse>builder().success(true).messages(null)
					.data(restaurentResponse).build(), HttpStatus.OK);
		} else {
			BaseMessage error = BaseMessage.builder().code("SRVR403").message("records not found").type("E").build();
			ServiceException serviceException = new ServiceException();
			serviceException.setBaseMessage(error);
			throw serviceException;
		}
	}

	@PostMapping("/doSaveOrUpdateRestaurents")
	public ResponseEntity<BaseResponse<String>> saveOrUpdateRestaurents(
			@Valid @RequestBody RestaurentRequest restaurentsSaveOrUpdateRequest) throws ServiceException {
		LOGGER.trace("inside saveOrUpdateRestaurents()");
		int i = restaurentService
				.saveOrUpdateRestaurents(restaurentsSaveOrUpdateRequest.getRestaurentsSaveOrUpdateRequest());
		if (i != 0) {
			return new ResponseEntity<>(BaseResponse.<String>builder().success(true).messages(null)
					.data("save or update records " + i).build(), HttpStatus.OK);
		} else {
			BaseMessage error = BaseMessage.builder().code("SRVR500").message("save or update records 0").type("E")
					.build();
			ServiceException serviceException = new ServiceException();
			serviceException.setBaseMessage(error);
			throw serviceException;
		}
	}
}
