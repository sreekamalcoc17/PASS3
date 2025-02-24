package com.revature.project03.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.id.enhanced.SequenceStyleGenerator;

import com.revature.project03.model.DoctorAvailability;



@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "Doctor_Table")
//@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public class Doctor {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int doctorId;
	private String firstName;
	private String lastName;
	private String email;
	private long phno;
	private int fees;
	private String designation;
	private String specialization;
	@Embedded
	private DoctorAvailability doctorAvailability;
	private String password;
	
	
	 @ManyToMany(mappedBy = "doctors")
	    private Set<Patient> patients = new HashSet<>();
	 
	 @ManyToMany(mappedBy = "doctors")
	    private Set<Family> families = new HashSet<>();
	
	
}
