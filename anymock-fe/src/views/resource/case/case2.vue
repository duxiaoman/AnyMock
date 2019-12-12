<template>
  <div>
    <h1>异常测试</h1>
    <h2>方案介绍：</h2>
    <p>在交互复杂的线下测试环境，使用AnyMock替代真实数据，可以以较小的代码构造异常的数据、覆盖更多的业务分支和异常场景。仍以金融支付场景为例介绍。</p>
    <h2>案例分享</h2>
    <h3>场景1 -  支付接口返回错误码</h3>
    <p>根据入参订单号不同，返回不同错误码
    <strong>配置方式参见url</strong>：https://anymockweb.duxiaoman.com/fe/index.html#/interface/update/106</p>
    <p><strong>关注点</strong>：</p>
    <img src="./../../../assets/doc_img/image2019-3-28_20-29-40.png" width="500">
    <br>
    <img src="./../../../assets/doc_img/image2019-3-28_20-35-35.png" width="500">

    <ul>
      <li>关注点一：如果输出存在中文乱码，可以配置HTTP返回头 dContent-Type，指定浏览器用UTF-8解码来解决。</li>
      <li>关注点二：Groovy中输出字符串有2种方式：<ul>
        <li>方法一：单引号将待输出字符串框起来，字符串内不可换行</li>
        <li>方法二：使用多行字符串，这种方法输出可带有格式，比如换行</li>
      </ul>
      </li>
    </ul>
    <p><strong>请求方式</strong>：https://anymock.duxiaoman.com/anymock/example/06?orderno=456</p>
    <p><strong>结果</strong>：</p>
    <img src="./../../../assets/doc_img/image2019-3-28_20-39-49.png" width="500">

    <h3>场景2 - 支付接口响应超时</h3>
    <p><strong>配置方式参见url</strong> : https://anymockweb.duxiaoman.com/fe/index.html#/interface/update/107</p>
    <p><strong>关注点</strong>：同步响应设置延迟，比如希望在Mock在收到请求3秒钟之后再响应，就可以配置为3000。以毫秒为单位。</p>
    <img src="./../../../assets/doc_img/image2019-3-28_20-42-55.png" width="500">
    <p><strong>请求方式</strong>：https://anymock.duxiaoman.com/anymock/example/07</p>
  </div>
</template>
