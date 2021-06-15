<template>
  <v-container>
    <v-container>
      <a @click="redirectBackwards" class="lastPage">&lt;&lt;</a>
      <h1 class="h1Style text-center display-3 font-weight-medium">
        {{ eventObj.title }}
      </h1>
    </v-container>

    <v-card
      center
      class="ml-10 rounded-xl md-layout md-gutter md-alignment-center"
      :style="styleObject"
    >
      <v-row>
        <v-col v-if="venueLoaded">
          {{ eventObj.eventStart }}
          {{ venueObj.name }}
          {{ venueObj.adress }}
        </v-col>
        <v-col>{{ eventObj.price }}</v-col>
      </v-row>
      <v-row>
        <v-col>{{ eventObj.info }}</v-col>
        <v-col>{{ eventObj.pic }}</v-col>
      </v-row>
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
      venueObj: {
        name: null,
        adress: null,
        website:null
      },

      venueLoaded:false

    }
  },
  created() {
    const eventId = this.$route.params.id
    axios.get(`/events/${eventId}`).then((response) => {
      console.log(response)
      this.eventObj.id = response.data.id
      this.eventObj.title = response.data.title
      this.eventObj.artists = response.data.artists
      this.eventObj.venueId = response.data.venueId
      this.eventObj.eventStart = response.data.eventStart
      this.eventObj.eventEnd = response.data.eventEnd
      this.eventObj.info = response.data.info
      this.eventObj.modifiedAt = response.data.modifiedAt
      this.eventObj.pic = response.data.pic
      this.eventObj.price = response.data.price

    })
  },
  mounted(){
    axios.get(`/venues/${this.eventObj.venueId}`).then((response) => {
      this.venueObj.name = response.data.name
      this.venueObj.adress = response.data.adress
      this.venueObj.website = response.data.website
      this.venueLoaded = true
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
.main-card {
  border: '2px solid #000000';
}
.bg-color-1 {
  background-color: grey;
}
.bg-color-2 {
  background-color: lightblue;
}
</style>