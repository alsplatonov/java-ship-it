package ru.yandex.practicum;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import ru.yandex.practicum.delivery.*;

class PerishableParcelTest {

    @Test
    void perishableParcelShouldExpireAfterTimeToLive() { //груз испортился
        PerishableParcel parcel = new PerishableParcel("Свинина", 1, "Москва", 1, 2);
        assertTrue(parcel.isExpired(4)); // прошло 3 дня после отправки
    }

    @Test
    void perishableParcelShouldNotExpireBeforeTimeToLive() { //груз не испортился
        PerishableParcel parcel = new PerishableParcel("Свинина", 1, "Москва", 1, 3);
        assertFalse(parcel.isExpired(3)); // прошло 2 дня
    }

    @Test
    void perishableParcelExpiresExactlyAtBoundary() {//граничная проверка. Груз еще не испортился (sendDay + timeToLive >= currentDay)
        PerishableParcel parcel = new PerishableParcel("Свинина", 1, "Москва", 1, 3);
        assertFalse(parcel.isExpired(4)); // ровно после 3 дней
    }
}
