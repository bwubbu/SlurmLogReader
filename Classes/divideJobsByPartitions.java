import java.awt.BorderLayout;
import java.awt.Color;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

public class divideJobsByPartitions {
    public static void main(String[] args) throws ParseException {
        String fileName = "extracted_log";
        
        // create a dataset for the chart
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
            int epycCount = 0;
            int opteronCount = 0;
            int gpuCount = 0;
        try {
            Scanner fileContent = new Scanner(new FileInputStream(fileName));
            while (fileContent.hasNextLine()) {
                String line = fileContent.nextLine();
                if (line.contains("Partition=cpu-epyc")) {
                    epycCount++;
                } else if (line.contains("Partition=cpu-opteron")) {
                    opteronCount++;
                } else if (line.contains("Partition=gpu")) {
                    gpuCount++;
                }
            }
            dataset.addValue(epycCount, "EPYC", "Jobs");
            dataset.addValue(opteronCount, "Opteron", "Jobs");
            dataset.addValue(gpuCount, "GPU", "Jobs");
            fileContent.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        JFrame frame = new JFrame("Jobs by Partition");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // bar chart
        JFreeChart chart = ChartFactory.createBarChart("Jobs by Partition", "Partition", "Num of Job", dataset);
        chart.getTitle().setPaint(Color.black);
        CategoryPlot p = chart.getCategoryPlot();
        p.setRangeGridlinePaint(Color.black);
        BarRenderer renderer = (BarRenderer) p.getRenderer();
        renderer.setSeriesPaint(0, Color.blue);
        renderer.setSeriesPaint(1, Color.green);
        renderer.setSeriesPaint(2, Color.red);
        ChartPanel chartPanel = new ChartPanel(chart);
        frame.add(chartPanel);
        frame.pack();
        frame.setVisible(true);

        // table
        String[] columnNames = {"Partition", "Num of Job"};
        Object[][] data = {{"EPYC", epycCount}, {"Opteron", opteronCount}, {"GPU", gpuCount}};
        JTable table = new JTable(data, columnNames);
        table.setEnabled(false);
        JScrollPane scrollPane = new JScrollPane(table);

        // combine chart and table in the frame
        frame.add(chartPanel);
        frame.getContentPane().add(chartPanel, BorderLayout.WEST);
        frame.getContentPane().add(scrollPane, BorderLayout.EAST);
        frame.pack();
    }
}//ぶるぶ:3
