package com.example.test.service;
import java.util.List;
import com.example.test.model.Speaker;
import com.example.test.repository.HibernateSpeakerRepository;
import com.example.test.repository.SpeakerRepository;

public class SpeakerServiceImpl implements SpeakerService {
	private SpeakerRepository repository;
	
	public SpeakerServiceImpl(SpeakerRepository speakerRepository )
	{
		repository=speakerRepository;
	}
public void setRepository(SpeakerRepository repository) {
		this.repository = repository;
	}

public List <Speaker> findAll(){
	return repository.findAll();

}

public String print() {
	return repository.print();
}
public String greet() {
	return repository.greet();
}
}
