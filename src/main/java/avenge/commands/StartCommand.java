package avenge.commands;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.webapp.WebAppInfo;

import java.util.ArrayList;
import java.util.List;

public class StartCommand {
    public static SendMessage execute(Update update) {
        Long chatId = update.getMessage().getChatId();

        SendMessage message = new SendMessage();
        message.setChatId(chatId.toString());
        message.setText("Приветствую, Призыватель!");

        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
        List<InlineKeyboardButton> rowInline = new ArrayList<>();

        InlineKeyboardButton startAppButton = new InlineKeyboardButton();
        startAppButton.setText("Start App");
        WebAppInfo webAppInfo = new WebAppInfo("https://avengerdima.github.io/StatsMCoC_bot/");
        startAppButton.setWebApp(webAppInfo);

        rowInline.add(startAppButton);
        rowsInline.add(rowInline);
        markupInline.setKeyboard(rowsInline);
        message.setReplyMarkup(markupInline);

        return message;
    }
}
