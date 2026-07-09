# SLURM Log Reader

A Java-based utility for analyzing and extracting meaningful metrics from SLURM (Simple Linux Utility for Resource Management) log files. This tool processes HPC (High Performance Computing) cluster logs to provide insights into job execution patterns, errors, and resource utilization.

## 📋 Overview

SLURM Log Reader extracts and analyzes data from SLURM log files, providing comprehensive metrics about job submissions, executions, and resource allocation across HPC clusters. It supports analysis of jobs across different partitions (EPYC, Opteron, GPU) and generates detailed reports on job performance and errors.

## 🎯 Key Features

- **Time Range Analysis**: Count jobs created/ended within specified time ranges
- **Partition Analysis**: Segregate and analyze jobs by partition (EPYC, Opteron, GPU)
- **Error Detection**: Identify jobs with errors and associated users
- **Error Classification**: Track error types and their occurrences
- **Monthly Breakdown**: Extract and analyze data by month
- **Performance Metrics**: Calculate average execution times for all submitted jobs
- **Trend Analysis**: Monitor errors and job cancellations by month

## 📊 Supported Metrics

- Number of jobs created/ended within a given time range
- Number of jobs by partitions (EPYC, Opteron, GPU)
- Number of jobs causing errors and the corresponding user
- Error types and occurrences
- Monthly data extraction and analysis
- Total average execution time of jobs submitted to UMHPC (all months)
- Number of errors by month
- Number of jobs cancelled by user by month

## 📁 Project Structure

```
SlurmLogReader/
├── README.md                          # This file
├── Assignment.docx                    # Assignment specifications
├── extracted_log                      # Sample SLURM log file
└── Classes/                           # Java source code
    ├── Main.java                      # Entry point
    ├── JobDateDetector.java           # Date/time parsing for job logs
    ├── AvgExecTime.java               # Average execution time calculations
    ├── ErrorByMonth.java              # Monthly error analysis
    ├── ErrorChart.java                # Error visualization and reporting
    ├── DataByMonth.java               # Monthly data extraction
    ├── UserAssErrorDetect.java        # User-associated error detection
    └── divideJobsByPartitions.java    # Partition-based job segregation
```

## 🚀 Getting Started

### Prerequisites

- Java 8 or higher
- A SLURM log file to analyze

### Compilation

Compile all Java files:

```bash
cd Classes
javac *.java
```

### Usage

Run the main program:

```bash
java -cp . Main
```

Or compile and run directly:

```bash
javac Classes/*.java
java -cp Classes Main
```

## 📖 Component Descriptions

### Main.java
Entry point for the application. Orchestrates the log reading and analysis workflow.

### JobDateDetector.java
Parses dates and times from SLURM log entries. Handles job creation and completion timestamps.

### AvgExecTime.java
Calculates average execution times for jobs across the entire dataset. Computes statistics for job duration analysis.

### ErrorByMonth.java
Analyzes and reports on errors grouped by month. Provides monthly error trends and statistics.

### ErrorChart.java
Generates detailed error reports and visualizations. Categorizes errors by type and frequency.

### DataByMonth.java
Extracts and organizes job data by month. Supports monthly trend analysis.

### UserAssErrorDetect.java
Identifies which users have jobs that encountered errors. Associates error occurrences with specific users.

### divideJobsByPartitions.java
Categorizes jobs based on their assigned partition (EPYC, Opteron, GPU). Provides partition-specific analysis.

## 📥 Input Format

The application expects SLURM log files in standard SLURM format. See `extracted_log` file for an example of the expected format.

## 📈 Output

The application generates various outputs including:
- Console-based reports
- Error summaries by month
- Job counts by partition
- User-associated error lists
- Average execution time statistics

## 🎓 Context

This project was created as part of a university assignment focused on data analysis and processing of HPC cluster logs. It demonstrates practical Java programming with file I/O, data structures, and statistical analysis.

## 🛠️ Technologies Used

- **Language**: Java
- **I/O**: File reading and parsing
- **Data Structures**: Collections for storing and analyzing log data
- **Algorithms**: Time-series analysis, grouping, aggregation

## 📝 Notes

- The `Assignment.docx` file contains the original assignment specifications and requirements
- The `extracted_log` file is a sample SLURM log for testing and validation
- All analysis is performed on the local machine without external dependencies

## 🤝 Contributing

This is an assignment-based project. Feel free to fork and experiment with enhancements!

## 📧 Contact

For questions or suggestions, feel free to open an issue in the repository.

---
