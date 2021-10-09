package telegram.bet.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;


@Configuration
@PropertySource("file:/opt/telegram_bot/config/config.properties")
public class ConfigurationProperties {
	

}
