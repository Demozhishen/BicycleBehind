package com.springcloud.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@TableName("vehicles")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Vehicle {

    @TableId(value = "id",type = IdType.AUTO)
    private int id;
    private double xCoordinate;
    private double yCoordinate;
    private String status;
    private String bluetoothId;
}
