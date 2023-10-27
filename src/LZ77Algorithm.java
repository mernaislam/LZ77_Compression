import java.util.Scanner;

public class LZ77Algorithm {

    public static void startMenu(){
        Scanner sc = new Scanner(System.in);
        Compression c = new Compression();
        int x = sc.nextInt();
        switch (x){
            case 1 -> c.compress();
            case 2 -> System.out.println("Decompression...");
            case 3 -> System.out.println("Thank you!");
            default -> {
                System.out.println("Invalid number, please choose between 1 - 3");
                startMenu();
            }
        }
    }
    public static void main(String[] args) {
        System.out.println("LZ77 Algorithm - choose your required functionality [1-3]:");
        System.out.println("1. Compression \n2. Decompression \n3. Exit");
        startMenu();
    }
}
