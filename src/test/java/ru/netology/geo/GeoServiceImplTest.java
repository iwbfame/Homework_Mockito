package ru.netology.geo;

import org.junit.jupiter.api.Test;
import ru.netology.entity.Country;
import ru.netology.entity.Location;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class GeoServiceImplTest {

    @Test
    void byIp() {
        GeoServiceImpl geoService = mock(GeoServiceImpl.class);

        Location mockLocationMoscow = mock(Location.class);
        Location mockLocationNewYork = mock(Location.class);

        when(mockLocationMoscow.getCity()).thenReturn("Moscow");
        when(mockLocationMoscow.getCountry()).thenReturn(Country.RUSSIA);
        when(mockLocationMoscow.getStreet()).thenReturn("Lenina");
        when(mockLocationMoscow.getBuiling()).thenReturn(15);

        when(mockLocationNewYork.getCity()).thenReturn("New York");
        when(mockLocationNewYork.getCountry()).thenReturn(Country.USA);
        when(mockLocationNewYork.getStreet()).thenReturn("10th Avenue");
        when(mockLocationNewYork.getBuiling()).thenReturn(32);

        geoService.byIp("172.0.32.11");
        when(geoService.byIp("172.0.32.11")).thenReturn(mockLocationMoscow);

        geoService.byIp("96.44.183.149");
        when(geoService.byIp("96.44.183.149")).thenReturn(mockLocationNewYork);

        assertEquals(mockLocationMoscow, geoService.byIp("172.0.32.11"));
        assertEquals(mockLocationNewYork, geoService.byIp("96.44.183.149"));
    }
}