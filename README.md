# 项目名称
简要说明

## 快速开始
### 安装依赖
```sh
# 安装Jumbo
bash -c "$( curl http://jumbo.baidu.com/install_jumbo.sh )"; source ~/.bashrc

# 安装java8
jumbo install sun-java8

# 安装maven
jumbo install apache-maven

# 安装redis
jumbo install redis

# 启用java8环境
source ${JUMBO_ROOT}/opt/sun-java8/sun-java8.sh

# 启用redis
nohup redis-server ~/.jumbo/etc/redis.conf --port 8331 > /dev/null 2>/dev/null &

# 使用anymock_opensource.sql中的语句创建数据库
```

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
贡献patch流程、质量要求

## 讨论
百度Hi讨论群：XXXX
