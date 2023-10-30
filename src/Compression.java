import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Compression {

    public void compress(){
        // open file
        Scanner sc = new Scanner(System.in);
        System.out.print("Please enter the filename to compress: ");
        String fileName = sc.next();
        String fullPath = fileName + ".txt";
        Path path = Paths.get(fullPath);

        // start compress
        String searchWindow = "";
        int tagLength, pos, index;
        String compressed = "";
        try
        {
            String content = Files.readString(path, StandardCharsets.UTF_8);
            for (int i = 0; i < content.length(); i++)
            {
                tagLength = 0;
                pos = 0;
                index = i;
                boolean found = false;
                String matchChars = "";
                for (int j = 0; j < searchWindow.length(); j++)
                {
                    if(index >= content.length()) break;
                    if(content.charAt(index) == searchWindow.charAt(j))
                    {
                        matchChars += content.charAt(index);
                        found = true;
                        index++;
                    }
                    else if(found)
                    {
                        if(matchChars.length() > tagLength)
                        {
                            tagLength = matchChars.length();
                            pos = searchWindow.lastIndexOf(matchChars);
                        }
                        matchChars = "";
                        index = i;
                    }
                }
                if(!matchChars.isEmpty()){
                    if(matchChars.length() > tagLength)
                    {
                        tagLength = matchChars.length();
                        pos = searchWindow.lastIndexOf(matchChars);
                    }
                }
                String tag;
                if(!found)
                {
                    tag = "<0,0," + content.charAt(i) + ">";
                    searchWindow += content.charAt(i);
                }
                else
                {
                    tag = "<" + (i - pos) + "," + tagLength + ',';
                    if(i + tagLength >= content.length()) tag += "null" + ">";
                    else tag += content.charAt(i + tagLength) + ">";
                    for (int j = i; j <= i + tagLength && j < content.length(); j++) {
                        searchWindow += content.charAt(j);
                    }
                    i += tagLength;
                }
                compressed += tag;
            }
            String compressedFile = fileName + "_compressed.txt";
            Path pathCompressed = Paths.get(compressedFile);
            Files.writeString(pathCompressed, compressed, StandardCharsets.UTF_8);
            System.out.println("File compressed successfully in " + compressedFile);
        }
        catch (IOException e) {
            System.out.println("Error: File does not exist");
            System.out.print("Do you want to try again? (y/n): ");
            char choice = sc.next().charAt(0);
            if(choice == 'Y' || choice == 'y') compress();
        }
    }
}
