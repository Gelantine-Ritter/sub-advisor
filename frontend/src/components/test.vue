<template>
  <div>
    <ul>
      <li v-for="test in tests" :key="test.index">{{ test }}</li>
    </ul>
  </div>
</template>

<script>
export default {
  data() {
    return {
      tests: ['hallo', 'blubbl', 'okcool'],
    }
  },
}
</script>

<style>
ul {
  color: rgb(255, 0, 34);
}
</style>
