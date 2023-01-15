    //Number of jobs causing error and the corresponding user.
    public void ErrorDetection() throws ParseException {
        
        try {
            Scanner fileContent = new Scanner(new FileInputStream(fileName));
            int errorCount = 0;
            Map<String, Integer> errorByUser = new HashMap<>();//HashMap is used to keep track of the number of errors caused by each unique user.
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
            System.out.println("+------------------------------------+");
            System.out.println(String.format("| %-20s | %-11s |","User","Error Count"));
            System.out.println("|------------------------------------|");
            for (Map.Entry<String, Integer> entry : errorByUser.entrySet()) {
                String user = entry.getKey();
                String noComma = user.replaceAll(",$"," "); //To remove the comma on the list of usernames
                String noSingleQuote = noComma.replaceAll("'"," ");//To remove the single quote on the list of usernames
                int userErrorCount = entry.getValue();
                System.out.println(String.format("| %-20s | %-11d |", noSingleQuote, userErrorCount));
                System.out.println("+------------------------------------+");
            }
            System.out.println(String.format("| %-20s | %-11d |","Sum of Errors",errorCount));
            System.out.println("|------------------------------------|");
            fileContent.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
