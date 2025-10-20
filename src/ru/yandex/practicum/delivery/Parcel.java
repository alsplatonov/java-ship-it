package ru.yandex.practicum.delivery;

public abstract class Parcel {
    //добавьте реализацию и другие необходимые классы
    protected String description;
    protected int weight;
    protected String deliveryAddress;
    protected int sendDay;

    public Parcel(String description, int weight, String deliveryAddress, int sendDay) {
        this.description = description;
        this.weight = weight;
        this.deliveryAddress = deliveryAddress;
        this.sendDay = sendDay;
    }

    public String getDescription() {
        return description;
    }

    public int getWeight() {
        return weight;
    }

    public void packageItem() {
        System.out.println("Посылка " + description + " упакована");
    };

    public void deliver() {
        System.out.println("Посылка " + description + " доставлена по адресу " + deliveryAddress);
    }

    // Метод для получения базовой стоимости
    public abstract double getBaseCost();

    // Общий метод расчёта стоимости
    public double calculateDeliveryCost() {
        return weight * getBaseCost();
    }


}
