# annotation-wrapper

#### 介绍

annotation-wrapper通过注解生成mybatis-plus的查询wrapper

联系方式：

微信：wxid_hjbdtkuuwfp22

#### mybatis-plus兼容版本

基于MyBatis-Plus3.5开发 理论上mybatis-plus3.0及以上版本都兼容可自行替换。

#### 安装教程
    maven打包code模块上传到本地或私有仓库后直接引用
      Maven：  
        <dependency>
             <groupId>com.xiyu.wrapper</groupId>
            <artifactId>code</artifactId>
            <version>最新版本</version>
            <!--如已引用mybatis-plus可排除annotation-wrapper集成的mybatis-plus
            <exclusions>
                <exclusion>
                    <groupId>com.baomidou</groupId>
                    <artifactId>mybatis-plus</artifactId>
                </exclusion>
            </exclusions>
        </dependency>-->

#### 使用说明

使用演示：参考https://gitee.com/anZhi_RuoSu/annotation-wrapper.git下的example模块

使用方式1：调用WrapperGenerator.generateWrapper();

使用方式2：实现AnnotationWrapper接口，调用this.generateWrapper();

#### 参与贡献

1. Fork 本仓库
2. 新建 Feat_xxx 分支
3. 提交代码
4. 新建 Pull Request

