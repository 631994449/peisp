package cn.edu.hziee.peisp.utils;

import cn.edu.hziee.peisp.entity.Position;

import java.util.ArrayList;
import java.util.List;

public class PositionCounter {
    private static Position positionA = new Position(0.0,0.0);
    private static Position positionB = new Position(3.0,4.0);
    private static Position positionC = new Position(6.0,0.0);
    private static int rssiA = 37;
    private static double n = 2.5;
    private static List<Position> ans= new ArrayList<>();
    public static Position count(double distanceA,double distanceB,double distanceC){
        //参考算法  https://blog.csdn.net/qq_17616169/article/details/72833869
        ans.clear();
        double x1 = positionA.getX(),y1 = positionA.getY();
        double x2 = positionB.getX(),y2 = positionB.getY();
        double r1 = distanceA,r2 = distanceB,r3 = distanceC;
        double a,b,c,delta = -1;
        double ansX1,ansX2,ansY1,ansY2;
        if(y1 != y2){
            double A = (x1*x1 - x2*x2 + y1*y1 - y2*y2 + r2*r2 - r1*r1) / (2*(y1 - y2));
            double B = (x1 - x2) / (y1 - y2);
            a = 1 + B*B;
            b = -2 * (x1 + (A - y1) * B);
            c = x1 * x1 + (A - y1) * (A - y1) - r1 * r1;
            delta = b * b - 4 * a * c;
            if (delta > 0){
                ansX1 = (-b + Math.sqrt(b * b - 4 * a * c)) / (2 * a);
                ansX2 = (-b - Math.sqrt(b * b - 4 * a * c)) / (2 * a);
                ansY1 = A - B * ansX1;
                ansY2 = A - B * ansX2;
            }else if (delta == 0){
                ansX1 = ansX2 = -b / ( 2 * a );
                ansY1 = ansY2 = A - B * ansX1;
            }else {
                System.out.println("两个圆不相较");
                return null;
            }
        }else if(x1 != x2){
            //当y1 = y2时，x的两个解相同
            ansX1 = ansX2 = (x1 * x1 - x2 * x2 + r2 * r2 - r1 * r1) / (2 * (x1 - x2));
            a = 1;
            b = -2 * y1;
            c = y1 * y1 - r1 * r1 + (ansX1 - x1) * (ansX1 - x1);
            delta = b*b - 4*a*c;
            if (delta > 0){
                ansY1 = (-b + Math.sqrt(b * b - 4 * a * c)) / (2 * a);
                ansY2 = (-b - Math.sqrt(b * b - 4 * a * c)) / (2 * a);
            }else if (delta == 0){
                ansY1 = ansY2 = -b/(2*a);
            }else {
                System.out.println("两个圆不相较");
                return null;
            }
        }else{
            System.out.println("无解");
            return null;
        }
        Position ans1 = new Position(ansX1,ansY1);
        ans.add(ans1);
        if (delta!=0){
            Position ans2 = new Position(ansX2,ansY2);
            ans.add(ans2);
        }
        if (ans.size()>1){
//            for (int i = 0; i < ans.size(); i++) {
//                double d = Math.sqrt(Math.pow(ans.get(i).getX() - positionC.getX(), 2) + Math.pow(ans.get(i).getY() - positionC.getY(), 2));
//                if (Math.abs(d-distanceC)>2){
//                    ans.remove(i);
//                    if (ans.size()==1){
//                        break;
//                    }
//                }
//            }
            double d1 = Math.sqrt(Math.pow(ans.get(0).getX() - positionC.getX(), 2) + Math.pow(ans.get(0).getY() - positionC.getY(), 2));
            double d2 = Math.sqrt(Math.pow(ans.get(1).getX() - positionC.getX(), 2) + Math.pow(ans.get(1).getY() - positionC.getY(), 2));
            if(d1>d2){
                return ans.get(1).addDefault();
            }
        }

        return ans.get(0).addDefault();
    }
    public static double[] rssiToDistance(int[] rssi){
        double []ans = new double[3];
        for (int i = 0; i < rssi.length; i++) {
            ans[i] = Math.pow(10,(rssi[i]-rssiA)/10.0/n);
            System.out.println(ans[i]);
        }
        return ans;
    }
}
