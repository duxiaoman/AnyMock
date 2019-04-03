# 项目名称
简要说明

## 快速开始


### 如何构建
```sh
# 更新代码库
git pull

# 编译
mvn clean package
```

### 如何安装
```sh
mkdir -p output
mkdir -p output/core
mkdir -p output/web

# 拷贝编译产出
cp web/runner/target/anymock-web-runner-1.0-SNAPSHOT.jar output/web/
cp config/web/* output/web/
cp core/runner/target/anymock-core-runner-1.0-SNAPSHOT.jar output/core/
cp config/core/* output/core/
```

### 运行AnyMockCore
```sh
# 切换到编译产出目录
cd output/core

# 后台运行，其中${env}需替换为配置文件环境名
nohup java -jar anymock-core-runner-1.0-SNAPSHOT.jar --spring.profiles.active=${env} >/dev/null 2>/dev/null &
```

### 运行AnyMockWeb
```sh
# 切换到编译产出目录
cd output/web

# 1. 下载前端代码库（anymock-fe）！！！，并将anmock-web-config-${env}.yml中的fe.path修改为anymock-fe的dist目录地址
# 2. 后台运行，其中${env}需替换为配置文件环境名
nohup java -jar anymock-web-runner-1.0-SNAPSHOT.jar --spring.profiles.active=${env} >/dev/null 2>/dev/null &
```

接下来访问http://${ip}:8329/fe/index.html开始使用吧。

## 测试
如何执行自动化测试

## 如何贡献
### 代码结构
anymock由maven作为项目管理工具，模块如下
 - common 通用组件
    - base 基础组件
       - check http接口入参检查
       - entity 业务模型实体
       - enums 枚举类型
       - exception 异常
       - logger 日志
       - util 工具类
    - dal 数据访问层
       - config 数据源配置（数据库与redis）
       - dao 数据访问对象
       - entity 由mybatis-generator生成的数据库记录实体
       - mapper
         - auto 由mybatis-generator自动生成
 - core mock核心服务
    - biz 业务层
       - GrovyService groovy脚本执行服务
       - HttpMockService HTTP Mock服务
       - HttpSyncMockService HTTP Mock服务 同步执行逻辑
       - HTTPAsyncMockService HTTP Mock服务 异步执行逻辑
    - web 接口层
       - config 配置
       - controller
         - HttpController handle所有HTTP请求自行分发
         - HttpExceptionHandler HTTP异常处理模块
    - runner 应用运行入口
 - web 提供给前端调用的api接口
    - biz 业务层
       - api 定义部分特定的输入输出结构体
       - SpaceService 空间服务
       - HttpInterfaceService HTTP接口服务
       - HostInfoService 主机信息服务
    - service 接口层
       - config 配置
       - controller
         - SpaceController 空间项目管理
         - HttpInterfaceController 接口管理
         - HostInfoController 系统部署主机信息管理
    - runner 应用运行入口

贡献patch流程、质量要求

## 讨论
百度Hi讨论群：XXXX
