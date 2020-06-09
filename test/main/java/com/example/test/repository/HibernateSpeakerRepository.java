package com.example.test.repository;

import java.util.ArrayList;
import java.util.List;

import com.example.test.model.Speaker;

public class HibernateSpeakerRepository implements SpeakerRepository {
	@Override
	public List <Speaker>findAll(){
		
		List<Speaker> speakers=new ArrayList<Speaker>();
		Speaker speaker=new Speaker();
		
		speaker.setFirstName("gaurav");
		speaker.setLastName("singh");
		
		speakers.add(speaker);
		return speakers;
	}
	@Override
	public String print() {
		String s=  "inside the print function";
		return s;

}
	@Override
	public String greet() {
		Speaker speaker=new Speaker();
		
		speaker.setFirstName("gaurav");
		speaker.setLastName("singh");
		
		 return "hello " + speaker.getFirstName() +" "+ speaker.getLastName();
	
	
}
}
