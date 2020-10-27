package com.revature.models;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@NoArgsConstructor @AllArgsConstructor @Getter @Setter @EqualsAndHashCode @ToString
public class Order implements Serializable {

	private static final long serialVersionUID = 3461951983456819028L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private OrderType type;
	private double total;
	
	@OneToMany(fetch = FetchType.EAGER)
	private Set<Pizza> items;
	
	public double calculateTax() {
		return items.stream()
				.mapToDouble(Pizza::getPrice)
				.sum() * 1.08;
	}
}
