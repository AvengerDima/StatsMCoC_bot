package avenge;

import avenge.commands.*;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

public class StatsBot extends TelegramLongPollingBot {
    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String messageText = update.getMessage().getText();
            String username = update.getMessage().getFrom().getUserName();
            String firstName = update.getMessage().getFrom().getFirstName();

            if (messageText.equals("/start")) {
                // Проверка, существует ли пользователь в базе данных
                if (!mongoDBService.userExists(username)) {
                    mongoDBService.saveUserData(username, firstName);
                }

                // Создание кнопки "Start App" с использованием WebAppInfo
                InlineKeyboardButton startAppButton = new InlineKeyboardButton();
                startAppButton.setText("Start App");
                WebAppInfo webAppInfo = new WebAppInfo("https://avengerdima.github.io/StatsMCoC_bot/");
                startAppButton.setWebApp(webAppInfo);

                List<InlineKeyboardButton> keyboardButtonsRow = new ArrayList<>();
                keyboardButtonsRow.add(startAppButton);

                List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
                rowList.add(keyboardButtonsRow);

                InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
                inlineKeyboardMarkup.setKeyboard(rowList);

                // Отправка сообщения с кнопкой
                SendMessage message = new SendMessage();
                message.setChatId(update.getMessage().getChatId().toString());
                message.setText("Приветствую, Призыватель!");
                message.setReplyMarkup(inlineKeyboardMarkup);

                try {
                    execute(message);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
}
