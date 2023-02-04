import java.io.*;
import java.time.*;
import java.time.format.*;
import java.util.Scanner;
import java.util.regex.Pattern;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

public class JobDateDetector {
        public static void main(String[] args) throws IOException {
        // Get start date and end date from user input
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the date one day prior checking  (YYYY-MM-DD): ");
        String startDateString = scanner.nextLine();
        System.out.print("Enter the date to stop checking from (YYYY-MM-DD): ");
        String endDateString = scanner.nextLine();
        int created=0;
        int ended=0;
        // Parse start date and end date strings as LocalDate objects
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate startDate = LocalDate.parse(startDateString, formatter);
        LocalDate endDate = LocalDate.parse(endDateString, formatter);

        // Read text file line by line
        try (BufferedReader reader = new BufferedReader(new FileReader("extracted_log"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Extract date from line
                LocalDate date = LocalDate.parse(line.substring(1, 11), formatter);
                if (date.isAfter(startDate) && date.isBefore(endDate)) {
                    if(Pattern.matches(".*_slurm_rpc_submit_batch_job: JobId.*", line)){
                  created++;
           }      
              else if(Pattern.matches(".*_job_complete: JobId=\\d+ done.*", line)){
                  ended++;
              }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("file not found");
        }
        System.out.println("Number of jobs created: " + created);
        System.out.println("Number of jobs ended: "+ ended);
        
         DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue(created, "Jobs", "Created");
        dataset.addValue(ended, "Jobs", "Ended");

        // Create the bar chart
        JFreeChart chart = ChartFactory.createBarChart("Jobs Created and Ended", "", "Number of Jobs", dataset);

        // Create a frame to display the chart
        ChartFrame frame = new ChartFrame("Jobs Created and Ended", chart);
        frame.pack();
        frame.setVisible(true);
    }
}
