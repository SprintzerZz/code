class LongestPalindrome {
    //记录回文串的起点和终点
    int palindStart=0;
    int palindEnd=1;
    public String longestPalindrome(String s) {
        //0和1的情况
        if(s.length()==0 || s.length()==1) return s;

        //2个以上的情况
        for(int i=1;i<s.length();i++){
            //中心扩散
            scan(s,i,i);// babad
            scan(s,i-1,i);//cbbd
        }
        return s.substring(palindStart,palindEnd);//[)
    }
    public void scan(String s,int start,int end) {
        while(start>=0 && end<s.length()){
            if(s.charAt(start)==s.charAt(end)){
                start--;
                end++;
            }else{
                break;
            }

        }
        //这块需要注意
        //cbbd
        //s  e
        //在长度的计算上end-start-1
        //又因为[),所以 palindStart=start+1，end不变
        if(end-start-1> palindEnd-palindStart){
            palindStart=start+1;
            palindEnd=end;
        }
    }

    public static void main(String[] args) {
        String[] testCases = {"babad", "cbbd", "a", "ac", "racecar", ""};

        for (String input : testCases) {
            LongestPalindrome solution = new LongestPalindrome();
            String result = solution.longestPalindrome(input);
            System.out.println("输入: \"" + input + "\"，最长回文子串: \"" + result + "\"");
        }
    }
}
