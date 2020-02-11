package com.rupinder.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rupinder.dao.ProfileRepo;
import com.rupinder.model.Profile;

@RestController @CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/profile")
public class ProfileController {
	@Autowired
	ProfileRepo profileRepo;
	
	@GetMapping("/get_profile/{pid}")
	public Profile getProfileById(@PathVariable("pid") int id) {
		return profileRepo.findById(id).get();
	}
	
	/*@GetMapping("/get_profile/{pname}")
	public Profile getProfileByName(@PathVariable("pname") String name) {
		return profileRepo.findByName(name);
	}*/
	
	@PutMapping("/update_profile")
	public Profile updateProfile(@RequestBody Profile profile) {
		return profileRepo.save(profile);
	}
	
	@DeleteMapping("/delete_profile/{pid}")
	public ResponseEntity<String> deleteProfileById(@PathVariable("pid") int id) {
		profileRepo.deleteById(id);
		return new ResponseEntity<>("Profile deleted", HttpStatus.OK);
	}
	
	@DeleteMapping("/deleteAll")
	public ResponseEntity<String> deleteAllProfiles(){
		profileRepo.deleteAll();
		return new ResponseEntity<>("All profiles have been deleted ",HttpStatus.OK);
	}
}
