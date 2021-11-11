/**
 *  @author: Yunxiang He
 *  @date  : 2018-10-25
 */

package questions.problems;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;

public class Hosts_and_the_Total_Number_of_Requests {

    public void log(String fileName) {
        Map<String, Integer> map = new HashMap<>();
        readFile(fileName, map);
        writeFile(map, "records_" + fileName);
    }

    private void readFile(String fileName, Map<String, Integer> map) {
        try (Reader in = new FileReader(fileName)) {
            int b;
            StringBuilder sBuilder = new StringBuilder();
            String tempString;
            boolean isApp = true;
            while ((b = in.read()) != -1) {
                char ch = (char) b;
                if (isApp) {
                    if (ch != ' ') {
                        sBuilder.append(ch);
                    } else {
                        tempString = sBuilder.toString();
                        map.put(tempString, map.getOrDefault(tempString, 0) + 1);
                        sBuilder = new StringBuilder();
                        isApp = false;
                    }
                }
                if (ch == '\n') {
                    isApp = true;
                }
            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeFile(Map<String, Integer> map, String fileName) {
        File file = new File(fileName);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (Map.Entry<String, Integer> entry : map.entrySet()) {
                writer.write(entry.getKey() + entry.getValue());
                writer.newLine();
            }
            writer.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Hosts_and_the_Total_Number_of_Requests().log("input.txt");
    }
}
