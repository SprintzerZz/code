import java.util.HashMap;

class Solution {
    public static void main(String[] args) {
        /*ccbbcc的处理注意的细节：
        [c] max=1
        [cb] max=2
        [b] max=2
        [bc] max=2 
        主要是这里！在处理c的时候map中有{c:1,b:3}所以不能只是
        left=map.get(c)+1; map.get(c)+1=2,而是要和left比较，取大的值

         */
        String s = "ccbbcc";
        Solution solution = new Solution();
        System.out.println(solution.lengthOfLongestSubstring(s));
    }

    public int lengthOfLongestSubstring(String s) {
        HashMap <Character,Integer> map=new HashMap<Character,Integer>();
        int max=0;
        int left=0;
        
        for(int i=0;i<s.length();i++){
            //map中有保存过这个元素——>更新left
            char c=s.charAt(i);
            if(map.containsKey(c)){
                left=Math.max(left,map.get(c)+1);
            }
            //更新map
            map.put(c,i);
            max=Math.max(max,i-left+1);

        }
        return max;
        
    }
}
