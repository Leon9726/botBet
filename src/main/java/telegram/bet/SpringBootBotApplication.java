package telegram.bet;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@ComponentScan("telegram.*")
@SpringBootApplication
public class SpringBootBotApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(SpringBootBotApplication.class, args);
	}

}
