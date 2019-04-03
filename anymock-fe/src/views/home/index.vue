<template>
  <div class="home">
    <div class="banner">
      <img src="./../../assets/anymock_opensource_banner.png">
      <div class="banner-content">
        <span class="bannerTitle">便捷MOCK平台</span>
        <span class="subTitle">使用便捷，Mock在线创建和编辑，保存后立即可用</span>
        <span class="subTitle">易学易用，不用写代码就可以使用简单模式配置大部分的Mock</span>
        <span class="subTitle">专业支持，背后有专业团队支持，Mock平台会持续优化升级，也欢迎加入一起共建</span>
        <span class="subTitle">功能强大，简单模式不满足需求，还可以通过groovy脚本灵活扩展Mock功能，一切由你掌握</span>
        <!-- <ul class="subTitle">
          <li>使用便捷，Mock在线创建和编辑，保存后立即可用。</li>
          <li>易学易用，不用写代码就可以使用简单模式配置大部分的Mock。</li>
          <li>功能强大，简单模式不满足需求，还可以通过groovy脚本灵活扩展Mock功能，一切由你掌握。</li>
          <li>专业支持，背后有专业团队支持，Mock平台会持续优化升级，也欢迎加入一起共建。</li>
        </ul> -->
        <button class="submit" @click="experience">立即体验</button>
      </div>
    </div>
    <div class="cases">
      <div class="title">MOCK示例</div>
      <div class="list">
        <div class="item" @click="goTmpPage('mock1')">
          <span class="el-icon-dxm-rfqquantity"/>
          <div class="itemTitle"> 静态文本</div>
          <div class="itemSubTitle">同步HTTP接口，对每个请求返回固定字符串内容</div>
        </div>
        <div class="item" @click="goTmpPage('mock2')">
          <span class="el-icon-dxm-rfq"/>
          <div class="itemTitle">Groovy</div>
          <div class="itemSubTitle">同步HTTP接口，相同的场景返回不一样内容。通过设置配置模式为Groovy，在响应内容里编写自定义脚本</div>
        </div>
        <div class="item" @click="goTmpPage('mock3')">
          <span class="el-icon-dxm-rfqqm"/>
          <div class="itemTitle">Groovy(预设switch/case)</div>
          <div class="itemSubTitle">同步HTTP接口，响应内容包含逻辑分支，根据请求参数不同返回不同的内容</div>
        </div>
      </div>
    </div>
    <div class="cases">
      <div class="title">案例中心</div>
      <div class="list">
        <div class="item" @click="goTmpPage('case1')">
          <span class="el-icon-dxm-fanganchuangyicehua"/>
          <div class="itemTitle"> 功能测试</div>
          <div class="itemSubTitle">解决外部依赖: 在外部系统不稳定或者不可用的情况下，使用AnyMock来替代，可以保证项目如期推进</div>
        </div>
        <div class="item" @click="goTmpPage('case2')">
          <span class="el-icon-dxm-dingzhifangan"/>
          <div class="itemTitle">异常测试</div>
          <div class="itemSubTitle">在交互复杂的线下测试环境，使用AnyMock替代真实数据，可以以较小的代码构造异常的数据、覆盖更多的业务分支和异常场景。仍以金融支付场景为例介绍</div>
        </div>
        <div class="item" @click="goTmpPage('case3')">
          <span class="el-icon-dxm-santaofangan"/>
          <div class="itemTitle">性能测试</div>
          <div class="itemSubTitle">在依赖较多的线下自动化测试环境，使用AnyMock替代下游测试服务，可以最大保障自动化case运行的环境稳定性</div>
        </div>
      </div>
    </div>
    <!-- <div class="qa">
      <div class="title">常见问题2</div>
      <div class="qa-item">
        <span>1.什么是Anymock？</span>
        <p>anymockweb的介绍和描述介绍和描述介绍和描述介绍和描述介绍和描述介绍和描述介绍和描述介绍和描述介绍和描述介绍和描述介绍和描述介绍和描述介绍和描述</p>
      </div>
      <div class="qa-item">
        <span>2.什么是Anymock？</span>
        <p>anymockweb的介绍和描述介绍和描述介绍和描述介绍和描述介绍和描述介绍和描述介绍和描述介绍和描述介绍和描述介绍和描述介绍和描述介绍和描述介绍和描述</p>
      </div>
      <div class="qa-item">
        <span>3.什么是Anymock？</span>
        <p>anymockweb的介绍和描述介绍和描述介绍和描述介绍和描述介绍和描述介绍和描述介绍和描述介绍和描述介绍和描述介绍和描述介绍和描述介绍和描述介绍和描述</p>
      </div>
      <div class="more-qa">
        查看更多问题<i class="el-icon-d-arrow-right el-icon--right"/>
      </div>
    </div> -->
    <div class="footer">
      <img src="./../../assets/favicon.png">
      <a href="https://www.duxiaoman.com/" target="_blank">度小满金融 </a><span>|</span>
      <a href="https://talent.duxiaoman.com/wt/duxiaoman/web/index" target="_blank">诚征英才 </a><span>|</span>
      <a href="https://github.com/duxiaoman/AnyMock" target="_blank">GitHub </a>
    </div>
  </div>
</template>
<script>
export default {
  data() {
    return {
    }
  },
  methods: {
    goTmpPage(item) {
      this.$store.commit('UPDATE_NAR_BAR_ACTIVE', [])
      this.$router.push({ name: 'resource_tmp', params: { item: item }})
    },
    experience() {
      this.$store.commit('UPDATE_NAR_BAR_ACTIVE', ['/interface/list'])
      this.$router.push({ name: 'interface_list' })
    }
  }
}
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
  .home{
    width:100%;
    min-height: calc(100vh - 64px);
    height: calc(100vh - 64px);
    overflow-y:scroll;
    position:relative;
    margin-top:-10px;
    .title{
      font-size: 28px;
      text-align: center;
      padding: 25px 0;
      margin-bottom: 45px;
      font-weight: 200;
      &:after{
        content: "";
        position: relative;
        top: 30px;
        width: 50px;
        height: 5px;
        display: block;
        margin: 0 auto;
        background-color: #c8c8ce;
      }
    }
    .banner{
      width:100%;
      height:500px;
      position: relative;
      margin-top:-10px;
      .banner-content{
        z-index: 2;
        position: absolute;
        width: 100%;
        height:500px;
        display: flex;
        flex-direction: column;
        justify-content: flex-start;
        align-items:center;
        .bannerTitle{
          font-size: 48px;
          color: #f2f3f7;
          padding: 150px 0 20px 20px;
        }
        .subTitle{
          font-size: 16px;
          color: #fff;
          line-height: 30px;
          display: flex;
          flex-direction: column;
          justify-content: center;
          width:100%;
          text-align: center;
        }
        .submit{
          margin-top:50px;
          display: inline-block;
          padding: 7px 40px;
          color: #f2f3f7;
          font-size: 16px;
          border: 1px solid #9b9ba2;
          text-decoration: none;
          background:transparent;
          &:hover{
            color:black;
          }
        }
      }
      img{
        position: absolute;
        top:0;
        left:0;
        height:500px;
        width:100%;
      }
    }
    .cases{
      display: flex;
      flex-direction: column;
      justify-content: flex-start;
      align-items:center;
      width: 100%;
      padding:0 15%;
      .list{
        margin:50px 0;
        flex:1;
        width:100%;
        display: flex;
        flex-direction: row;
        justify-content: space-between;
        align-items: center;
        .item{
          padding:10px;
          width:300px;
          display: inherit;
          flex-direction: column;
          justify-content: flex-start;
          align-items: center;
          text-align: center;
          span{
            font-size: 108px;
            color: #333;
          }
          .itemTitle{
            font-size: 18px;
            color: #333;
            margin-top: 30px;
          }
          .itemSubTitle{
            font-size: 14px;
            color: #9a9a9a;
            margin-top: 25px;
          }
          &:hover{
            border:1px solid #e6e6e6;
          }
        }
      }
    }
    .qa{
      width:100%;
      padding: 0 15% 50px;
      .qa-item{
        padding:10px 10px 0 15px;
        display: flex;
        flex-direction: column;
        justify-content: flex-start;
        align-items: flex-start;
        span{
          font-size: 24px;
          color: #333;
        }
        p{
          font-size: 15px;
          color: #9a9a9a;
          line-height: 24px;
        }
      }
      .more-qa{
        margin-left:15px;
        color: #1890ff;
        font-size: 20px;
      }
    }
    .footer{
      width: 100%;
      height: 48px;
      background-color: #343440;
      display: flex;
      flex-direction: row;
      justify-content: center;
      align-items: center;
      img{
        width:18px;
        height:18px;
        margin-right:10px;
      }
      a{
        text-align: center;
        font-size: 14px;
        color: #999;
      }
      span{
        color: white;
        padding: 0 10px;
      }
    }
  }
</style>
