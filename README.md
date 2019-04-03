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



AnyMock的主要特征---系统架构图


<img src="https://github.com/duxiaoman/AnyMock/blob/master/img/image2019-3-20_10-34-0%20(1).png" width="600" hegiht="600" />


AnyMock的开源生态



<img src="https://github.com/duxiaoman/AnyMock/blob/master/img/image2019-3-12_10-25-49.png" width="600" hegiht="600"/>



AnyMock平台主要包含两个部分：
- 接口管理后台即AnymockWeb系统，主要提供Mock配置和管理。展示已有节点空间、接口的信息，并对这些接口的详细内容进行查看、修改或者新增接口。
- 服务核心系统AnymockCore，接收上游请求，模拟响应的服务。承担所有核心流程处理、及对应的数据输出。。

## 二、快速开始


