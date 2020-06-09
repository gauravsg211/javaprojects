import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.example.test.service.SpeakerService;
import com.example.test.service.SpeakerServiceImpl;
import com.example.test.repository.HibernateSpeakerRepository;
import com.example.test.repository.SpeakerRepository;

@Configuration
public class AppConfig {
	@Bean(name="speakerservice")
	public SpeakerService getSpeakerService()
	{
		SpeakerServiceImpl service= new SpeakerServiceImpl(getSpeakerRepository());//constructor injection
		//service.setRepository(getSpeakerRepository());// setter injection
		return service;
		
	}
	
	@Bean(name="SpeakerRepository")
	public SpeakerRepository getSpeakerRepository()
	{
		return new HibernateSpeakerRepository();
		
	}

}
