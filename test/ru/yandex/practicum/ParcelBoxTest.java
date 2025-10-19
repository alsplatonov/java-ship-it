package ru.yandex.practicum;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import ru.yandex.practicum.delivery.*;

import java.util.ArrayList;

class ParcelBoxTest {

    @Test
    void shouldAddParcelIfEnoughSpace() {
        ParcelBox<StandardParcel> box = new ParcelBox<>(new ArrayList<>(), 10);
        StandardParcel parcel = new StandardParcel("Подарок", 4, "Москва", 1);

        boolean result = box.addParcel(parcel);
        assertTrue(result, "Посылка должна успешно добавиться в коробку");
    }

    @Test
    void shouldNotAddParcelIfWeightLimitExceeded() {
        ParcelBox<StandardParcel> box = new ParcelBox<>(new ArrayList<>(), 5);
        StandardParcel parcel = new StandardParcel("Подарок", 8, "Москва", 11);

        boolean result = box.addParcel(parcel);

        assertFalse(result, "Посылка не должна добавляться при превышении максимального веса");
    }
}