package CarPack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class CarUtilities {
    public static Position ZERO_POSITION = new Position(0, 0);
    public static boolean ehh = false;
    public static double STANDARD_WHEEL_DIAMETER = 1;
    public static List<Car> list = new ArrayList<>();
    public static Car removed;
    public static List<String> MODELS = Arrays.asList("Audi R8", "Fiat Punto", "Ferrari FXX", "Skoda Octavia", "Suzuki Capuccino", "Toyota Supra", "Toyota Avensis", "Fiat 500", "Volkswagen Golf");
    public static List<Integer> SPEEDS = Arrays.asList(180, 220, 210, 300, 240, 300, 200, 350, 200, 300, 420);
    public static List<String> WEIRD_NAMES = Arrays.asList("ADA11M", "M1R23AD1A", "21AA0", "3DSDS00", "240Z2A33", "DSA444", "!32AAAA");
    public static List<String> ENGINE_NAMES = Arrays.asList("2JZ-FSE", "2JZ-GTE", "2JZ-GE", "2JZ", "1JZ-FSE", "V4 Twin Turbo", "1.6 TDI", "1.6 TSI");



    public static Car carCreator() {
        Random random = new Random();
        Clutch clutch = new Clutch(WEIRD_NAMES.get(random.nextInt(WEIRD_NAMES.size())), random.nextInt(30) + 5, random.nextInt(5000) + 1500);
        GearBox gearBox = new GearBox(WEIRD_NAMES.get(random.nextInt(WEIRD_NAMES.size())), random.nextInt(30) + 15, random.nextInt(5000) + 1500, random.nextInt(2) + 5, clutch);
        Engine engine = new Engine(ENGINE_NAMES.get(random.nextInt(WEIRD_NAMES.size())), random.nextInt(30) + 100, random.nextInt(15000) + 5000, (random.nextInt(5) + 5) * 1000);
        return new Car(random.nextInt(899999) + 100000,
                MODELS.get(random.nextInt(MODELS.size())),
                SPEEDS.get(random.nextInt(SPEEDS.size())),
                engine,
                gearBox,
                STANDARD_WHEEL_DIAMETER);
    }

    public static Car carCreator(int providedRegistrationNumber, String providedModel, int providedVMAX, double providedWheelDiameter) {
        Random random = new Random();
        Clutch clutch = new Clutch(WEIRD_NAMES.get(random.nextInt(WEIRD_NAMES.size())), random.nextInt(30) + 5, random.nextInt(5000) + 1500);
        GearBox gearBox = new GearBox(WEIRD_NAMES.get(random.nextInt(WEIRD_NAMES.size())), random.nextInt(30) + 15, random.nextInt(5000) + 1500, random.nextInt(2) + 5, clutch);
        Engine engine = new Engine(ENGINE_NAMES.get(random.nextInt(WEIRD_NAMES.size())), random.nextInt(30) + 100, random.nextInt(15000) + 5000, (random.nextInt(5) + 5) * 1000);
        return new Car(providedRegistrationNumber,
                providedModel,
                providedVMAX,
                engine,
                gearBox,
                providedWheelDiameter);
    }
}
