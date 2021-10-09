package telegram.bet.bot;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public class EchoBot extends TelegramLongPollingBot {
	
	@Value("${nomeBot}")
	String nomeBot;
	
	@Value("${tokenBot}")
	String token;
	
	public String getBotUsername() {
		return nomeBot;
	}
	
	
	@Override
	public String getBotToken() {
		return token;
	}
	
	public void onUpdateReceived(Update update) {
		
		if (update.hasMessage() && update.getMessage().hasText()) {
			String msg = update.getMessage().getText();
			String chatId=update.getMessage().getChatId().toString();
	        SendMessage sendMessage = new SendMessage();
	        sendMessage.setChatId(chatId);
	        sendMessage.setText("Tutto sull'Italia allora !!!!");
	        try {
	            execute(sendMessage);
	        } catch (TelegramApiException e) {
	           // gestione errore in invio
	        }
		}
		
	}
}