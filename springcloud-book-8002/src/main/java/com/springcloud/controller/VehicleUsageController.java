package com.springcloud.controller;


import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.springcloud.common.Result;
import com.springcloud.entity.VehicleUsage;
import com.springcloud.mapper.VehicleUsageMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.util.List;


@RestController
@CrossOrigin
@RequestMapping("/vehicleUsage")
public class VehicleUsageController {
    @Resource
    VehicleUsageMapper vehicleUsageMapper;



    @PostMapping

    public Result<?> save(@RequestBody VehicleUsage vehicleUsage){
        int insert = vehicleUsageMapper.insert(vehicleUsage);
        return Result.success();
    }






    @GetMapping
    public Result<?> findPage(@RequestParam(defaultValue = "1") Integer pageNum,@RequestParam(defaultValue = "10") Integer pageSize, @RequestParam(defaultValue = "") String search){
        Page<VehicleUsage> vehicleUsagePage = vehicleUsageMapper.selectPage(new Page<>(pageNum, pageSize), Wrappers.<VehicleUsage>lambdaQuery().like(VehicleUsage::getVehicleId, search));
        return Result.success(vehicleUsagePage);
    }




     @PutMapping
    public Result<?> update(@RequestBody VehicleUsage vehicleUsage){
        vehicleUsageMapper.updateById(vehicleUsage);
        return Result.success();
     }


     @DeleteMapping("/{id}")
    public Result<?> delete(@PathVariable int id){
        vehicleUsageMapper.deleteById(id);
        return Result.success();
     }

    @GetMapping("/export")
    public void export(HttpServletResponse response) throws Exception {
        // 从数据库查询出所有的数据
        List<VehicleUsage> list = vehicleUsageMapper.selectList(null);
        // 通过工具类创建writer 写出到磁盘路径
//        ExcelWriter writer = ExcelUtil.getWriter(filesUploadPath + "/用户信息.xlsx");
        // 在内存操作，写出到浏览器
        ExcelWriter writer = ExcelUtil.getWriter(true);
        //自定义标题别名


        // 一次性写出list内的对象到excel，使用默认样式，强制输出标题
        writer.write(list, true);

        // 设置浏览器响应的格式
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
        String fileName = URLEncoder.encode("用户信息", "UTF-8");
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName + ".xlsx");

        ServletOutputStream out = response.getOutputStream();
        writer.flush(out, true);
        out.close();
        writer.close();

    }
}
