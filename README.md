# annotation-wrapper

#### 介绍
annotation-wrapper通过注解生成mybatis-plus的查询wrapper

github: https://github.com/NanyouqiaoM/annotation-wrapper
gitee: https://gitee.com/anZhi_RuoSu/annotation-wrapper.git

#### mybatis-plus兼容版本

基于MyBatis-Plus3.5开发 理论上mybatis-plus3.0及以上版本都兼容可自行替换。

#### 安装教程

先添加repositories节点

    <repositories>
        <repository>
            <id>jitpack.io</id>
            <url>https://jitpack.io</url>
        </repository>
    </repositories>
然后添加如下maven依赖

    <dependency>
        <groupId>com.github.NanyouqiaoM</groupId>
        <artifactId>annotation-wrapper</artifactId>
        <version>最新版本</version>
    </dependency>

#### 使用说明

使用演示：参考annotation-wrapper-example

地址：https://github.com/NanyouqiaoM/annotation-wrapper-example

使用方式1：调用WrapperGenerator.generateWrapper(obj);

使用方式2：实现AnnotationWrapper接口，调用this.generateWrapper();

#### 参与贡献

1. Fork 本仓库
2. 新建 Feat_xxx 分支
3. 提交代码
4. 新建 Pull Request

