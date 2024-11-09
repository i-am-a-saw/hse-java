import java.io.*;
import java.util.Scanner;

public class Counter {
    public static void main(String[] args) {
        int[] mas = new int[52];
        int chrt;
        Scanner scanner = new Scanner(System.in);
        String inName;
        String outName;
        
        while (true) {
            System.out.print("Файл для чтения: ");
            inName = scanner.nextLine();
            if (inName.trim().isEmpty()) {
                System.out.println("Имя файла не может быть пустым.");
            } else {
                break;
            }
        }

        File in = new File(inName);
        if (!in.exists()) {
            System.out.println("Файл не существует.");
            System.exit(0);
        }
        if (!in.isFile()) {
            System.out.println("Неверный формат входного файла.");
            System.exit(0);
        }

        try {
            BufferedReader Read = new BufferedReader(new FileReader(in));
            while ((chrt = Read.read()) != -1) {
                char letter = (char) chrt;
                if (letter >= 'a' && letter <= 'z') {
                    mas[letter + 26 - 'a']++;
                }
                if (letter >= 'A' && letter <= 'Z') {
                    mas[letter - 'A']++;
                }
            }
        } catch (IOException e) {
            System.out.println("В процессе чтения возникла ошибка: " + e.getMessage());
            System.exit(0);
        }

        while (true) {
            System.out.print("Файл для записи: ");
            outName = scanner.nextLine();
            if (outName.trim().isEmpty()) {
                System.out.println("Имя файла не может быть пустым.");
            } else {
                break;
            }
        }

        try {
            BufferedWriter Write = new BufferedWriter(new FileWriter(outName));
            for (int i = 0; i < 52; i++) {
                if (i >= 26) {
                    Write.write((char) (i - 26 + 'a') + ": " + mas[i]);
                } else {
                    Write.write((char) (i + 'A') + ": " + mas[i]);
                }
                Write.newLine();
            }
        } catch (IOException e) {
            System.out.println("В процессе записи возникла ошибка: " + e.getMessage());
        }
        scanner.close();
    }
}
