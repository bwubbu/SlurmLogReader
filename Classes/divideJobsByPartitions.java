    //divide the number of jobs by partitions (e.g. EPYC, Opteron, and GPU) from the extracted log.
    public void divideJobsByPartitions() throws ParseException {
        
    try{
        Scanner fileContent = new Scanner(new FileInputStream(fileName));
        int epycCount = 0;
        int opteronCount = 0;
        int gpuCount = 0;
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
        System.out.println("EPYC partition jobs: " + epycCount);
        System.out.println("Opteron partition jobs: " + opteronCount);
        System.out.println("GPU partition jobs: " + gpuCount);
        System.out.println("Total partition jobs: " + (epycCount + opteronCount + gpuCount));
        fileContent.close();
        }catch (Exception e){
    }
}
