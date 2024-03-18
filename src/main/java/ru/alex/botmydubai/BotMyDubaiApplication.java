package ru.alex.botmydubai;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@SpringBootApplication
public class BotMyDubaiApplication {
    private static final Logger logger = LoggerFactory.getLogger(MyTelegramBot.class);
    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(BotMyDubaiApplication.class, args);
        MyTelegramBot myTelegramBot = context.getBean(MyTelegramBot.class);

        try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(myTelegramBot);
        } catch (TelegramApiException e) {
            logger.error("Произошла Ошибка", e);
        }
    }
}

