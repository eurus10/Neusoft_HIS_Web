<template>
  <div id="app">
    <router-view></router-view>
  </div>
</template>

<script>
export default {
  name: 'App'
}
</script>

<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
}
.control-xl {
  width: 250px;
  margin-bottom: 20px;
  margin-right: 30px;
}
.control-l {
  width: 180px;
  margin-bottom: 20px;
  margin-right: 30px;
}
.control {
  width: 150px;
  margin-bottom: 20px;
  margin-right: 30px;
}
</style>
