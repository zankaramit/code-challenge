package com.test.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Data
@Getter
@Setter
@ToString
public class TransactionSum {

	private double sum;
	private String currency;


}