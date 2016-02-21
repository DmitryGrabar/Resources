import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TimeSearcher {
    private String firstDate;
    private String secondDate;
    final String REGEX = "[0-9]{4}-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1]) (2[0-3]|[01][0-9]):[0-5][0-9]";

    public TimeSearcher() {
        Scanner in = new Scanner(System.in);

        System.out.println("Введите дату начала поиска (пример : 2014-02-04 12:34): ");
        this.firstDate = in.nextLine();
        System.out.println("Введите конечную дату (example : 2014-02-04 12:34): ");
        this.secondDate = in.nextLine();
    }

    public boolean rightDate(Message element) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd HH:mm");
        Pattern pattern = Pattern.compile(REGEX);
        Matcher mtFirtDate = pattern.matcher(this.firstDate);
        Matcher mtSecondDate = pattern.matcher(this.secondDate);

        //Сплит двух входящих дат и текущей даты
        String splitFirsDate[] = this.firstDate.split("-|:| ");
        String splitSecondDate[] = this.secondDate.split("-|:| ");
        String splitcheckDate[] = (sdf.format(element.getDate())).split((":| |\\."));

        //Преобразование 3-х дат в числа
        long firstDate = Long.parseLong(splitFirsDate[0] + splitFirsDate[1] + splitFirsDate[2] + splitFirsDate[3] + splitFirsDate[4]);
        long secondDate = Long.parseLong(splitSecondDate[0] + splitSecondDate[1] + splitSecondDate[2] + splitSecondDate[3] + splitSecondDate[4]);
        long checkDate = Long.parseLong(splitcheckDate[0] + splitcheckDate[1] + splitcheckDate[2] + splitcheckDate[3] + splitcheckDate[4]);
        return mtFirtDate.find() && mtSecondDate.find() && firstDate < secondDate && checkDate <= secondDate && checkDate >= firstDate;
    }
}
