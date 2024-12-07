import org.jsoup.*;
import org.jsoup.nodes.*;

import java.io.IOException;

public class parcer {
    private static String url1 = "https://fake-json-api.mock.beeceptor.com/users";
    private static String url2 = "https://dummy-json.mock.beeceptor.com/todos";

    public static void main(String[] args) {
        parse(url1, "users");

        parse(url2, "todos");
    }

    public static void parse(String url1, String task) {
        String mas[] = new String[10];
        try {
            String json = Jsoup.connect(url1).ignoreContentType(true).execute().body();
            mas = json.replace("\"", "").replace(",", "").split("\n");
        } catch (IOException e) {
            System.out.println("Ooops, could not connect to the source :(");
        }

        if (task == "users") {
            System.out.println("\nUSERS:\n");

            for (int i = 0; i < mas.length; i++) {
                // System.out.println(mas[i]);
                if (mas[i].contains("id:")) {
                    System.out.println("Id - " + mas[i].substring(8));
                } else if (mas[i].contains("name:") && !(mas[i].contains("username:"))) {
                    System.out.println("Name - " + mas[i].substring(10));
                }
                if (mas[i].contains("company:")) {
                    System.out.println("Company - " + mas[i].substring(13));
                }
                if (mas[i].contains("username:")) {
                    System.out.println("Username - " + mas[i].substring(14));
                }
                if (mas[i].contains("email:")) {
                    System.out.println("Email - " + mas[i].substring(11));
                }
                if (mas[i].contains("address:")) {
                    System.out.println("Address - " + mas[i].substring(13));
                }
                if (mas[i].contains("zip:")) {
                    System.out.println("Zip - " + mas[i].substring(9));
                }
                if (mas[i].contains("state:")) {
                    System.out.println("State - " + mas[i].substring(11));
                }
                if (mas[i].contains("country:")) {
                    System.out.println("Country - " + mas[i].substring(13));
                }
                if (mas[i].contains("phone:")) {
                    System.out.println("Phone - " + mas[i].substring(11));
                }
                if (mas[i].contains("photo:")) {
                    System.out.println("Photo - " + mas[i].substring(11) + "\n");
                }
            }
        } else if (task == "todos") {
            System.out.println("\nTODOS:\n");

            for (int i = 0; i < mas.length; i++) {
                // System.out.println(mas[i]);
                if (mas[i].contains("userId:")) {
                    System.out.println("UserId - " + mas[i].substring(12));
                } else if (mas[i].contains("id:")) {
                    System.out.println("Id - " + mas[i].substring(8));
                }
                else if (mas[i].contains("title:")) {
                    System.out.println("Title - " + mas[i].substring(11));
                }
                else if (mas[i].contains("completed:")) {
                    System.out.println("Completed - " + mas[i].substring(15) + "\n");
                }
            }
        }
    }
}
