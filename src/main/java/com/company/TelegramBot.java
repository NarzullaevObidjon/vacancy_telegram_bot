package com.company;

import org.telegram.telegrambots.bots.TelegramWebhookBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class TelegramBot extends TelegramWebhookBot {
    private static final String botUsername="@Fortestishmehnatbot.";
    private static final String botToken="5905774032:AAHGvBEHJnn7WeL5rfCRp6ikyyRil3TRXTk";
    private final String channel;

    public TelegramBot(String channel) {
        this.channel = channel;
    }

    public void sendMessageToChannel(final String text) {
        try {
            SendMessage sendMessage = new SendMessage(channel, text);
            sendMessage.setParseMode("HTML");
            this.execute(sendMessage);
        } catch (TelegramApiException e) {
            NotificationBot notificationBot = new NotificationBot();
            notificationBot.sendMessage("Kanallarga habar jo'natishda exception bo'ldi : "+e.getMessage());
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
