<template>
  <div>

    <h1 class="h1Style text-center display-3 font-weight-medium" :class="[$vuetify.breakpoint.mdAndUp ? 'display-4' : 'display-2']">
      {{ eventObj.title }}
    </h1>

    <v-card
      center
      class="ml-10 mr-10 rounded-xl md-layout md-gutter md-alignment-center"
      :style="styleObject"
    >
      <v-container center>
        <v-row center>
          <!-- venueName, adress, ... -->
          <v-col cols="12" xs="12" sm="8" md="8" lg="8" xl="8">
              <div>
                {{venueObj.pic}}
              </div>
              <div>
              <h2>{{ venueObj.name }}</h2>
              <div>
                <h5>
                  {{ venueObj.adress.street }}
                  {{ venueObj.adress.number }},
                  {{ venueObj.adress.plz }}
                  {{ venueObj.adress.city }}
                </h5>
              </div>
              <v-div v-if="eventObj.eventStart">
                starts around: {{ eventObj.eventStart }}
              </v-div>
              </div>
          </v-col>
          <!-- tags, price, ... -->
          <v-col cols="12" xs="12" sm="4" md="4" lg="4" xl="4">
            Price: {{ eventObj.price }} Euro
          </v-col>
        </v-row>

        <v-row center>
          <!-- description, venue website, ...-->
          <v-col cols="12" xs="12" sm="8" md="8" lg="8" xl="8">
            <div>
              <!-- {{ eventObj.info }} -->
              {{ this.getLorem() }}
            </div>
            <div></div>
            {{ venueObj.website }}
          </v-col>
          <!-- image -->
          <v-col v-if="eventObj.pic" cols="12" xs="12" sm="4" md="4" lg="4" xl="4" >
            {{ eventObj.pic }}
          </v-col>
        </v-row>
      </v-container>
    </v-card>
  </div>
</template>
      
<!--

v-if="venueLoaded"
          <h2>{{ venueObj.name }}</h2>


  -->



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
    getLorem(){
      return "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet. Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et e"
    }
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