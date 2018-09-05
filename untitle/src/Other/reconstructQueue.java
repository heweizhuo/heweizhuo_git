package Other;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class reconstructQueue {
    public static void main(String[] args){
        reconstructQueue(new int[][]{{7,0}, {4,4}, {7,1}, {5,0}, {6,1}, {5,2}});
    }
    public static int[][] reconstructQueue(int[][] people) {
        Arrays.sort(people, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if(o1[0]!=o2[0])
                    return o2[0]-o1[0];
                return o1[1]-o2[1];
            }
        });
        List<int[]> res=new LinkedList<>();
        for (int i=0;i<people.length;i++){
            if(people[i][1]<i){
                res.add(people[i][1],new int[]{people[i][0],people[i][1]});
            }else res.add(new int[]{people[i][0],people[i][1]});
        }
        for (int i=0;i<res.size();i++){
            people[i][0]=res.get(i)[0];
            people[i][1]=res.get(i)[1];
        }
        return people;
    }
}
