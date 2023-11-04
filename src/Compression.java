public class Compression {
    private String input = "";
    public void setInput(String input) {
        this.input = input;
    }

    public String compress(){
        String searchWindow = "";
        int tagLength, pos, index;
        String compressed = "";
        for (int i = 0; i < input.length(); i++)
        {
            tagLength = 0;
            pos = 0;
            index = i;
            boolean found = false;
            String matchChars = "";
            for (int j = 0; j < searchWindow.length(); j++)
            {
                if(index >= input.length()) break;
                if(input.charAt(index) == searchWindow.charAt(j))
                {
                    matchChars += input.charAt(index);
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
                tag = "<0,0," + input.charAt(i) + ">";
                searchWindow += input.charAt(i);
            }
            else
            {
                tag = "<" + (i - pos) + "," + tagLength + ',';

                if(i + tagLength >= input.length())
                {
                    tag += "null" + ">";
                }
                else
                {
                    tag += input.charAt(i + tagLength) + ">";
                }

                for (int j = i; j <= i + tagLength && j < input.length(); j++) {
                    searchWindow += input.charAt(j);
                }
                i += tagLength;
            }
            compressed += tag;
        }
        return compressed;
    }
}