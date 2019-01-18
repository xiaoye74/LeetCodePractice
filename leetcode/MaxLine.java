package com.qianxiang.leetcode;
/*
Given n points on a 2D plane, find the maximum number of points that lie on the same straight line.
解题思路：
每次选取两个点形成一条直线，然后遍历整个点集来穷举在这条线上的点。最后选取最多的共线的点数返回。

注意要考虑如下情况：1、点组里所有的点都是同一个。
2、存在其中两个点一样的情况，这样会使dx=dy=0，从而使else中的判断条件无效。
因为我们每次都会遍历整个点集来寻找共线的点，所以对于dx,dy中只要有一个不为0的case，else语句中的
判断方法都足以处理。(即每次只要确定和两个相同点的其中一个共线后，另外一个被遍历到后也必然共线)
所以我们只需使用简单使if条件成立后找出所有相同点即可处理以上两个case。
 */
class Point {
    int x;
    int y;
    Point() { x = 0; y = 0; }
    Point(int a, int b) { x = a; y = b; }
}
public class MaxLine {
    public int maxPoints(Point[] points) {
        if(points == null || points.length<3)
            return points.length;
        int res =0;
        for(int i=1;i<points.length;i++){
            int count = 0;
            long a = points[i].x;
            long b = points[i].y;
            long dx = a - points[i-1].x;
            long dy = b - points[i-1].y;
            if(dx==0 && dy==0){
                for(int j=0;j<points.length;j++){
                    if(points[j].x==a && points[j].y==b){
                        count++;
                    }
                }
            }else{
                for(int j=0;j<points.length;j++){
                    if((points[j].x-a)*dy==(points[j].y-b)*dx){
                        count++;
                    }
                }
            }
            res = Math.max(res,count);
        }
        return res;
    }
    public boolean isLine(Point p1, Point p2, Point p3)
    {
        if((p3.y - p1.y)/(p3.x-p1.x) == (p2.y - p1.y)/(p2.x-p1.x))
            return true;
        return false;
    }
}
