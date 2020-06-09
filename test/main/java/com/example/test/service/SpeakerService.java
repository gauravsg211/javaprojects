package com.example.test.service;
import java.util.List;
import com.example.test.model.Speaker;

public interface SpeakerService {
List <Speaker> findAll();
String print();
String greet();
}