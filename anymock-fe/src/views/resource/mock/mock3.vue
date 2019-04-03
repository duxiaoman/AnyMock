<template>
  <div>
    <h2>案例3 - 同步HTTP请求，预设响应内容</h2>
    <p><strong>场景</strong>：</p>
    <p>同步HTTP接口，响应内容包含逻辑分支，根据请求参数不同返回不同的内容。如果不想编写响应脚本，可以通过设置配置模式为Groovy预设分支，配置路由模板。</p>
    <p><strong>配置方式参见，如下url</strong>：</p>
    <p>https://anymockweb.duxiaoman.com/fe/index.html#/interface/update/109</p>
    <p><strong>请求的url</strong>：
    https://anymock.duxiaoman.com/anymock/example/09?username=00</p>
    <p><strong>返回：hello AnyMock</strong></p>
    <p><strong>关注点</strong>：</p>
    <ul>
      <li><p>接口配置：</p>
        <ol>
          <li>配置模式选Groovy预设</li>
          <li>配置模式下扩展出路由解析规则，是一段可执行的Groovy脚本，根据这段脚本计算出路由模板的路由协议码</li>
        </ol>
      </li>
      <li><p>同步响应配置：</p>
        <ol>
          <li>同步响应返回内容不再一段Groovy脚本，而是个路由模板list，每个模板的响应路由码都不一样</li>
          <li>路由解析规则计算所得结果和每个路由模板的路由码进行匹配，获取模板。</li>
          <li>模板响应内容为一段Groovy脚本，print输出的内容即是脚本执行后输出的响应内容</li>
        </ol>
      </li>
    </ul>
  </div>
</template>
