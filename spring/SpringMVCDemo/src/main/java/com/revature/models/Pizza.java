package com.revature.models;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@NoArgsConstructor @AllArgsConstructor @Getter @Setter @EqualsAndHashCode @ToString
public class Pizza implements Serializable {
	private static final long serialVersionUID = -3118958564094838472L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@ElementCollection
	@CollectionTable(name = "toppings", joinColumns = {@JoinColumn(name = "pizza_id")})
	private Set<Topping> toppings;
	
	private Size size;
	private double price;
	private CrustType crust;	
}
