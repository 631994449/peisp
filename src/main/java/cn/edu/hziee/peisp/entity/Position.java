package cn.edu.hziee.peisp.entity;

public class Position {
    //private String worker_id
    //测试仅一人，不需要worker_id来区别多个数据
    private double x;
    private double y;

    private static double defaultX = 13339389.8514;
    private static double defaultY = 3541340.7679;

    public Position(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "Position{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
    public Position addDefault(){
        return new Position(this.getX()+defaultX,this.getY()+defaultY);
    }

}
