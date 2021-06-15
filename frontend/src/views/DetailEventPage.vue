<template>
  <v-container class="head_container">
    <v-card
      class="ml-10 rounded-xl md-layout md-gutter md-alignment-center main_card"
    >
      <a @click="redirectBackwards" class="lastPage">&lt;&lt;</a>
      <h1 class="h1Style text-center display-3 font-weight-medium">
        {{ eventObj.title }}
      </h1>
      <p>{{eventObj.info}}</p>
      <p>{{eventObj.eventStart}}</p>
      <p>{{eventObj.eventEnd}}</p>
      <h3>Preis</h3>
      <p>{{eventObj.price}}</p>
      <p>{{eventObj.pic}}</p>
      <h3>Künstler</h3>
      <ul id="my-list">
        <li v-for="artist in eventObj.artists" v-bind:key="artist">
          {{artist}}
        </li>
      </ul>
    </v-card>
  </v-container>
</template>

<script>
import axios from 'axios'
export default {
  data() {
    return {
      eventObj: {
        id: null,
        title: null,
        artists: null,
        eventStart: null,
        eventEnd: null,
        info: null,
        modifiedAt: null,
        pic: null,
        price: null,
      },
    }
  },
  created() {
    const eventId = this.$route.params.id
    axios.get(`/events/${eventId}`).then((response) => {
      console.log(response)
      this.eventObj.id = response.data.id
      this.eventObj.title = response.data.title
      this.eventObj.artists = response.data.artists
      this.eventObj.eventStart = response.data.eventStart
      this.eventObj.eventEnd = response.data.eventEnd
      this.eventObj.info = response.data.info
      this.eventObj.modifiedAt  = response.data.modifiedAt
      this.eventObj.pic  = response.data.pic
      this.eventObj.price  = response.data.price
      
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