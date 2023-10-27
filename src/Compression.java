import java.io.*;
import java.util.Scanner;

public class Compression {

    String searchWindow = "";

//    public boolean checkSearchWindow(char c){
//        for (int i = 0; i < searchWindow.length(); i++) {
//            if(searchWindow.charAt(i) == c)
//        }
//    }

    public void compress(){
        Scanner sc = new Scanner(System.in);
        System.out.print("Please enter the filename to compress: ");
        String fileName = sc.next();
        String fullPath = fileName + ".txt";
        File file = new File(fullPath);
        if(file.exists())
        {
            BufferedReader fileR = null;
            try {
                fileR = new BufferedReader(new FileReader(file));
                String line, pattern;
                while ((line = fileR.readLine()) != null){
                    for (int i = 0; i < line.length(); i++) {
//                        checkSearchWindow(line.charAt(i));
                    }
                }
                fileR.close();
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        else
        {
            System.out.println("This file does not exist, please try again.");
            compress();
        }
    }
}
