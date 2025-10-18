package ru.yandex.practicum.delivery;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DeliveryApp {

    private static final Scanner scanner = new Scanner(System.in);
    private static List<Parcel> allParcels = new ArrayList<>();

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            showMenu();
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    addParcel();
                    break;
                case 2:
                    sendParcels();
                    break;
                case 3:
                    calculateCosts();
                    break;
                case 0:
                    running = false;
                    break;
                default:
                    System.out.println("Неверный выбор.");
            }
        }
    }

    private static void showMenu() {
        System.out.println("Выберите действие:");
        System.out.println("1 — Добавить посылку");
        System.out.println("2 — Отправить все посылки");
        System.out.println("3 — Посчитать стоимость доставки");
        System.out.println("0 — Завершить");
    }

    // реализуйте методы ниже


    private static void addParcel() {
        System.out.print("Введите тип посылки (1 - стандартная, 2 - скоропортящаяся, 3 - хрупкая): ");
        int type = scanner.nextInt();
        scanner.nextLine(); // очистка буфера

        System.out.print("Введите описание посылки: ");
        String description = scanner.nextLine();

        System.out.print("Введите вес посылки (в кг): ");
        int weight = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Введите адрес доставки: ");
        String deliveryAddress = scanner.nextLine();

        System.out.print("Введите день отправки: ");
        int sendDay = scanner.nextInt();
        scanner.nextLine();

        Parcel parcel;
        switch (type) {
            case 1:
                parcel = new StandardParcel(description, weight, deliveryAddress, sendDay);
                break;
            case 2:
                System.out.print("Введите срок жизни посылки в днях: ");
                int timeToLive = scanner.nextInt();
                scanner.nextLine();

                parcel = new PerishableParcel(description, weight, deliveryAddress, sendDay, timeToLive);
                break;
            case 3:
                parcel = new FragileParcel(description, weight, deliveryAddress, sendDay);
                break;
            default:
                System.out.println("Неверный тип посылки!");
                return;
        }

        allParcels.add(parcel);
        System.out.println("Посылка добавлена!");
    }

    private static void sendParcels() {
        // Пройти по allParcels, вызвать packageItem() и deliver()
        for (Parcel parcel : allParcels) {
            parcel.packageItem();
            parcel.deliver();
        }
    }

    private static void calculateCosts() {
        // Посчитать общую стоимость всех доставок и вывести на экран
        double totalCost = 0;
        for (Parcel parcel : allParcels) {
            totalCost += parcel.calculateDeliveryCost();
        }
        System.out.println("Общая стоимость всех доставок: " + totalCost);
    }

}

