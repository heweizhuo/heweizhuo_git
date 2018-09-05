package Other;

import java.util.*;

public class getSkyline {
    public List<int[]> getSkyline(int[][] buildings) {
        List<int[]> res=new ArrayList<>();
        List<int[]> list=new LinkedList<>();
        for (int i=0;i<buildings.length;i++){
            list.add(new int[]{buildings[i][0],-buildings[i][2]});
            list.add(new int[]{buildings[i][1],buildings[i][2]});
        }
        Collections.sort(list, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if(o1[0]!=o2[0])
                    return o1[0]-o2[0];
                return o1[1]-o2[1];
            }
        });
        PriorityQueue<Integer> Skyline=new PriorityQueue<>((a,b)->(b-a));
        Skyline.offer(0);
        int prev=0;
        for (int[] h:list){
            if(h[1]<0)
                Skyline.offer(-h[1]);
            else Skyline.remove(h[1]);
            int cur=Skyline.peek();
            if(cur!=prev){
                res.add(new int[]{h[0],cur});
                prev=cur;
            }
        }
        return res;
    }
}
