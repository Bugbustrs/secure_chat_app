import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

public class KeyManager {




    static Map<String, String> getKeys(){
        String filename = "./src/main/resources/keys.json";
        Map<String,String> keyMap = new HashMap<>();

        try {
            String file = new File(filename).getAbsolutePath();
           //System.out.println(file);
            FileReader fr = new FileReader(file);
            BufferedReader bf = new BufferedReader(fr); //allows us to read the file line by line
            String line=null;
            while((line=bf.readLine())!=null){
                line = line.trim();
                String lineArr [] = line.split(":");
                keyMap.put(lineArr[0],lineArr[1]);
            }

        }
        catch (Exception e){
            e.printStackTrace();
        }



        return keyMap;

    }
}
