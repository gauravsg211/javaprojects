package com.example.test.repository;

import java.util.List;

import com.example.test.model.Speaker;

public interface SpeakerRepository {

	List<Speaker> findAll();
	String print();
	String greet();

}