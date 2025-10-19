package ru.yandex.practicum.delivery;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DeliveryApp {

    private static final Scanner scanner = new Scanner(System.in);
    private static List<Parcel> allParcels = new ArrayList<>();
    private static List<Trackable> trackingParcels = new ArrayList<>();


    private static ParcelBox<StandardParcel> standardBox =
            new ParcelBox<>(new ArrayList<>(), 50); // maxWeight = 50 кг
    private static ParcelBox<PerishableParcel> perishableBox =
            new ParcelBox<>(new ArrayList<>(), 40); // maxWeight = 40 кг
    private static ParcelBox<FragileParcel> fragileBox =
            new ParcelBox<>(new ArrayList<>(), 30); // maxWeight = 30 кг


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
                case 4:
                    if (trackingParcels.size() > 0) {
                        reportStatus();
                    } else {
                        System.out.println("Нет отслеживаемых посылок");
                    }

                    break;
                case 5:
                    showBoxContents();
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
        System.out.println("4 — Изменить местоположение посылок");
        System.out.println("5 — Показать содержимое коробки");
        System.out.println("0 — Завершить");
    }

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
        boolean abilityToAdd = false;
        switch (type) {
            case 1:
                parcel = new StandardParcel(description, weight, deliveryAddress, sendDay);
                abilityToAdd = standardBox.addParcel((StandardParcel) parcel);
                break;
            case 2:
                System.out.print("Введите срок жизни посылки в днях: ");
                int timeToLive = scanner.nextInt();
                scanner.nextLine();
                parcel = new PerishableParcel(description, weight, deliveryAddress, sendDay, timeToLive);
                abilityToAdd = perishableBox.addParcel((PerishableParcel) parcel);
                break;
            case 3:
                parcel = new FragileParcel(description, weight, deliveryAddress, sendDay);
                abilityToAdd = fragileBox.addParcel((FragileParcel) parcel);
                if (abilityToAdd) {
                    trackingParcels.add((Trackable) parcel); //добавили в отдельный список поддерживающих трекинг посылок
                }
                break;
            default:
                System.out.println("Неверный тип посылки!");
                return;
        }
        if (abilityToAdd) {
            allParcels.add(parcel);
            System.out.println("Посылка добавлена!");
        }
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

    private static void reportStatus() {
        for (Trackable trackableParcel : trackingParcels) {
            Parcel parcel = (Parcel) trackableParcel; // приведение к родительскому классу
            System.out.println("Введите новое местоположение для посылки: " + parcel.getDescription());
            String newLocation = scanner.nextLine();
            trackableParcel.reportStatus(newLocation);
        }
    }

    private static void showBoxContents() {
        System.out.print("Введите тип коробки для посылок (1 - стандартные, 2 - скоропортящиеся, 3 - хрупкие): ");
        int type = scanner.nextInt();
        scanner.nextLine();

        switch (type) {
            case 1:
                System.out.println("Содержимое коробки стандартных посылок:");
                for (StandardParcel parcel : standardBox.getAllParcels()) {
                    System.out.println(parcel.getDescription() + " — вес: " + parcel.weight + " кг, адрес: " + parcel.deliveryAddress);
                }
                break;

            case 2:
                System.out.println("Содержимое коробки скоропортящихся посылок:");
                for (PerishableParcel parcel : perishableBox.getAllParcels()) {
                    System.out.println(parcel.getDescription() + " — вес: " + parcel.weight + " кг, адрес: " + parcel.deliveryAddress);
                }
                break;

            case 3:
                System.out.println("Содержимое коробки хрупких посылок:");
                for (FragileParcel parcel : fragileBox.getAllParcels()) {
                    System.out.println(parcel.getDescription() + " — вес: " + parcel.weight + " кг, адрес: " + parcel.deliveryAddress);
                }
                break;

            default:
                System.out.println("Неверный тип коробки!");
        }
    }
}

