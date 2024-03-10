import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

public class Reader {
    private Map<String, Integer> map;

    public Reader(Map map) {
        this.map = map;
    }

    public void run(String filename){

    }

    private void readFromFile(String filename){
        try(BufferedReader reader = new BufferedReader(new FileReader(filename))){
            StringBuilder word = new StringBuilder();
            int symbol = reader.read();
            while(symbol != -1){
                if(symbol != 32){
                    word.append(symbol);
                    symbol = reader.read();
                }
                else{
                    if (map.containsKey(word.toString())){
                        int count = map.get(word.toString());
                        map.put(word.toString(), ++count);
                    }else{
                        map.put(word.toString(), 1);
                    }
                    word.setLength(0);
                    symbol = reader.read();
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
