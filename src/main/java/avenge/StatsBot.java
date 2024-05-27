package avenge;

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
            String chatId = update.getMessage().getChatId().toString();
            SendMessage message = new SendMessage();
            message.setChatId(chatId);
            message.setText("Click the link to open the Mini App: https://avengerdima.github.io/StatsMCoC_bot");
            try {
                execute(message);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (update.hasCallbackQuery()) {
            String chatId = update.getCallbackQuery().getMessage().getChatId().toString();
            String data = update.getCallbackQuery().getData();
            SendMessage message = new SendMessage();
            message.setChatId(chatId);
            message.setText("Data received: " + data);
            try {
                execute(message);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
