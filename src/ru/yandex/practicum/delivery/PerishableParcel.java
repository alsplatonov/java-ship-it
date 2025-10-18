package ru.yandex.practicum.delivery;

public class PerishableParcel extends Parcel{
    private static final double BASE_COST = 3;
    private int timeToLive;

    public PerishableParcel(String description, int weight, String deliveryAddress, int sendDay, int timeToLive) {
        super(description, weight, deliveryAddress, sendDay);
        this.timeToLive = timeToLive;
    }

    @Override
    public double getBaseCost() {
        return BASE_COST;
    }

    public boolean isExpired(int currentDay) {
        if (sendDay + timeToLive >= currentDay) {
            return false;
        }
        return true;
    }
}
