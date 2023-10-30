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
        String fileName = sc.nextLine();
        Path path = Path.of("LZ77_Compression/" + fileName);
        try {
            String content = Files.readString(path);
            c.setInput(content);
            d.setInput(content);
            file = fileName;
        }
        catch (IOException ex) {
            System.out.println("The file does not exist.");
            System.out.print("Do you want to try again? (y/n): ");
            char choice = sc.next().charAt(0);
            if(choice == 'Y' || choice == 'y') readFile();
        }
    }
    public void writeToFile(String str, char option) {
        if (file != "") {
            int i = file.indexOf(".");
            int j = file.indexOf("_");
            int x = j;
            if(j == -1) x = i;
            String newName;
            if(option == 'd')
                newName = file.substring(0,x) + "_decompressed.txt";
            else
                newName = file.substring(0,x) + "_compressed.txt";
            Path path = Paths.get("LZ77_Compression/" + newName);
            try {
                Files.writeString(path, str, StandardCharsets.UTF_8);
                System.out.println("Done Successfully!");
            }
            catch (IOException ex) {
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
                    l.writeToFile(l.c.compress(), 'c');
                    break;
                case 2:
                    l.readFile();
                    l.writeToFile(l.d.decompress(), 'd');
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