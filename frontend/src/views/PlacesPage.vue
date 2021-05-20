<template>
  <h1><v-btn @click="testMethod">Test</v-btn></h1>
</template>

<script>
import axios from 'axios'

export default {
  methods: {
    testMethod() {
      console.log('Test clicked')
      axios.get('/venues', {}).then((err, response) => {
        if (err) {
          console.log(err)
        } else if (!response) {
          console.log('SchadeMarmelade')
        } else {
          console.log(response)
        }
      })
    },
  },
}
</script>

<style></style>
