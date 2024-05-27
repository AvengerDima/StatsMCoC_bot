package avenge;

import avenge.commands.*;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

public class StatsBot extends TelegramLongPollingBot {
    @Override
    public String getBotUsername() {
        return "@StatsMCoC_bot";
    }

    @Override
    public String getBotToken() {
        return "7071295461:AAEahEf_EzkOwU0IwoRXFibLz25Ch9PcP4U";
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String messageText = update.getMessage().getText();
            if (messageText.equals("/start")) {
                SendMessage message = StartCommand.execute(update);
                try {
                    execute(message);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
