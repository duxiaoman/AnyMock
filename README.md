# AnyMock


## 一、产品介绍

AnyMock是一个通用接口Mock平台，提供Mock配置和模拟响应的服务。

起源于银行通道接入在所依赖服务不稳定或不可用时模拟系统返回交互报文，保障项目稳定开展和线下环境的稳定业务回路。开源v1.0覆盖了http协议、xml/k-v/json报文格式、同/异步等不同类型的通讯方式，具有可扩展性，可通过脚本语言动态组装响应报文。


使用AnyMock后，服务调用变成了如下的交互方式：

<img src="https://github.com/duxiaoman/AnyMock/blob/master/img/mock.png" width="400" hegiht="400"/>


**AnyMock具有以下特征**
- **解决外部依赖:** 在外部系统不稳定或者不可用的情况下，使用AnyMock来替代，可以保证项目如期推进。
- **构造异常测试:** 在交互复杂的线下测试环境，使用AnyMock替代真实数据，可以以较小的代码构造异常的数据、覆盖更多的业务分支和异常场景。
- **支持性能测试:** 在线上环境进行压测，使用AnyMock替代依赖下游服务，可以屏蔽对下游服务的影响，压出被压服务自己的性能数据。
- **稳定自动化测试:** 在依赖较多的线下自动化测试环境，使用AnyMock替代下游测试服务，可以最大保障自动化case运行的环境稳定性。
- **降低测试成本:** 在外部系统调用代价较高时，使用AnyMock解耦与外部系统的依赖，降低真实调用的测试成本。


AnyMock平台主要包含两个部分：
- 接口管理后台即AnymockWeb系统，主要提供Mock配置和管理。展示已有节点空间、接口的信息，并对这些接口的详细内容进行查看、修改或者新增接口。
- 服务核心系统AnymockCore，接收上游请求，模拟响应的服务。承担所有核心流程处理、及对应的数据输出。


AnyMock的系统架构图


<img src="https://github.com/duxiaoman/AnyMock/blob/master/img/image2019-3-20_10-34-0%20(1).png" width="600" hegiht="600" />

## 二、文档

有关完整文档，示例，博客文章，操作详细信息和其他信息，请参阅[wiki](https://github.com/duxiaoman/AnyMock/wiki)



## 三、AnyMock的开源生态



<img src="https://github.com/duxiaoman/AnyMock/blob/master/img/image2019-3-12_10-25-49.png" width="600" hegiht="600"/>



## 四、快速开始

### 1. 如何初始化数据库
执行 [anymock_opensource.sql](https://github.com/duxiaoman/AnyMock/blob/master/anymock_opensource.sql)

### 2. 如何构建
```sh
# 更新代码库
git pull

# 编译
mvn clean package
```

### 3. 如何安装
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

### 4. 运行AnyMockCore
```sh
# 切换到编译产出目录
cd output/core

# 后台运行，其中${env}需替换为配置文件环境名
nohup java -jar anymock-core-runner-1.0-SNAPSHOT.jar --spring.profiles.active=${env} >/dev/null 2>/dev/null &
```

### 5. 运行AnyMockWeb
```sh
# 切换到编译产出目录
cd output/web

# 1. 下载前端代码库（anymock-fe）！！！，并将anmock-web-config-${env}.yml中的fe.path修改为anymock-fe的dist目录地址
# 2. 后台运行，其中${env}需替换为配置文件环境名
nohup java -jar anymock-web-runner-1.0-SNAPSHOT.jar --spring.profiles.active=${env} >/dev/null 2>/dev/null &
```

### 6. 接下来就开始使用吧!

**管理后台** ：访问 http://${ip}:{web port}/fe/index.html 

Mock配置详细介绍和使用文档可参考：[AnyMockWeb管理后台
](https://github.com/duxiaoman/AnyMock/wiki/AnymockWeb%E7%AE%A1%E7%90%86%E5%90%8E%E5%8F%B0)



**核心服务** ：访问 http://${ip}:{core port}/${接口url}

从服务调用方发起业务流程，请求到AnyMock核心服务而非真实要调用的服务提供方的系统.



### 五、问题和反馈
有关错误报告，问题和讨论，请提交[GitHub问题](https://github.com/duxiaoman/AnyMock/issues)
<br>
<br>
联系我们：
<br><img src='https://github.com/duxiaoman/AnyMock/blob/master/img/wechat.jpeg' width='200'>



