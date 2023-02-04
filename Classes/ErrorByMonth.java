import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javax.swing.JTable;
import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

public class ErrorByMonth {
    public static int tt=0, june=0,july=0,august=0,september=0,october=0,november=0,december=0;  //to insert the values of the errors

    public static void main(String[] args) {
        try {
            parseFile("extracted_log", "error");
        }catch (FileNotFoundException e) {
        e.printStackTrace();
    }
}
    public static void parseFile(String fileName, String searchStr) throws FileNotFoundException {
        try{
            Scanner scan = new Scanner (new File (fileName));

        while (scan.hasNextLine()){
            String line = scan.nextLine().toLowerCase();
            if (line.contains(searchStr)){
                //Setting conditions 
                if (line.contains("2022-06")){
                    june++;
                }
                if (line.contains("2022-07")){
                    july++;
                }
                if (line.contains("2022-08")){
                    august++;
                }
                if (line.contains("2022-09")){
                    september++;
                }
                if (line.contains("2022-10")){
                    october++;
                }
                if (line.contains("2022-11")){
                    november++;
                }
                if (line.contains("2022-12")){
                    december++;
                }
                tt++;
            }
        }
        scan.close();                                           //closes the scanner so the resources do not leak
        
        // Display table of months and the error count
        String[] columnNames = {"Month", "Error Count"};
        Object[][] data = {
        {"June", june},
        {"July", july},
        {"August", august},
        {"September", september},
        {"October", october},
        {"November", november},
        {"december", december},
        {"Total errors", tt}};
        
        //Create the table
        JTable table = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(table);
        JFrame tableFrame = new JFrame();
        tableFrame.add(scrollPane, BorderLayout.CENTER);
        tableFrame.setSize(560, 200);
        tableFrame.setVisible(true);
        
        // Create a dataset for the bar chart
        DefaultCategoryDataset bar = new DefaultCategoryDataset();
        bar.setValue(june, "Error Count", "June");
        bar.setValue(july, "Error Count", "July");
        bar.setValue(august, "Error Count", "August");
        bar.setValue(september, "Error Count", "September");
        bar.setValue(october, "Error Count", "October");
        bar.setValue(november, "Error Count", "November");
        bar.setValue(december, "Error Count", "December");
        
        // Create a bar chart
        JFreeChart barChart = ChartFactory.createBarChart("Errors by Month", "Month", "Error Count", bar, PlotOrientation.VERTICAL, true, true, false);     //first true is to display legend, second for tooltips, third for URLs
        
        // Create the panel for the barchart
        ChartPanel barpanel = new ChartPanel(barChart);
        barpanel.setPreferredSize(new java.awt.Dimension(560, 367));
        final CategoryPlot plot = barChart.getCategoryPlot();                       // plots the bar chart
        final NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();          
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());        // y-axis will have integer values

        JFrame barframe = new JFrame("Errors by Month Chart");
        barframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);        // Exit the application when the close button is clicked
        barframe.setContentPane(barpanel);                              // Sets the bar chart to be the content
        barframe.pack();                                                // size the frame to follow preffered size
        barframe.setVisible(true);                                      // Makes the frame visible
        }catch (FileNotFoundException e) {                              //if the file can't be found.
            System.out.println("Error: File not found.");
        }
    }
}//ぶるぶ:3
