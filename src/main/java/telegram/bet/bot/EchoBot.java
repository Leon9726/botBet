package telegram.bet.bot;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updates.SetWebhook;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.starter.SpringWebhookBot;

import telegram.bet.service.FootballPredictionApi;

public class EchoBot extends SpringWebhookBot {
	
	public EchoBot(SetWebhook setWebhook) {
		super(setWebhook);
	}
	
	public EchoBot (DefaultBotOptions options, SetWebhook setWebhook) {
	    super(options, setWebhook);
	}
	
	
	private final Logger log = LogManager.getLogger(EchoBot.class);
	
	@Value("${nomeBot}")
	String nomeBot;
	
	@Value("${tokenBot}")
	String token;
	
	@Autowired
	FootballPredictionApi footballPredictionApi;
	
	public String getBotUsername() {
		return nomeBot;
	}
	
	@Override
	public String getBotToken() {
		return token;
	}

	@Override
	public BotApiMethod<?> onWebhookUpdateReceived(Update update) {
		if (update.hasMessage() && update.getMessage().hasText()) {
			String msg = update.getMessage().getText();
			String chatId=update.getMessage().getChatId().toString();
	        SendMessage sendMessage = new SendMessage();
	        sendMessage.setChatId(chatId);
	        sendMessage.setText("Tutto sull'Italia allora !!!!");
	        footballPredictionApi.getPronostici();
	        try {
	            execute(sendMessage);
	        } catch (TelegramApiException e) {
	           log.error("Errore nel bot", e);
	        }
		}
		return null;
	}


	@Override
	public String getBotPath() {
		return nomeBot;
	}
}