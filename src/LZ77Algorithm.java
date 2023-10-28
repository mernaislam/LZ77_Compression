import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class LZ77Algorithm {
    private Compression c;
    private Decompression d;
    String file = "";
    public LZ77Algorithm(Compression c, Decompression d) {
        this.c = c;
        this.d = d;
    }

    public void readFile(){
        Scanner sc = new Scanner(System.in);
        System.out.print("Please enter the filename: ");
        String fileName = sc.nextLine(), content;
        Path path = Path.of(fileName);
        try {
            content = Files.readString(path);
//            c.setInput(content);
            d.setInput(content);
            file = fileName;
        }
        catch (IOException ex) {
            System.out.println("The file does not exist.");
        }
    }
    public void writeToFile(String str) {
        if (file != "") {
            Path path = Paths.get(file);
            try {
                Files.writeString(path, str, StandardCharsets.UTF_8);
                System.out.println("Done Successfully!");
            } catch (IOException ex) {
                System.out.println("The file does not exist.");
            }
        }
    }

    public static void main(String[] args) {
        LZ77Algorithm l = new LZ77Algorithm(new Compression(), new Decompression());
        System.out.println("LZ77 Algorithm - choose your required functionality [1-3]:");
        while(true){
            System.out.println("1. Compression \n2. Decompression \n3. Exit");
            Scanner sc = new Scanner(System.in);
            int x = sc.nextInt();
            switch (x){
                case 1:
                    l.readFile();
//                    l.c.compress();
                    break;
                case 2:
                    l.readFile();
                    l.writeToFile(l.d.decompress());
                    break;
                case 3:
                    System.out.println("Thank you!");
                    System.exit(0);
                default:
                    System.out.println("Invalid number, please choose between 1 - 3");
            }
        }
    }
}
