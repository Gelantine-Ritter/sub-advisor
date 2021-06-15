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
      <row container gutter="{12}">
        <column xs="{12}" md="{4}" lg="{3}" v-if="venueLoaded">
          <h2>{{ venueObj.name }}</h2>
          <column xs="{12}" md="{4}" lg="{3}">
            {{ venueObj.adress.street }}
            {{ venueObj.adress.number }}
            {{ venueObj.adress.plz }}
            {{ venueObj.adress.city }}
          </column>
        </column>
        <column xs="{12}" md="{4}" lg="{3}"> {{ eventObj.price }} Euro </column>
      </row>

      <row container gutter="{12}">
        <column xs="{12}" md="{4}" lg="{3}">
          <column xs="{12}" md="{4}" lg="{3}">
            {{ eventObj.info }}
          </column>
          {{ venueObj.website }}
        </column>
        <column xs="{12}" md="{4}" lg="{3}"> {{ eventObj.pic }} </column>
      </row>
    </v-card>
  </v-container>
</template>

<script>
import axios from 'axios'

import Vue from 'vue'
import { Row, Column } from 'vue-grid-responsive'

Vue.component('row', Row)
Vue.component('column', Column)
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
        adress: {
          city: null,
          number: null,
          plz: null,
          street: null,
        },
        website: null,
        email: null,
      },

      venueLoaded: false,

      styleObject: { border: '2px solid #000000' },
    }
  },
  mounted() {
    const eventId = this.$route.params.id
    axios.get(`/events/${eventId}`).then((response) => {
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

      axios.get('/venues/' + this.eventObj.venueId).then((responseVenue) => {
        this.venueObj.name = responseVenue.data.name
        console.log(responseVenue.data.address)
        this.venueObj.adress.city = responseVenue.data.address.city
        this.venueObj.adress.street = responseVenue.data.address.street
        this.venueObj.adress.number = responseVenue.data.address.number
        this.venueObj.adress.plz = responseVenue.data.address.plz
        this.venueObj.website = responseVenue.data.website
        this.venueObj.email = responseVenue.data.email
        this.venueLoaded = true
        console.log('Updated')
      })
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
.head_container {
  display: flex;
  justify-content: space-between;
  align-items: center;
}
.h1Style {
  color: #cafb03;
  text-shadow: 1px 1px 1px black, 1px -1px 1px black, -1px 1px 1px black,
    -1px -1px 1px black;
}
.lastPage {
  text-decoration: none;
  font-size: 1.5em;
  color: black;
}

.lastPage:visited {
  color: black;
  text-decoration: none;
}

.lastPage:hover {
  color: #cafb03;
  text-decoration: none;
}
</style>