package com.springcloud.controller;


import com.baomidou.mybatisplus.core.toolkit.Wrappers;

import com.springcloud.common.Result;
import com.springcloud.entity.Admin;
import com.springcloud.mapper.AdminMapper;
import com.springcloud.utils.JwtUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;


@RestController
@CrossOrigin
public class LoginController {

    @Autowired
    private AdminMapper adminMapper;




    @PostMapping("/login")
    public Result<?> findUser(@RequestBody Admin admin){


            Admin admin1 = adminMapper.selectOne(Wrappers.<Admin>lambdaQuery().eq(Admin::getUsername, admin.getUsername()).eq(Admin::getPassword, admin.getPassword()));
            if(admin1==null){
                return Result.error("200","未查询到用户信息");
            }
            String token = JwtUtil.createJWT(UUID.randomUUID().toString(), admin1.getUsername(), null);
            admin1.setToken(token);

            return Result.success(admin1);


    }


}
