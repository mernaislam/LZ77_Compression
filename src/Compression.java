import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Scanner;

public class Compression {
    String searchWindow = "";
    Integer tagPosition;
    String content = "";

//    public boolean checkSearchWindow(char c){
//        for (int i = 0; i < searchWindow.length(); i++) {
//            if(searchWindow.charAt(i) == c)
//        }
//    }

    public int longestSequence(int i, int j){
        // base case
        if(i == content.length() || j == searchWindow.length()) return 0;
        // take
        int opt1 = -1;
        if(content.charAt(i) == searchWindow.charAt(j)){
            opt1 = longestSequence(i+1, j+1) + 1;
        }

        // leave
        int opt2 = longestSequence(i, j+1);

        return Math.max(opt1, opt2);
    }
    public void compress(){
        Scanner sc = new Scanner(System.in);
        System.out.print("Please enter the filename to compress: ");
        String fileName = sc.next();
        String fullPath = fileName + ".txt";
        Path path = Paths.get(fullPath);
        try {
            content = Files.readString(path, StandardCharsets.UTF_8);
            int tagLength, pos;
            String compressed = "";
            for (int i = 0; i < content.length(); i++) {
                pos = 0;
                int index = i;
                tagLength = longestSequence(i, 0);
                System.out.print(tagLength + " ");
//                boolean found = false;
//                for (int j = 0; j < searchWindow.length(); j++) {
//                    if(i == content.length()) break;
//                    if(content.charAt(i) == searchWindow.charAt(j)){
//                        // shift both
//                        i++;
//                        tagLength++;
//                        if(pos == 0) pos = j;
//                        found = true;
//                    }
//                    else if(found)
//                    {
//                        break;
//                    }
//                }
                String tag;
                if(tagLength == 0)
                {
                    tag = "<0,0," + content.charAt(i) + ">";
                    searchWindow += content.charAt(i);
                }
                else
                {
                    if(i == content.length()) break;
                    tag = "<" + (index - pos) + "," + tagLength + ',' + content.charAt(i) + ">";
                    for (int j = index; j <= index+tagLength; j++) {
                        searchWindow+= content.charAt(j);
                    }
                }
                compressed += tag;
            }
            for (int i = 0; i < compressed.length(); i++) {
                System.out.print(compressed.charAt(i));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
