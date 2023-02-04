import java.io.File;
import java.awt.*;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javax.swing.*;
import javax.swing.JFrame;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

// ErrorChart Types and its Occurences
public class ErrorChart {
    public static final String[] node_error = {"error: nodes umhpc", "error: nodes cpu", "error: _find_node_record", "error: node_name2bitmap", //based on the extracted_log
                                               "error: node gpu", "error: nodes management", "error: node cpu", "error: configured cpu",
                                               "error: validate_node_specs", "error: nodes gpu"};

    public static final String[] job_error = {"error: prolog launch failure", "error: registered pending", "error: valid_job_resources", "error: job_epilog_complete", 
                                              "error: slurmd error running","_slurm_rpc_requeue"};

    public static final String[] sec_error = {"error: security"};

    public static final String[] qos_error = {"error: this association", "error: invalid qos"};

    public static final String[] com_error = {"error: slurm_receive_msg", "error: slurm_msg_sendto"};

    public static final String[] other_error = {"error: _handle_assoc_tres_run_secs", "error: user", "error: sched:", "error: _slurm_rpc_reconfigure_controller",
                                                "error: aborting", "error: _slurm_rpc_epilog_complete"};

public static void main(String[] args) {
    try {

    Scanner scan = new Scanner(new File("extracted_log"));      //scans the log
    int nodeErrorCnt = 0;                                       // initialize the number count for errors
    int jobErrorCnt = 0;
    int secErrorCnt = 0;
    int qosErrorCnt = 0;
    int comErrorCnt = 0;
    int otherErrorCnt = 0;

        while (scan.hasNextLine()) {
            String line = scan.nextLine().toLowerCase();
            for (String nodeError : node_error) {
                if (line.contains(nodeError)) {
                    nodeErrorCnt++;
                }
            }
            for (String jobError : job_error) {
                if (line.contains(jobError)) {
                    jobErrorCnt++;
                }
            }
            for (String securityError : sec_error) {
                if (line.contains(securityError)) {
                    secErrorCnt++;
                }
            }
            for (String qosError : qos_error) {
                if (line.contains(qosError)) {
                    qosErrorCnt++;
                }
            }
            for (String comError : com_error) {
                if (line.contains(comError)) {
                    comErrorCnt++;
                }
            }
            for (String otherError : other_error) {
                if (line.contains(otherError)) {
                    otherErrorCnt++;
                }
            }
        }
        scan.close();                                           //closes the scanner so the resources do not leak

        // Display table of error types and counts
        int totalErrorCnt = nodeErrorCnt+jobErrorCnt+secErrorCnt+qosErrorCnt+comErrorCnt+otherErrorCnt;
        System.out.println("Node-related errors: " + nodeErrorCnt);
        System.out.println("Job-related errors: " + jobErrorCnt);
        System.out.println("Security-related errors: " + secErrorCnt);
        System.out.println("Quality of Service (QoS)-related errors: " + qosErrorCnt);
        System.out.println("Communication-related errors: " + comErrorCnt);
        System.out.println("Other errors: " + otherErrorCnt);
        System.out.println("Total Errors: " + totalErrorCnt);
            scan.close();

        // Create a dataset for the pie chart
        DefaultPieDataset pie = new DefaultPieDataset();
        pie.setValue("Node-related errors", nodeErrorCnt);
        pie.setValue("Job-related errors", jobErrorCnt);
        pie.setValue("Security-related errors", secErrorCnt);
        pie.setValue("Quality of Service (QoS)-related errors", qosErrorCnt);
        pie.setValue("Communication-related errors", comErrorCnt);
        pie.setValue("Other errors", otherErrorCnt);

        // Create a chart
        JFreeChart chart = ChartFactory.createPieChart("Error Chart", pie, true, true, false);      //first true is to display legend, second for tooltips, third for URLs

        // Create a frame to display the chart
        ChartFrame pieframe = new ChartFrame("Error Chart", chart);
        pieframe.setVisible(true);
        pieframe.setSize(450, 500);

        String[] columnNames = {"Error Type", "Count"};
        Object[][] data = {
        {"Node-related errors", nodeErrorCnt},
        {"Job-related errors", jobErrorCnt},
        {"Security-related errors", secErrorCnt},
        {"Quality of Service (QoS)-related errors", qosErrorCnt},
        {"Communication-related errors", comErrorCnt},
        {"Other errors", otherErrorCnt},
        {"Total errors", totalErrorCnt}};

        JTable table = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(table);
        table.setFillsViewportHeight(true);

        JFrame tableframe = new JFrame("Error Table");
        tableframe.add(scrollPane, BorderLayout.CENTER);
        tableframe.setSize(500, 200);
        tableframe.setVisible(true);
        }catch (FileNotFoundException e) {                  //if the file can't be found.
            System.out.println("Error: File not found.");
        }
    }  
}//ぶるぶ:3
