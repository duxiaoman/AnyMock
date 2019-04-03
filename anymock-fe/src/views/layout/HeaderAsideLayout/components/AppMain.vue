<template>
  <section class="app-main">
    <transition name="fade" mode="out-in">
      <router-view/>
    </transition>
  </section>
</template>

<script>
export default {
  name: 'AppMain'
}
</script>
<style>
 .app-main{
   overflow:hidden;
   min-height:calc(100vh);
   height: calc(100vh);
   padding-top:74px;
 }
</style>

