<template>
  <v-container>
    <v-container class="head_container">
      <a @click="redirectBackwards" class="lastPage">&lt;&lt;</a>
      <h1 class="h1Style text-center display-3 font-weight-medium">
        {{ venueObj.name }}
      </h1>
      <p></p>
    </v-container>

    <v-card
      center
      class="ml-10 rounded-xl md-layout md-gutter md-alignment-center"
      :style="styleObject"
    >
      <v-list-item three-line>
        <v-list-item-content>
          <div class="text-overline mb-4">{{ venueObj.email }}</div>
          <v-list-item-title class="text-h5 mb-1">
            {{ venueObj.name }}
          </v-list-item-title>
        </v-list-item-content>
      </v-list-item>
      <template>
        <v-container>
          <v-row>
            <!--big screens-->
            <v-col cols="12" md="8">
              <v-card class="pa-2" flat tile> {{ venueObj.info }} </v-card>
            </v-col>
            <v-col align="center" class="d-none d-md-block" cols="6" md="4">
              <v-card class="pa-2" flat tile>
                <MapsView :adress="venueObj.adress" />
              </v-card>
            </v-col>
            <!--small screens-->
            <v-col class="d-lg-none" cols="12" md="4">
              <v-card class="pa-2" flat tile>
                <MapsView :adress="venueObj.adress" />
              </v-card>
            </v-col>
          </v-row>
        </v-container>
      </template>
    </v-card>
  </v-container>
</template>

<script>
import axios from 'axios'
import MapsView from '../components/features/MapsView.vue'

export default {
  data() {
    return {
      styleObject: { border: '2px solid #000000' },
      venueObj: {
        name: 'SampleName',
        email: 'SampleMail',
        info: 'SampleInfo',
        adress: {
          street: 'Prenzlauer Allee',
          number: '22',
          plz: '10407',
          city: 'Berlin',
        },
      },
    }
  },
  mounted() {
    const param = this.$route.params.id
    axios.get(`/venues/${param}`).then((response) => {
      this.venueObj.name = response.data.name
      this.venueObj.email = response.data.email
      // this.venueObj.info = response.data.info
      this.venueObj.info = this.getLorem()
      this.venueObj.adress = response.data.address
    })
  },
  methods: {
    redirectBackwards() {
      history.back()
    },
    getLorem() {
      return 'Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet. Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet. Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet.'
    },
  },
  components: {
    MapsView,
  },
}
</script>

<style>
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

.childElem {
  margin: 2vw;
}
</style>
