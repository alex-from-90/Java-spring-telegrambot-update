package ru.alex.bot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public class MyTelegramBot extends TelegramLongPollingBot {

    private static final Logger logger = LoggerFactory.getLogger(MyTelegramBot.class);
   private final ru.alex.bot.BotConfig botConfig;

    @Autowired
    public MyTelegramBot(BotConfig botConfig) {
        super(botConfig.getBotToken());
        this.botConfig = botConfig;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            if ("/start".equals(update.getMessage().getText())) {
                SendMessage message = new SendMessage();
                message.setChatId(update.getMessage().getChatId().toString());
                message.setText("Привет! Я ваш бот.");

                try {
                    execute(message);
                } catch (TelegramApiException e) {
                    logger.error("Ошибка при отправке сообщения", e);
                }
            }
        }
    }

    @Override
    public String getBotUsername() {
        return botConfig.getBotUsername();
    }
}
