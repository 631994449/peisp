package cn.edu.hziee.peisp.utils;

import cn.edu.hziee.peisp.entity.Position;

import java.util.ArrayList;
import java.util.List;

public class PositionCounter {
    private static Position positionA = new Position(13339389.8514,3541340.7679);
    private static Position positionB = new Position(13339392.8493,3541344.8252);
    private static Position positionC = new Position(13339395.9126,3541340.7668);
    private static List<Position> ans= new ArrayList<>();
    public static Position count(double distanceA,double distanceB,double distanceC){
        //参考算法  https://blog.csdn.net/qq_17616169/article/details/72833869
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
            for (int i = 0; i < ans.size(); i++) {
                double d = Math.sqrt(Math.pow(ans.get(i).getX() - positionC.getX(), 2) + Math.pow(ans.get(i).getY() - positionC.getY(), 2));
                if (Math.abs(d-distanceC)>2){
                    ans.remove(i);
                    if (ans.size()==1){
                        break;
                    }
                }
            }
        }
        return ans.get(0);
    }
}