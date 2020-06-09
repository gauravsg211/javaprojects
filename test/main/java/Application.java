import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.example.test.service.SpeakerService;
import com.example.test.service.SpeakerServiceImpl;
public class Application {
	public static void main(String args[]) {
		ApplicationContext appContext=new AnnotationConfigApplicationContext(AppConfig.class);
		//SpeakerService service=new SpeakerServiceImpl();
		SpeakerService service=appContext.getBean("speakerservice",SpeakerService.class);
		System.out.println(service.findAll().get(0).getLastName());
		System.out.println(service.print());
		System.out.println(service.greet());

		
	}

}
