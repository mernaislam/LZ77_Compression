public class Decompression {
    private int pos;
    private int len;
    private char next;
    private String currWindow = "";
    private String input = "";

    public void setInput(String input) {
        this.input = input;
    }

    public String decompress(){
        if(input != "") {
            String[] tags = input.split(">");

            for (int i = 0; i < tags.length; i++) {
                String output = "";
                pos = -1;
                len = -1;
                String currTag = tags[i];

                for (int j = 0; j < currTag.length(); j++) {
                    if (Character.isDigit(currTag.charAt(j))) {
                        if (pos == -1)
                            pos = currTag.charAt(j) - '0';
                        else
                            len = currTag.charAt(j) - '0';
                    } else if (Character.isAlphabetic(currTag.charAt(j)))
                        next = currTag.charAt(j);
                }
                if (pos == 0 && len == 0) {
                    output += next;
                }
                else {
                    int begin = currWindow.length() - pos;
                    int end = begin + len;
                    output += currWindow.substring(begin, end);
                    output += next;
                }
                currWindow += output;
            }
        }
        return currWindow;
    }
}