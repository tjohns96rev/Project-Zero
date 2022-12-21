package com.revature.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LogParser {
    private static File logFile = new File("D:\\repos\\P0-Template-Trevor\\logs\\rollingFile.log");
    private List<Integer> codes = new ArrayList<>();
    private List<Float> responseTimes = new ArrayList<>();

    public String getLogMetrics() {
        Pattern pattern = Pattern.compile("(?<=took)(.*)(?=ms)");

        try {
            Scanner fileScanner = new Scanner(logFile);
            while (fileScanner.hasNext()) {
                String currLine = fileScanner.nextLine();
                if (currLine.contains("Response") != true) {
                    continue;
                }
                codes.add(Integer.parseInt(currLine.substring(11, 14)));
                Matcher matcher = pattern.matcher(currLine);
                if (matcher.find()) {
                    responseTimes.add(Float.parseFloat(matcher.group(1).strip()));
                }
            }
            int totalCodes = 0;
            int badCodes = 0;
            for (Integer code : codes) {
                totalCodes++;
                if (code == 500) {
                    badCodes++;
                }
            }
            float successRate = (totalCodes - badCodes) / totalCodes * 100;
            float totalResponseTime = 0;
            float highestResponseTime = 0;
            int totalResponses = 0;
            for (Float responseTime : responseTimes) {
                totalResponseTime += responseTime;
                totalResponses++;
                if (responseTime > highestResponseTime) {
                    highestResponseTime = responseTime;
                }
            }
            float averageResponseTime = totalResponseTime / totalResponses;
            String logMetrics = "The success rate is " + successRate + "%.\n The average response time was "
                    + averageResponseTime
                    + " ms. The highest response time was " + highestResponseTime + " ms.";
            System.out.println(logMetrics);
            return logMetrics;
        } catch (FileNotFoundException e) {
            return e.getMessage();
        }

    }

    public static void main(String[] args) {
        LogParser lp = new LogParser();
        lp.getLogMetrics();
    }
}
