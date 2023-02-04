import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

public class AvgExecTime02 {
        public static void main(String[] args) throws IOException {
        Map<Integer, Double> jobStartTimes = new HashMap<>();
        Map<Integer, Double> jobEndTimes = new HashMap<>();
        Map<Integer, Double> monthDuration = new HashMap<>();
        Map<Integer, Integer> monthCompletedJobs = new HashMap<>();
        double totalDuration = 0;
        int completedJobs = 0;
        // String to store each line in the file
        try ( // File reader to read "extracted_log" file
                BufferedReader reader = new BufferedReader(new FileReader("extracted_log"))) {
            // String to store each line in the file
            String line;
            // Loop through each line in the file
            while ((line = reader.readLine()) != null) {
                int month = extractMonth(line);
                if (line.matches(".*_job_complete: JobId=(\\d+) done.*")) {
                   // System.out.println(line);
                    int jobId = extractJobId(line);
                    double endTime = extractDate(line);
                    jobEndTimes.put(jobId, endTime);
                    if (jobStartTimes.containsKey(jobId)) {
                        double startTime = jobStartTimes.get(jobId);
                        double duration = endTime - startTime;
                        totalDuration += duration;
                        completedJobs++;
                        if (!monthDuration.containsKey(month)) {
                            monthDuration.put(month, duration);
                            monthCompletedJobs.put(month, 1);
                        } else {
                            monthDuration.put(month, monthDuration.get(month) + duration);
                            monthCompletedJobs.put(month, monthCompletedJobs.get(month) + 1);
                        }
                    }
                   }
                if (Pattern.matches(".*_slurm_rpc_submit_batch_job: JobId=(\\d+).*",line)) {
                int jobId = extractJobId(line);
                double startTime = extractDate(line);
                jobStartTimes.put(jobId, startTime);
                 }
                 }
                if(!monthDuration.isEmpty()) {
                double averageDuration = totalDuration / completedJobs;
                System.out.printf("Average execution time of completed jobs: %.2f hours\n",averageDuration);
                for (Integer m : monthDuration.keySet()) {
                double avgDuration = monthDuration.get(m) / monthCompletedJobs.get(m);
                System.out.printf("Average execution time of completed jobs in month %d: %.2f hours\n", m, avgDuration);
                }
                DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (Integer m : monthDuration.keySet()) {
            double avgDuration = monthDuration.get(m) / monthCompletedJobs.get(m);
            dataset.addValue(avgDuration, "Average Duration", String.valueOf(m));
        }
        JFreeChart barChart = ChartFactory.createBarChart("Average Execution Time of Completed Jobs by Month", "Month", "Duration (hours)", dataset);
        ChartFrame frame = new ChartFrame("Bar Chart", barChart);
        frame.setVisible(true);
        frame.setSize(450, 350);
    } else {
        System.out.println("No completed jobs found in the log file");
            }
        // Close the reader
        }
    }
        
public static int extractJobId(String line) {
    Matcher matcher = Pattern.compile("JobId=(\\d+)").matcher(line);
    if (matcher.find()) {
        return Integer.parseInt(matcher.group(1));
        }
        return -1;
    }

public static double extractDate(String line) {
    Matcher matcher = Pattern.compile("\\[(\\d+)-(\\d+)-(\\d+)T(\\d+):(\\d+):(\\d+)\\.\\d+\\]").matcher(line);
    if (matcher.find()) {
        double year = Double.parseDouble(matcher.group(1));
        double month = Double.parseDouble(matcher.group(2));
        double day = Double.parseDouble(matcher.group(3));
        double hour = Double.parseDouble(matcher.group(4));
        double minute = Double.parseDouble(matcher.group(5));
        double second = Double.parseDouble(matcher.group(6));
        return (year*8760+month*720+day*24+hour+(minute/60)+(second/3600));
        }
        return -1;
    }

public static int extractMonth(String line) {
    
    Matcher matcher = Pattern.compile("\\[\\d+-(\\d+)-\\d+T\\d+:\\d+:\\d+\\.\\d+\\]").matcher(line);
    if (matcher.find()) {
        return Integer.parseInt(matcher.group(1));
        }
        return -1;
    }
}
