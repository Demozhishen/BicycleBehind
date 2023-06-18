package com.springcloud.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@TableName("fence")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Fence {

    @TableId(value = "fence_id",type = IdType.AUTO)
    private int fenceId;
    private String fenceName;
    private double point1X;
    private double point1Y;
    private double point2X;
    private double point2Y;
    private double point3X;
    private double point3Y;
    private double point4X;
    private double point4Y;
    private int vehicleCount;


}
