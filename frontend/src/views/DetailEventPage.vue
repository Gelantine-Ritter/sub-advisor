<template>
  <v-container class="head_container">
    <v-card
      class="ml-10 rounded-xl md-layout md-gutter md-alignment-center main_card"
    >
      <a @click="redirectBackwards" class="lastPage">&lt;&lt;</a>
      <h1 class="h1Style text-center display-3 font-weight-medium">
        {{ eventObj.title }}
      </h1>
    </v-card>
  </v-container>
</template>

<script>
import axios from 'axios'
export default {
  data() {
    return {
      eventObj: {
        title: null,
      },
    }
  },
  created() {
    const eventId = this.$route.params.id
    axios.get(`/events/${eventId}`).then((response) => {
      console.log(response)
      this.eventObj.title = response.data.title
      
    })
  },
  methods: {
    redirectBackwards() {
      history.back()
    },
  },
}
</script>

<style>
.main_card {
  border: '2px solid #000000';
}
</style>