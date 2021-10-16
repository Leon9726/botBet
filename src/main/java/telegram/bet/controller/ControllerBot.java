package telegram.bet.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.telegram.telegrambots.meta.api.objects.Update;

import telegram.bet.bot.EchoBot;

@RestController
public class ControllerBot {
	
	private final Logger log = LogManager.getLogger(ControllerBot.class);
	
	@Autowired
	private EchoBot bot;
	
	@PostMapping("/start")
	 public void getUpdate(@RequestBody Update update){
		log.debug("Sono entrato---ma poi esco (come sempre)");
		bot.onWebhookUpdateReceived(update);
	 }

}
