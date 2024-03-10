import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        Reader reader = new Reader(new HashMap());
        reader.run("src/input.txt");
    }
}