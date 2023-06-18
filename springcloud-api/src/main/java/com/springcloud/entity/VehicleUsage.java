package com.springcloud.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

@TableName("vehicleusage")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VehicleUsage {

    @TableId(value = "record_id",type = IdType.AUTO)
    private int recordId;
    private int vehicleId;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Timestamp startTime;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Timestamp  endTime;
    private float distance;
    private int fenceId;
    private float startX;
    private float startY;
    private float endX;
    private float endY;
}
