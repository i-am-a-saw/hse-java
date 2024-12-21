import java.util.*;
import java.util.concurrent.*;

class Request {
    int fromFloor;
    int toFloor;

    public Request(int fromFloor, int toFloor) {
        this.fromFloor = fromFloor;
        this.toFloor = toFloor;
    }
}

class Elevator implements Runnable {
    private final int id;
    private int currentFloor = 0;
    private final Queue<Request> requests = new ConcurrentLinkedQueue<>();
    private boolean isMoving = false;

    public Elevator(int id) {
        this.id = id;
    }

    public synchronized void requestElevator(Request request) {
        requests.offer(request);
        notify();
    }

    @Override
    public void run() {
        while (true) {
            if (requests.isEmpty()) {
                try {
                    synchronized (this) {
                        wait();
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            } else {
                processNextRequest();
            }
        }
    }

    private void processNextRequest() {
        Request request = requests.poll();
        if (request != null) {
            moveToFloor(request.fromFloor);
            pickUpPassenger(request);
            moveToFloor(request.toFloor);
            dropOffPassenger(request);
        }
    }

    private void moveToFloor(int targetFloor) {
        System.out.println("Лифт " + id + " движется с этажа " + currentFloor + " на этаж " + targetFloor);
        currentFloor = targetFloor;
        System.out.println("Лифт " + id + " прибыл на этаж " + targetFloor);
    }

    private void pickUpPassenger(Request request) {
        System.out.println("Лифт " + id + " забрал пассажира на этаже " + request.fromFloor);
    }

    private void dropOffPassenger(Request request) {
        System.out.println("Лифт " + id + " высадил пассажира на этаже " + request.toFloor);
    }

    int getCurrentFloor(){return this.currentFloor;}
}

class RequestGenerator implements Runnable {
    private final List<Elevator> elevators;
    private final Random random = new Random();

    public RequestGenerator(List<Elevator> elevators) {
        this.elevators = elevators;
    }

    @Override
    public void run() {
        while (true) {
            int fromFloor = random.nextInt(10);
            int toFloor;
            do {
                toFloor = random.nextInt(10);
            } while (toFloor == fromFloor);

            Request request = new Request(fromFloor, toFloor);
            Elevator bestElevator = findBestElevator(request);

            if (bestElevator != null) {
                bestElevator.requestElevator(request);
            }

            try {
                Thread.sleep(2000); // Интервал между запросами
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }

    private Elevator findBestElevator(Request request) {
        Elevator bestElevator = null;
        int minDistance = Integer.MAX_VALUE;

        for (Elevator elevator : elevators) {
            int distance = Math.abs(elevator.getCurrentFloor() - request.fromFloor);
            if (distance < minDistance) {
                minDistance = distance;
                bestElevator = elevator;
            }
        }

        return bestElevator;
    }
}

