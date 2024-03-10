import java.io.*;
import java.util.Map;

public class Reader {
    private Map<String, Integer> map;

    public Reader(Map map) {
        this.map = map;
    }

    public void run(String filepath){
        if (filepath.isEmpty()){
            System.err.println("Пустое путь до файла для считывания данных");
        }else{
            readFromFile(filepath);
            writeToFile();
        }
    }

    private void readFromFile(String filepath){
        try(BufferedReader reader = new BufferedReader(new FileReader(filepath))){
            StringBuilder word = new StringBuilder();
            int symbol = reader.read();
            while(symbol != -1){
                if(symbol != 32){
                    word.append((char) symbol);
                    symbol = reader.read();
                }
                else{
                    addToMap(word.toString());
                    word.setLength(0);
                    symbol = reader.read();
                }
            }
            addToMap(word.toString());
            word.setLength(0);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void writeToFile(){
        try(BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"))){
            StringBuilder string = new StringBuilder();
            for (Map.Entry<String, Integer> entry : map.entrySet()){
                string.append(entry.getKey()).append(" - ").append(entry.getValue()).append("\n");
                writer.write(string.toString());
                string.setLength(0);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void addToMap(String newWord){
        if (map.containsKey(newWord)){
            int count = map.get(newWord);
            map.put(newWord, ++count);
        }else{
            map.put(newWord, 1);
        }
    }
}
