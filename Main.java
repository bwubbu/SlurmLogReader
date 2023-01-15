package Assignment;

import java.text.ParseException;

public class Main{

    public static void main(String[] args) throws ParseException {
        ExtractedLogHandler log = new ExtractedLogHandler("extracted_log");
        log.divideJobsByPartitions();
        log.ErrorDetection();
        log.AvgExecTime();
    }
}
