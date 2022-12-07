package dev.panzers1916.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/** Represents a Utils class
 * @author Konrad Nowak */

public class Utils {
    /** method which convert a txt file to String
     * @param path file path
     * @return string */
    public static String loadFileAsString(String path){
        StringBuilder builder = new StringBuilder();
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            String line;
            while((line = br.readLine()) != null){
                builder.append(line).append("\n");
            }
        } catch(IOException e){
            e.printStackTrace();
        }
        return builder.toString();
    }
    /** method which convert a txt file to int
     * @param number number of util
     * @return string */
    public static int parseInt(String number){
        try {
            return Integer.parseInt(number);
        } catch(NumberFormatException e){
            e.printStackTrace();
            return 0;
        }
    }
}
