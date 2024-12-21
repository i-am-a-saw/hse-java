import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Elevator> elevators = new ArrayList<>();

        for (int i = 1; i <= 3; i++) {
            Elevator elevator = new Elevator(i);
            elevators.add(elevator);
            new Thread(elevator).start();
        }

        RequestGenerator generator = new RequestGenerator(elevators);
        Thread generatorThread = new Thread(generator);
        generatorThread.start();

        try {
            Thread.sleep(30000); // Генерация запросов в течение 30 секунд
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        generatorThread.interrupt();
        for (Elevator elevator : elevators) {
            Thread.currentThread().interrupt();
        }

        System.out.println("Система лифтов завершила работу.");

        System.exit(0);
    }
}
