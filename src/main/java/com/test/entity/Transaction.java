package com.test.entity;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Setter
@Getter
@ToString
@Table(name = "transactions")
public class Transaction {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long transactionId;
	private Double amount;
	private String currency;
	private String type;

	@OneToOne
	@JsonIgnoreProperties({"parentId"})
	private Transaction parentId;

	

}