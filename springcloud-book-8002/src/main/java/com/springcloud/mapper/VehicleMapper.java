package com.springcloud.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.springcloud.entity.Admin;
import com.springcloud.entity.Vehicle;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;


@Repository
public interface VehicleMapper extends BaseMapper<Vehicle> {

    @Update("update vehicles set status = #{status} where id=#{id}")
    public void updateStatusById(Vehicle vehicle);

}
