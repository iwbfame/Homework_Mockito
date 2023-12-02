package ru.netology.sender;


import org.junit.jupiter.api.Test;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.i18n.LocalizationService;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class MessageSenderImplTest {

    @Test
    void send() {

        GeoService mockedGeoService = mock(GeoService.class);
        when(mockedGeoService.byIp("172.0.32.11"))
                .thenReturn(new Location("Moscow", Country.RUSSIA, "Lenina", 15));
        when(mockedGeoService.byIp("96.44.183.149"))
                .thenReturn(new Location("New York", Country.USA, "10th Avenue", 32));

        LocalizationService mockedLocalizationService = mock(LocalizationService.class);
        when(mockedLocalizationService.locale(Country.RUSSIA)).thenReturn("Добро пожаловать");
        when(mockedLocalizationService.locale(Country.USA)).thenReturn("Welcome");

        MessageSenderImpl messageSender = new MessageSenderImpl(mockedGeoService, mockedLocalizationService);

        Map<String, String> headersRussia = new HashMap<>();
        headersRussia.put(MessageSenderImpl.IP_ADDRESS_HEADER, "172.0.32.11");

        String resultRussia = messageSender.send(headersRussia);

        assertEquals("Добро пожаловать", resultRussia);

        Map<String, String> headersUSA = new HashMap<>();
        headersUSA.put(MessageSenderImpl.IP_ADDRESS_HEADER, "96.44.183.149");

        String resultUSA = messageSender.send(headersUSA);

        assertEquals("Welcome", resultUSA);
    }
}