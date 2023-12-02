package ru.netology.i18n;

import org.junit.jupiter.api.Test;
import ru.netology.entity.Country;

import static org.junit.jupiter.api.Assertions.*;

class LocalizationServiceImplTest {

    @Test
    void locale() {
        LocalizationServiceImpl localizationService = new LocalizationServiceImpl();

        String russianText = localizationService.locale(Country.RUSSIA);
        assertEquals("Добро пожаловать", russianText);

        String defaultText = localizationService.locale(Country.GERMANY);
        assertEquals("Welcome", defaultText);
    }
}