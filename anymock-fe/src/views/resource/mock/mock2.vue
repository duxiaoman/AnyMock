<template>
  <div>
    <h1>Groovy案例</h1>
    <h2>案例2 - 同步HTTP请求，自定义脚本响应</h2>
    <p><strong>场景</strong>：</p>
    <p>同步HTTP接口，我想不同的场景返回不一样内容。我应该怎么配置，通过设置配置模式为Groovy，在响应内容里编写自定义脚本。</p>
    <p><strong>配置方式参见，如下url</strong>：</p>
    <p>https://anymockweb.duxiaoman.com/fe/index.html#/interface/update/99</p>
    <p><strong>请求的url</strong>：
    https://anymock.duxiaoman.com/anymock/example/08?username=AnyMock</p>
    <p><strong>返回：hello AnyMock</strong></p>
    <p><strong>关注点</strong>：</p>
    <ul>
      <li><p>接口配置：
      （1）配置模式选Groovy</p>
      </li>
      <li><p>同步响应配置：
      （1）同步响应返回内容不再是个固定内容，而是一段可执行的Groovy脚本。
      （2）print输出的内容即是脚本执行后输出的响应内容。</p>
      </li>
    </ul>
  </div>
</template>
