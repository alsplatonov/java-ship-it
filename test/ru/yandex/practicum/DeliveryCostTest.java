package ru.yandex.practicum;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import ru.yandex.practicum.delivery.*;

class DeliveryCostTest { //тест корректности расчета цены доставки

    @Test
    void standardParcelCostShouldBeCorrect() {
        Parcel parcel = new StandardParcel("Документы", 9, "Астрахань", 1);
        double realCost = parcel.calculateDeliveryCost();
        assertEquals(9 * 2, realCost, "Неверная стоимость стандартной посылки");
    }

    @Test
    void fragileParcelCostShouldBeCorrect() {
        Parcel parcel = new FragileParcel("Стекло", 33, "Москва", 1);
        double realCost = parcel.calculateDeliveryCost();
        assertEquals(33 * 4, realCost, "Неверная стоимость хрупкой посылки");
    }

    @Test
    void perishableParcelCostShouldBeCorrect() {
        Parcel parcel = new PerishableParcel("Продукты", 15, "Вологда", 1,
                3);
        double realCost = parcel.calculateDeliveryCost();
        assertEquals(15 * 3, realCost, "Неверная стоимость скоропортящейся посылки");
    }
}