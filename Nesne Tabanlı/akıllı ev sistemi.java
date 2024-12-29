import java.util.Scanner;

// 1. Interface Tanımları
interface Controllable {
    void turnOn();    // Cihazı açma
    void turnOff();   // Cihazı kapama
    boolean isOn();   // Cihazın durumunu sorgulama
}

// 2. Temel Sınıf: Appliance
abstract class Appliance implements Controllable {
    private boolean isOn; // Cihazın durumu

    public Appliance() {
        this.isOn = false; // Başlangıçta cihaz kapalı
    }

    @Override
    public void turnOn() {
        isOn = true;
        System.out.println(this.getClass().getSimpleName() + " is now ON.");
    }

    @Override
    public void turnOff() {
        isOn = false;
        System.out.println(this.getClass().getSimpleName() + " is now OFF.");
    }

    @Override
    public boolean isOn() {
        return isOn;
    }
}

// 3. Cihaz Sınıfları

// Light (Aydınlatma Sistemi) Sınıfı
class Light extends Appliance {
    public void adjustBrightness(int level) {
        System.out.println("Adjusting light brightness to level " + level);
    }
}

// Thermostat (Isıtma ve Soğutma Sistemi) Sınıfı
class Thermostat extends Appliance {
    private int temperature;

    public Thermostat() {
        this.temperature = 22; // Varsayılan sıcaklık
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
        System.out.println("Temperature set to " + temperature + "°C.");
    }

    public int getTemperature() {
        return temperature;
    }
}

// SecuritySystem (Güvenlik Sistemi) Sınıfı
class SecuritySystem extends Appliance {
    public void activateAlarm() {
        System.out.println("Alarm activated.");
    }

    public void deactivateAlarm() {
        System.out.println("Alarm deactivated.");
    }

    public void viewCamera() {
        System.out.println("Viewing security camera feed.");
    }
}

// 4. Konsol Uygulaması (Ana Program)
public class SmartHomeSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Appliance light = new Light();
        Appliance thermostat = new Thermostat();
        Appliance securitySystem = new SecuritySystem();

        System.out.println("Welcome to Smart Home System!");

        while (true) {
            System.out.println("\n1. Turn ON Light");
            System.out.println("2. Turn OFF Light");
            System.out.println("3. Turn ON Thermostat");
            System.out.println("4. Turn OFF Thermostat");
            System.out.println("5. Set Temperature");
            System.out.println("6. Activate Security System");
            System.out.println("7. Deactivate Security System");
            System.out.println("8. View Camera Feed");
            System.out.println("9. Exit");
            System.out.print("Choose an option: ");
            
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    light.turnOn();
                    break;
                case 2:
                    light.turnOff();
                    break;
                case 3:
                    thermostat.turnOn();
                    break;
                case 4:
                    thermostat.turnOff();
                    break;
                case 5:
                    System.out.print("Enter temperature: ");
                    int temperature = scanner.nextInt();
                    ((Thermostat) thermostat).setTemperature(temperature);
                    break;
                case 6:
                    securitySystem.turnOn();
                    ((SecuritySystem) securitySystem).activateAlarm();
                    break;
                case 7:
                    securitySystem.turnOff();
                    ((SecuritySystem) securitySystem).deactivateAlarm();
                    break;
                case 8:
                    ((SecuritySystem) securitySystem).viewCamera();
                    break;
                case 9:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice! Try again.");
            }
        }
    }
}
