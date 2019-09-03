import java.time.DateTimeException;
import java.time.LocalDateTime;

public class Event extends Task {
    private LocalDateTime startDateTime;   // DD/MM/YYYY
    private LocalDateTime endDateTime;

    public Event(String description, String timePeriod) throws DukeException{
        super(description);
        this.setDateTime(timePeriod);
    }

    private void setDateTime(String timePeriod) throws DukeException{
        String datetimeInput[] = timePeriod.split(" ");
        String dateInput[] = datetimeInput[0].split("/");
        String timeInput[] = datetimeInput[1].split("-");
        int year, month, day, startHour, startMinute, endHour, endMinute;
        year = Integer.parseInt(dateInput[2]);
        month = Integer.parseInt(dateInput[1]);
        day = Integer.parseInt(dateInput[0]);
        startHour = Integer.parseInt(timeInput[0])/100;
        startMinute = Integer.parseInt(timeInput[0])%100;
        endHour = Integer.parseInt(timeInput[1])/100;
        endMinute = Integer.parseInt(timeInput[1])%100;
        try {
            this.startDateTime = LocalDateTime.of(year, month, day, startHour, startMinute);
            this.endDateTime = LocalDateTime.of(year, month, day, endHour, endMinute);
        }catch(DateTimeException e){
            throw new DukeException("Bad date or time provided");
        }
    }

    protected String toFileString(){
        StringBuilder fileString = new StringBuilder();
        fileString.append("E | 0 | " + description + " | ");
        fileString.append(datetimeToString() + "\n");
        return fileString.toString();
    }

    private String datetimeToString(){
        StringBuilder datetimeString = new StringBuilder();
        datetimeString.append(startDateTime.getDayOfMonth() + "/" + startDateTime.getMonthValue() + "/"
                + startDateTime.getYear());
        if(startDateTime.getHour()<10) {
            datetimeString.append(" 0" + startDateTime.getHour());
        }else{
            datetimeString.append(" " + startDateTime.getHour());
        }
        if(startDateTime.getMinute()<10){
            datetimeString.append("0" + startDateTime.getMinute());
        }else{
            datetimeString.append(startDateTime.getMinute());
        }
        if(endDateTime.getHour()<10) {
            datetimeString.append("-0" + endDateTime.getHour());
        }else{
            datetimeString.append("-" + endDateTime.getHour());
        }
        if(endDateTime.getMinute()<10){
            datetimeString.append("0" + endDateTime.getMinute());
        }else{
            datetimeString.append(endDateTime.getMinute());
        }
        return datetimeString.toString();
    }

    @Override
    public String toString() {
        if (isDone) {
            return "[E]" + "[" + "\u2713" + "]" + this.description + " (at: " + this.datetimeToString() + ")";
        } else {
            return "[E]" + "[" + "\u2718" + "]" + this.description + " (at: " + this.datetimeToString() + ")";
        }
    }
}
