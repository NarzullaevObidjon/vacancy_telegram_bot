package com.company;

import org.telegram.telegrambots.bots.TelegramWebhookBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class ReporterBot extends TelegramWebhookBot {
    private static final String botUsername=" t.me/hisobotForVacancybot";
    private static final String botToken="6251614666:AAG5PF7mOhSV0QszDV03WbT5TwD3sJ4b6t8";


    public void sendMessage(final String text) {
        try {
            SendMessage sendMessage = new SendMessage("1628423816", text);
            this.execute(sendMessage);
        } catch (TelegramApiException e) {
            SendMessage sendMessage = new SendMessage("1628423816", "Botga habar jo'natishda exception bo'ldi : "+ e.getMessage());
            try {
                this.execute(sendMessage);
            } catch (TelegramApiException ex) {

            }
        }
    }

    @Override
    public BotApiMethod<?> onWebhookUpdateReceived(Update update) {
        return null;
    }

    @Override
    public String getBotUsername() {
        return botUsername;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }

    @Override
    public String getBotPath() {
        return "/tmp/telegram_bot";
    }
}
