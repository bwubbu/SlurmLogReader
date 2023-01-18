package Assignment;

import java.text.ParseException; //Only use main.java if ur using the methods.

public class Main{

    public static void main(String[] args) throws ParseException {
        ExtractedLogHandler log = new ExtractedLogHandler("extracted_log");
        log.divideJobsByPartitions();
        log.ErrorDetection();
        log.AvgExecTime();
    }
}
