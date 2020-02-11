package com.rupinder.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rupinder.model.Profile;

public interface ProfileRepo extends JpaRepository<Profile, Integer> {
	public Profile findByName(String name);
}
