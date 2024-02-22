package com.xiyu.wrapper.example;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xiyu.wrapper.example.entity.User;
import com.xiyu.wrapper.example.mapper.UserMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SpringBootTest
class WrapperTest {
    @Resource
    private UserMapper userMapper;
    @Test
    public void tests() {
        System.out.println("----- 普通查询 ------");
        QueryUserParam param=new QueryUserParam();
        //param.setNameKeyword("j");
        List<Long> roleIdList=new ArrayList<>();
        roleIdList.add(2L);
        roleIdList.add(1L);
        param.setRoleIdList(roleIdList);
        List<User> plainUsers = userMapper.selectList(param.lambdaWrapper());
        print(plainUsers);
    }
    private <T> void print(List<T> list) {
        if (!CollectionUtils.isEmpty(list)) {
            list.forEach(System.out::println);
        }
    }
}