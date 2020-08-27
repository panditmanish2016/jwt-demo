package com.jwt.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.Id;
import javax.persistence.Table;

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
@Entity
@Table(name = "RESTAURENT")
public class Restaurent {

	@Id
	@Column
	private int restaurentId;
	@Column
	private String restaurentName;
	@Column
	private String cuisines;
	@Column
	private String currency;
	@Column
	private double averageCostForTwo;
	@Column
	private boolean hasTableBooking;
	@Column
	private boolean hasOnlineDelivery;
	@Column
	private float aggregateRating;
	@Column
	private String ratingColor;
	@Column
	private String ratingText;
	@Column
	private int votes;
}
