<template>
  <div>
    <h1>功能测试</h1>
    <h2>方案介绍：</h2>
    <ul>
      <li>解决外部依赖: 在外部系统不稳定或者不可用的情况下，使用AnyMock来替代，可以保证项目如期推进。</li>
      <li>降低测试成本: 在外部系统调用代价较高时，使用AnyMock解耦与外部系统的依赖，降低真实调用的测试成本。</li>
    </ul>
    <p>AnyMock 开源v1.0覆盖了http协议、xml/k-v/json报文格式、同/异步等不同类型的通讯方式，支持静态文本返回，同时具有可扩展性，可通过脚本语言动态组装响应报文，也支持脚本预设路由返回。</p>
    <h2>案例分享</h2>
    <p>AnyMock平台起源于银行通道接入，在外部银行环境不稳定或不可用时模拟银行系统返回交互报文，保障项目稳定开展和自身线下环境的稳定业务回路。接下来我们就用金融支付场景，来介绍如何使用AnyMock应用于功能测试。</p>
    <p>平台A在线售卖商品，用户下单后完成支付，平台A发货。第三方支付和银行也不提供线下环境，如果在线上真金验证整个购买流程，成本太高。可以通过AnyMock解决以上功能测试困难。以支付方式使用度小满支付为例。</p>
    <h3>场景1  -  支付受理成功</h3>
    <p>所有支付请求过来，都返回相同的受理成功结果</p>
    <p><strong>配置方式参见url</strong>：https://anymockweb.duxiaoman.com/fe/index.html#/interface/update/98</p>
    <p><strong>请求方式</strong>：http://anymock.duxiaoman.com/anymock/example/01</p>
    <p><strong>结果</strong>：</p>
    <img src="./../../../assets/doc_img/image2019-3-28_19-37-7.png" width="500">

    <h3>场景2 - 请求中订单号不同，返回不同支付受理结果</h3>
    <p><strong>配置方式参见url</strong>：https://anymockweb.duxiaoman.com/fe/index.html#/interface/update/99</p>
    <p><strong>关注点</strong>：</p>
    <img src="./../../../assets/doc_img/image2019-3-28_19-41-29.png" width="500" >

    <p><strong>请求方式</strong>：https://anymock.duxiaoman.com/anymock/example/02?orderno=12</p>
    <p><strong>结果</strong>：</p>
    <img src="./../../../assets/doc_img/image2019-3-28_19-38-17.png" width="500">

    <h3>场景3 - 支付确认成功</h3>
    <p>支付确认是个异步过程，所以将接口配成异步请求，确认成功作为异步响应返回。</p>
    <p><strong>配置方式参见url</strong>：https://anymockweb.duxiaoman.com/fe/index.html#/interface/update/100</p>
    <p><strong>关注点</strong>：</p>
    <img src="./../../../assets/doc_img/image2019-3-28_19-44-32.png" width="500">
    <br>
    <img src="./../../../assets/doc_img/image2019-3-27_21-26-45.png" width="500">

    <p><strong>请求方式</strong>：curl -X POST https://anymock.duxiaoman.com/anymock/example/03</p>
    <h3>场景4 - 不同订单号返回不同支付确认结果</h3>
    <p>配置方式参见url：</p>
    <h3>场景5 - 支付结果查询，根据入参订单号返回不同结果</h3>
    <p><strong>配置方式参见url</strong>：https://anymockweb.duxiaoman.com/fe/index.html#/interface/update/98</p>
    <p><strong>请求方式</strong>：https://anymock.duxiaoman.com/example/05?orderno=123</p>
    <p><strong>结果</strong>：</p>
    <img src="./../../../assets/doc_img/image2019-3-28_23-28-26.png" width="500">

  </div>
</template>
<style>
 img{
   padding-bottom: 20px;
 }
</style>

