package org.marathe.javatraining.jsonreader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.opencsv.CSVWriter;
 
/**
 * @author Mukesh Marathe
 */
 
public class JSONReaderToCSVFile {
 
    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        JSONParser parser = new JSONParser();
        int count = 0;
        try {
        	List<String[]> data = new ArrayList<String[]>();
        	
            Object obj = parser.parse(new FileReader("C://Users//Mukesh//Desktop//response.json"));
            String filePath = "C://Users//Mukesh//Desktop//result.csv";
            JSONObject jsonObject = (JSONObject) obj;
            JSONArray companyList = (JSONArray) jsonObject.get("items");          
            //System.out.println("\nCompany List:");
            data.add(new String[] { "displayName", "repositoryId"});
            Iterator<JSONObject> iterator = companyList.iterator();
            while (iterator.hasNext()) {
                //System.out.println(iterator.next());
                JSONObject jsonObj = (JSONObject) iterator.next();
                String displayName = (String) jsonObj.get("displayName");
                String repositoryId = (String) jsonObj.get("repositoryId");
                System.out.println("displayName: " + displayName);
                System.out.println("repositoryId: " + repositoryId);
                count++;                 
                data.add(new String[] { displayName, repositoryId }); 
            }
            writeDataAtOnce(filePath, data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
 
    public static void writeDataAtOnce(String filePath, List<String[]> data) 
    { 
      
        // first create file object for file placed at location 
        // specified by filepath 
        File file = new File(filePath); 
      
        try { 
            // create FileWriter object with file as parameter 
            FileWriter outputfile = new FileWriter(file); 
      
            // create CSVWriter object filewriter object as parameter 
            CSVWriter writer = new CSVWriter(outputfile); 
            writer.writeAll(data); 
      
            // closing writer connection 
            writer.close(); 
        } 
        catch (IOException e) { 
            // TODO Auto-generated catch block 
            e.printStackTrace(); 
        } 
    }
}
