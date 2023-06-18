package com.springcloud.service;

import com.springcloud.entity.Fence;
import com.springcloud.entity.Point;
import com.springcloud.entity.Vehicle;
import com.springcloud.mapper.FenceMapper;
import com.springcloud.mapper.VehicleMapper;
import org.apache.poi.POIDocument;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class FenceServiceImpl implements FenceService {

    @Resource
    VehicleMapper vehicleMapper;

    @Resource
    FenceMapper fenceMapper;

    @Override
    public void updateVehicleNumber() {
        List<Fence> fences = fenceMapper.selectList(null);
        List<Vehicle> vehicles = vehicleMapper.selectList(null);
        for(Fence fence:fences){
            int count=0;
            Point[] points=new Point[4];
            points[0]=new Point(fence.getPoint1X(),fence.getPoint1Y());
            points[1]=new Point(fence.getPoint2X(),fence.getPoint2Y());
            points[2]=new Point(fence.getPoint3X(),fence.getPoint3Y());
            points[3]=new Point(fence.getPoint4X(),fence.getPoint4Y());

            for(Vehicle vehicle:vehicles){
                Point target=new Point(vehicle.getXCoordinate(),vehicle.getYCoordinate());
                if (isInsidePlane(points,target)) {
                    count++;
                }
            }
            fence.setVehicleCount(count);
            fenceMapper.updateById(fence);
        }

    }

    public static boolean isInsidePlane(Point[] points, Point target) {
        int count = 0;

        for (int i = 0; i < 4; i++) {
            Point current = points[i];
            Point next = points[(i + 1) % 4];

            if (isIntersecting(current, next, target)) {
                count++;
            }
        }

        // 如果相交数为奇数，则点在四边形平面内
        return count % 2 != 0;
    }

    private static boolean isIntersecting(Point p1, Point p2, Point target) {
        // 检查目标点是否与边p1p2相交
        if ((p1.getY() > target.getY() && p2.getY() <= target.getY()) ||
                (p1.getY() <= target.getY() && p2.getY() > target.getY())) {

            double intersectX = (p2.getX() - p1.getX()) * (target.getY() - p1.getY()) /
                    (p2.getY() - p1.getY()) + p1.getX();

            // 如果目标点的x坐标在射线上，则返回相交
            if (intersectX > target.getX()) {
                return true;
            }
        }

        return false;
    }
}
