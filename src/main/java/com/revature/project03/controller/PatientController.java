package com.revature.project03.controller;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.project03.entities.Family;
import com.revature.project03.entities.Patient;
import com.revature.project03.exception.ResourceNotFoundException;
import com.revature.project03.repository.FamilyRepository;
import com.revature.project03.repository.PatientRepository;
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/patient")
public class PatientController {

	@Autowired
	private PatientRepository patientRepository;
	@Autowired
	private FamilyRepository familyRepository;

	

	@GetMapping("/family/{id}")
	public ResponseEntity<Family> getFamilyMemberById(@PathVariable(value = "id") Integer familyMemberId)
			throws ResourceNotFoundException {
		Family familyMember = familyRepository.findById(familyMemberId)
				.orElseThrow(() -> new ResourceNotFoundException("FamilyMember not found for this id :: " + familyMemberId));
		return ResponseEntity.ok().body(familyMember);
	}

	@PostMapping("{patientid}/family/addmember")
	public Family createFamilyMember(@RequestBody Family familyMember, @PathVariable(value = "patientid") Integer patientId) throws ResourceNotFoundException {
		Patient patient = patientRepository.findById(patientId)
		          .orElseThrow(() -> new ResourceNotFoundException("Patient not found for this id :: " + patientId));
		familyMember.setPatient(patient);
		return familyRepository.save(familyMember);
	}

	@PutMapping("/family/{id}")
	public ResponseEntity<Family> updateFamilyMember(@PathVariable(value = "id") Integer familyMemberId,
			@RequestBody Family familyMemberDetails) throws ResourceNotFoundException {
		Family familyMember =familyRepository.findById(familyMemberId)
				.orElseThrow(() -> new ResourceNotFoundException("FamilyMember not found for this id :: " + familyMemberId));

		familyMember.setEmail_id(familyMemberDetails.getEmail_id());
		familyMember.setLastName(familyMemberDetails.getLastName());
		familyMember.setFirstName(familyMemberDetails.getFirstName());
		familyMember.setAge(familyMemberDetails.getAge());
		familyMember.setAddress(familyMemberDetails.getAddress());
		familyMember.setGender(familyMemberDetails.getGender());
		familyMember.setMobileNo(familyMemberDetails.getMobileNo());

		final Family updatedFamilyMember = familyRepository.save(familyMember);
		return ResponseEntity.ok(updatedFamilyMember);
	}
	@DeleteMapping("/family/{id}")
	public Map<String, Boolean> deleteFamilyMember(@PathVariable(value = "id") Integer familyMemberId)
			throws ResourceNotFoundException {
		Family famiyMember = familyRepository.findById(familyMemberId)
				.orElseThrow(() -> new ResourceNotFoundException("FamilyMember not found for this id :: " + familyMemberId));

		familyRepository.delete(famiyMember);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
                                                       //http://localhost:9090/patient
    @GetMapping("/patients")
    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }
   
    @GetMapping("/patient/{id}")
    public ResponseEntity<Patient> getPatientById(@PathVariable(value = "id") Integer patientId)
        throws ResourceNotFoundException {
        Patient patient = patientRepository.findById(patientId)
          .orElseThrow(() -> new ResourceNotFoundException("Patient not found for this id :: " + patientId));
        return ResponseEntity.ok().body(patient);
    }
    
    @PostMapping("/patients")
    public Patient createPatient(@RequestBody  Patient patient) {
        return patientRepository.save(patient);
    }
         
    @PutMapping("/patient/{id}")
    public ResponseEntity<Patient> updatePatient(@PathVariable(value = "id") Integer patientId,
         Patient patientDetails) throws ResourceNotFoundException {
        Patient patient = patientRepository.findById(patientId)
        .orElseThrow(() -> new ResourceNotFoundException("Patient not found for this id :: " + patientId));
        
        patient.setUserName(patientDetails.getUserName());
        patient.setPassword(patientDetails.getPassword());
        patient.setEmail(patientDetails.getEmail());
        patient.setLastName(patientDetails.getLastName());
        patient.setFirstName(patientDetails.getFirstName());
        patient.setAge(patientDetails.getAge());
        patient.setAddress(patientDetails.getAddress());
        patient.setGender(patientDetails.getGender());
        patient.setMobileNo(patientDetails.getMobileNo());
        final Patient updatedPatient = patientRepository.save(patient);
        return ResponseEntity.ok(updatedPatient);
    }

    @DeleteMapping("/patient/{id}")
    public Map<String, Boolean> deletePatient(@PathVariable(value = "id") Integer patientId)
         throws ResourceNotFoundException {
        Patient patient = patientRepository.findById(patientId)
       .orElseThrow(() -> new ResourceNotFoundException("Patient not found for this id :: " + patientId));

        patientRepository.delete(patient);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
    
   
}

