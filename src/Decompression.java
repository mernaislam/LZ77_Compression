public class Decompression {
    private int pos;
    private int len;
    private String next = "";
    private String currWindow = "";
    private String input = "";

    public void setInput(String input) {
        this.input = input;
    }

    public String decompress(){
        if(!input.equals("")) {
            String[] tags = input.split(">");

            for (int i = 0; i < tags.length; i++) {
                String currTag = tags[i];
                currTag += ">";
                String tag = currTag.substring(currTag.indexOf("<") + 1, currTag.indexOf(">"));
                String[] attributes = tag.split(",");
                pos = Integer.parseInt(attributes[0]);
                len = Integer.parseInt(attributes[1]);
                next = attributes[2];

                if (pos == 0 && len == 0) {
                    if(!next.equals("null"))
                        currWindow += next;
                }
                else {
                    int begin = currWindow.length() - pos;
                    for(int j = 0;j<len;j++){
                        currWindow += currWindow.charAt(begin);
                        begin++;
                    }
                    if(!next.equals("null"))
                        currWindow += next;
                }
            }
        }
        return currWindow;
    }
}