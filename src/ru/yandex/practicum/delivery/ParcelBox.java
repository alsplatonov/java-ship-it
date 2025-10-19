package ru.yandex.practicum.delivery;

import java.util.ArrayList;

public class ParcelBox<T extends Parcel> {
    private ArrayList<T> parcels = new ArrayList<>();
    private int maxWeight;

    public ParcelBox(ArrayList<T> parcels, int maxWeight) {
        this.parcels = parcels;
        this.maxWeight = maxWeight;
    }

    public boolean addParcel(T parcel) {
        double currentWeight = 0;
        for (T p : parcels) {
            currentWeight += p.weight;
        }
        if (currentWeight + parcel.weight > maxWeight) {
            System.out.println("Превышен максимальный вес коробки");
            return false;
        }
        parcels.add(parcel);
        return true;
    }

    public ArrayList<T> getAllParcels() {
        return parcels;
    }
}

