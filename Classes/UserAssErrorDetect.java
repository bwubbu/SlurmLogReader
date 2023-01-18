import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import javax.swing.JTable;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
// A class to make a table and chart from the errors and its corresponding users
public class UserAssErrorDetect extends JFrame {    //subclass for JFrame
    private static final long serialVersionUID = 1L;    //universal version identifier
    private static String fileName = "extracted_log";

    public UserAssErrorDetect(String title) {
        super(title);
    }
    public static void main(String[] args) throws Exception {
        UserAssErrorDetect chart = new UserAssErrorDetect("Error Detection");
        chart.ErrorDetection();
    }
    public void ErrorDetection() throws Exception {
        try {
            Scanner fileContent = new Scanner(new FileInputStream(fileName));
            int errorCount = 0;
            Map<String, Integer> errorByUser = new HashMap<>();     // HashMap is used to keep track of the number of errors caused by each unique user.
            while (fileContent.hasNextLine()) {
                String line = fileContent.nextLine();
                if (line.contains("error: This association")) {
                    errorCount++;
                    int userStart = line.indexOf("user=") + "user=".length();
                    int userEnd = line.indexOf(" ", userStart);
                    String user = line.substring(userStart, userEnd);
                    if (errorByUser.containsKey(user)) {
                        errorByUser.put(user, errorByUser.get(user) + 1);
                    } else {
                        errorByUser.put(user, 1);
                    }
                }
            }
            // Create a dataset for the bar chart
            DefaultCategoryDataset dataset = new DefaultCategoryDataset();
            for (Map.Entry<String, Integer> entry : errorByUser.entrySet()) {
                String user = entry.getKey();
                int userErrorCount = entry.getValue();
                dataset.addValue(userErrorCount, "Errors", user);
            }
            // Create the chart
            JFreeChart chart = ChartFactory.createBarChart("Association Errors by User", "User", "Error Count", dataset, PlotOrientation.HORIZONTAL, true, true, false);

            // Create a panel to display the chart
            ChartPanel chartPanel = new ChartPanel(chart);
            chartPanel.setPreferredSize(new java.awt.Dimension(600, 500));
            setContentPane(chartPanel);

        JFrame frame = new JFrame("Association Errors by User");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    // Create a JTable to display the data
        String[] columnNames = { "User", "Error Count" };
        Object[][] data = new Object[errorByUser.size()][2];
    int i = 0;
    for (Map.Entry<String, Integer> entry : errorByUser.entrySet()) {
        data[i][0] = entry.getKey();
        data[i][1] = entry.getValue();
        i++;
    }
        JTable table = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(table);

        // Add the JTable and ChartPanel to the frame
        frame.add(scrollPane, BorderLayout.NORTH);
        frame.add(chartPanel, BorderLayout.SOUTH);
        frame.setVisible(true);
        frame.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}//ぶるぶ:3
