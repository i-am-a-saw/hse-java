import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.Period;
import java.util.*;

public class lab{
    public static void main(String[] args) throws ParseException {
        String f_name, l_name, patr;
        Scanner sc = new Scanner(System.in);

        f_name = Enter_str("First name", sc);
        l_name = Enter_str("Last name", sc);
        patr = Enter_str("Patronymic", sc);

        String BD = Enter_date(sc);
        DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setLenient(false);
        Date dat = sdf.parse(BD);

        LocalDate birthday = LocalDate.of(1900 + dat.getYear(), dat.getMonth()+1, dat.getDate());
        int age = Period.between(birthday, LocalDate.now()).getYears();

        System.out.println(l_name.substring(0, 1).toUpperCase() + l_name.substring(1) + " " + f_name.substring(0,1).toUpperCase() + "." + patr.substring(0,1).toUpperCase() + ".");
        System.out.print("Age = " + age);
        if (age % 10 == 1){
            System.out.println(" год");
        }
        else if (age % 10 > 1 && age % 10 < 5 && (age < 11 || age > 20)){
            System.out.println(" года");
        }
        else{
            System.out.println(" лет");
        }

        if (patr.endsWith("ович") || l_name.endsWith("ов") || patr.endsWith("евич") || l_name.endsWith("ев") || patr.endsWith("ич") || patr.endsWith("ыч")){
            System.out.println("Пол: мужской");
        }
        else if (l_name.endsWith("ова") || patr.endsWith("овна") || patr.endsWith("евна") || l_name.endsWith("ева") || patr.endsWith("чна")){
            System.out.println("Пол: женский");
        }
        else{
            System.out.println("Пол определить не удалось :(");
        }
    }

    public static String Enter_str(String arg, Scanner sc){
        String res;
        System.out.println("Enter your " + arg.toLowerCase());
        while (true){
            res = sc. next();
            if (res.matches("[а-яА-Я]+")){break;}
            else{System.out.println(arg + " can only contain russian letters! Enter your " + arg.toLowerCase() + ": ");}
        }
        return res;
    }

    public static String Enter_date(Scanner sc){
        String res;
        while(true) {
            System.out.println("Enter your birthday: ");
            res = sc.next().replaceAll("\\.", "/");;
            if (isValid(res)) {
                break;
            }
            System.out.println("Invalid date...");
        }
        return res;
    }

    public static boolean isValid(String dateStr) {
        DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setLenient(false);
        try {
            sdf.parse(dateStr);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }
}
