<template>
  <div>
    <h4>{{ title }}</h4>
    <Test />
  </div>
</template>

<script>
import Test from './components/test.vue'

export default {
  name: 'App',

  components: {
    Test,
  },

  data: () => ({
    title: 'Just a test',
  }),
}
</script>
