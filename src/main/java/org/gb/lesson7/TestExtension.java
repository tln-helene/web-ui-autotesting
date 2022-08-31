package org.gb.lesson7;

import io.qameta.allure.Allure;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;

import java.io.ByteArrayInputStream;

public class TestExtension implements TestWatcher {
    ByteArrayInputStream screenStream;

    // вызывается в конце каждого теста для передачи скриншота в виде потока байтов, чтобы сохранить его в отчёт
    public void setScreenStream(ByteArrayInputStream screenStream) {
        this.screenStream = screenStream;
    }

    // при падении теста выполняется код для добавления скриншота в отчет
    public void testFailed(ExtensionContext context, Throwable cause) {
        Allure.addAttachment("Скриншот перед закрытием браузера", screenStream);
        System.out.println("ТЕСТ УПАЛ");
    }
}
