package Other;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class evalRPN {
    public static void main(String[] args){
        System.out.println(evalRPN1(new String[]{"10","6","9","3","+","-11","*","/","*","17","+","5","+"}));
    }
    static Set<String > set=new HashSet<String>(){{add("+");add("-");add("*");add("/"); }};
    public static int evalRPN1(String[] tokens) {
        List<String> queue=new LinkedList<>();
        for (int i=0;i<tokens.length;i++){
            if(set.contains(tokens[i]) && queue.size()>=2){
                int opNum=Integer.valueOf(queue.get(queue.size()-2));
                int opNumed=Integer.valueOf(queue.get(queue.size()-1));
                queue.remove(queue.size()-1);
                queue.remove(queue.size()-1);
                switch (tokens[i]){
                    case "+":queue.add(String.valueOf(opNum+opNumed));break;
                    case "-":queue.add(String.valueOf(opNum-opNumed));break;
                    case "*":queue.add(String.valueOf(opNum*opNumed));break;
                    case "/":queue.add(String.valueOf(opNum/opNumed));break;
                }
            }
            else queue.add(tokens[i]);
        }
        return Integer.valueOf(queue.get(0));
    }
}
