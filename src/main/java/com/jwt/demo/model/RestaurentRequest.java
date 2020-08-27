package com.jwt.demo.model;

import java.util.List;

import javax.validation.Valid;

import com.jwt.demo.annotation.ValidRestaurentSaving;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ToString
@Builder
public class RestaurentRequest {

	@ValidRestaurentSaving(message = "invalid.restaurent.booking")
	@Valid
	List<Restaurent> restaurentsSaveOrUpdateRequest;
}
