<template>
  <div class="tmp">
    <a-button type="primary" size="small" style="margin-bottom:5px" @click="goBack">
      <a-icon type="left" />Go back
    </a-button>
    <mock1 v-if="item==='mock1'" />
    <mock2 v-if="item==='mock2'" />
    <mock3 v-if="item==='mock3'" />
    <case1 v-if="item==='case1'" />
    <case2 v-if="item==='case2'" />
    <case3 v-if="item==='case3'" />
  </div>
</template>
<script>
import case1 from './case/case1'
import case2 from './case/case2'
import case3 from './case/case3'

import mock1 from './mock/mock1'
import mock2 from './mock/mock2'
import mock3 from './mock/mock3'
export default {
  components: {
    case1,
    case2,
    case3,

    mock1,
    mock2,
    mock3
  },
  data() {
    return {
      item: this.$route.params.item
    }
  },
  methods: {
    goBack() {
      this.$router.back(-1)
    }
  }
}
</script>
<style>
 .tmp{
     border:1px solid #e6e6e6;
     height:calc( 100vh - 120px);
     overflow-y: scroll;
     margin-left: 15%;
     width:1000px;
     padding:10px;
 }
</style>

