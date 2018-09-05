package Math;

class Point {
      int x;
      int y;
      Point() { x = 0; y = 0; }
      Point(int a, int b) { x = a; y = b; }
 }

public class maxPoints {
    public static void main(String[] args){
        System.out.println(maxPoints1(new Point[]{new Point(84,250),new Point(0,0),new Point(1,0),
                new Point(0,-70),new Point(0,-70),new Point(1,-1),
                new Point(21,10),new Point(42,90),new Point(-42,-230)}));
    }
    //y1、y2重合的时候要特殊处理
    public static int maxPoints1(Point[] points) {
        int max=0,duplicate=0;
        for (int i=0;i<points.length;i++){
            duplicate=1;
            for(int j=i+1;j<points.length;j++){
                long x1=points[i].x,x2=points[j].x,y1=points[i].y,y2=points[j].y;
                if(x1==x2 && y1==y2){duplicate++;continue;}
                int tmp=0;
                for (int k=0;k<points.length;k++){
                    long x3=points[k].x,y3=points[k].y;
                    if((x3-x2)*(y1-y2)-(x1-x2)*(y3-y2)==0)
                        tmp++;
                }
                max=Math.max(max,tmp);
            }
            max=Math.max(max,duplicate);
        }
        return max;
    }
}
