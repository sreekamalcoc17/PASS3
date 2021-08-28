package com.revature.project03.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
//import javax.validation.constraints.Max;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "Family")
public class Family {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
//	@Max(8)
	private int family_id;
	private String firstName;
	private String lastName;
	private int age;
	private String gender;
	private String mobileNo;
	private String address;
	private String email_id;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="p_id")
	private Patient patient;
	
	@ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
        name = "Doctor_Family", 
        joinColumns = { @JoinColumn(name = "family_id") }, 
        inverseJoinColumns = { @JoinColumn(name = "doctorId") }
    )
    Set<Doctor> doctors = new HashSet<>();
	
}