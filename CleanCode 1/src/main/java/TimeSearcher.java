import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TimeSearcher {
    private String firstDate;
    private String secondDate;
    final String REGEX = "[0-9]{4}-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1]) (2[0-3]|[01][0-9]):[0-5][0-9]";

    public TimeSearcher() {
        Scanner in = new Scanner(System.in);

        System.out.println("Enter first date (example : 2014-02-04 12:34): ");
        this.firstDate = in.nextLine();
        System.out.println("Enter last date (example : 2014-02-04 12:34): ");
        this.secondDate = in.nextLine();
    }

    public boolean rightDate() {
        Pattern pattern = Pattern.compile(REGEX);
        Matcher mtFirtDate = pattern.matcher(this.firstDate);
        Matcher mtSecondDate = pattern.matcher(this.secondDate);
        long firstDate = Long.parseLong(mtFirtDate.group(1) + mtFirtDate.group(2) + mtFirtDate.group(3) + mtFirtDate.group(4));
        long secondDate = Long.parseLong(mtSecondDate.group(1) + mtSecondDate.group(2) + mtSecondDate.group(3) + mtSecondDate.group(4));
        return mtFirtDate.find() && mtSecondDate.find() && firstDate < secondDate;
    }
}
