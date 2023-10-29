import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Compression {

    public void compress(){
        Scanner sc = new Scanner(System.in);
        System.out.print("Please enter the filename to compress: ");
        String fileName = sc.next();
        String fullPath = fileName + ".txt";
        Path path = Paths.get(fullPath);
        String searchWindow = "";
        int tagLength, pos, index;
        String compressed = "";
        Map<Integer, Integer> mp = new HashMap<>();
        try
        {
            String content = Files.readString(path, StandardCharsets.UTF_8);
            for (int i = 0; i < content.length(); i++)
            {
                mp.clear();
                tagLength = 0;
                pos = 0;
//                        break;
                index = i;
                boolean found = false, flag = false;
                for (int j = 0; j < searchWindow.length(); j++)
                {
                    if(content.charAt(i) == searchWindow.charAt(j))
                    {
                        if(!found) pos = j;
                        flag = true;
                        i++;
                        tagLength++;
                        found = true;
                    }
                    else if(found)
                    {
                       mp.put(tagLength, pos);
                       tagLength = 0;
                       pos = 0;
                       found = false;
                       i = index;
                    }
                    if(found && (j == searchWindow.length()-1 || i == content.length())){
                        mp.put(tagLength, pos);
                        i = index;
                    }
                }
                String tag;
                if(!flag)
                {
                    tag = "<0,0," + content.charAt(i) + ">";
                    searchWindow += content.charAt(i);
                }
                else
                {
                    int maxLength = 0, currentPos = 0;
                    for (Map.Entry<Integer, Integer> entry : mp.entrySet()){
                        if(maxLength < entry.getKey()){
                            maxLength = entry.getKey();
                            currentPos = entry.getValue();
                        }
                    }
                    tag = "<" + (index - currentPos) + "," + maxLength + ',' + content.charAt(i + maxLength) + ">";
                    for (int j = index; j <= index+maxLength; j++)
                    {
                        searchWindow += content.charAt(j);
                    }
                    if(searchWindow.length() > 12){
                        searchWindow = searchWindow.substring(searchWindow.length()-12);
                    }
                    i += maxLength;
                }
                compressed += tag;
            }
            for (int i = 0; i < compressed.length(); i++)
            {
                System.out.print(compressed.charAt(i));
            }
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
