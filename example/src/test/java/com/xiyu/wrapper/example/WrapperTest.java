package com.xiyu.wrapper.example;

import com.xiyu.wrapper.code.WrapperGenerator;
import com.xiyu.wrapper.example.entity.User;
import com.xiyu.wrapper.example.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
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
        //实现接口
        List<User> plainUsers = userMapper.selectList(param.lambdaWrapper());
        //WrapperGenerator.generateWrapper()生成
        List<User> plainUsers1 = userMapper.selectList(WrapperGenerator.generateWrapper(param));
        print(plainUsers);
        print(plainUsers1);
    }
    private <T> void print(List<T> list) {
        if (!CollectionUtils.isEmpty(list)) {
            list.forEach(System.out::println);
        }
    }
}