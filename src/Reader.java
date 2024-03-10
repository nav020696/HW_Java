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

    //Задание 3: Вычислить частоту слов
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
                    addToMap(word.toString().toLowerCase());
                    word.setLength(0);
                    symbol = reader.read();
                }
            }
            addToMap(word.toString().toLowerCase());
            word.setLength(0);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void writeToFile(){
        try(BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"))){
            writer.write("Количество слов: " + countOfWords() + "\n");
            writer.write("Самое длинное слово: " + theLongestWord() + "\n");
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
        if (!newWord.equals("")){
            if (map.containsKey(newWord)){
                int count = map.get(newWord);
                map.put(newWord, ++count);
            }else{
                map.put(newWord, 1);
            }
        }
    }

    //Задание 1: Осуществить подсчет слов
    private int countOfWords(){
        int count = 0;
        for (Map.Entry<String, Integer> entry : map.entrySet()){
            count += entry.getValue();
        }
        return count;
    }

    //Задание 2: Найти самое длинное слово
    private String theLongestWord(){
        String word = "";
        int maxlLength = 0;
        for (Map.Entry<String, Integer> entry : map.entrySet()){
            if (entry.getKey().length() > maxlLength){
                word = entry.getKey();
                maxlLength = entry.getKey().length();
            }
        }
        return word;
    }
}
