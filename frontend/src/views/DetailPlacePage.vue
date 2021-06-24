<template>
  <div v-if="venueObj != null">
    <v-container my-5>
    <h1
      class="h1Style text-center display-3 font-weight-medium"
      :class="[$vuetify.breakpoint.mdAndUp ? 'display-4' : 'display-2']"
    >
      {{ venueObj.name }}
    </h1>

    <v-card
      center
      class="ml-10 mr-10 rounded-xl md-layout md-gutter md-alignment-center"
      :style="styleObject"
    >
      <v-container>
        <v-row>
          <!-- header info -->
          <v-col xs="12" sm="4" md="4" lg="4" xl="3">
            <div>
              {{ venueObj.website }}
            </div>
            <div>
              {{ venueObj.address.street }}
              {{ venueObj.address.number }},
              {{ venueObj.address.plz }}
              {{ venueObj.address.city }}
            </div>
          </v-col>
          <v-col xs="12" sm="4" md="4" lg="4" xl="3"> </v-col>
          <v-col xs="12" sm="4" md="4" lg="4" xl="3" class="pls_center_it">
            <MapsView :adress="venueObj.address" />
          </v-col>
        </v-row>

        <v-row>
          <!-- description -->
          <v-col xs="12" sm="6" md="8" lg="8" xl="8">
            {{ venueObj.info }}
          </v-col>
          <!-- venue img, map button -->
          <v-col xs="12" sm="6" md="4" lg="4" xl="4">
            <v-row class="ml-10 mr-10 pls_center_it">
              <v-avatar size="150" v-if="venueObj.pic !== null">
                <PictureView :picData="venueObj.pic" />
              </v-avatar>
            </v-row>
            <v-row class="ml-10 mr-10 mt-5"> </v-row>
          </v-col>
        </v-row>
      </v-container>
      <EventsList class="mt-5" :venueId="this.$route.params.id" />
    </v-card>
    </v-container>
  </div>
</template>

<script>
import MapsView from '../components/features/MapsView.vue'
import PictureView from '../components/features/PictureView.vue'
import EventsList from '../components/events/EventsListFor1Venue.vue'
import { requestProvider } from '../util/requestProvider'

export default {
  data() {
    return {
      styleObject: { border: '2px solid #000000' },
      fetchedAdressData: false,
      venueObj: null,
    }
  },
  mounted() {
    const venueId = this.$route.params.id
    requestProvider.getVenue(venueId)
      .then((response) => {
        this.venueObj = response.data
      })
  },
  methods: {
    redirectBackwards() {
      history.back()
    },
    picDataUrl() {
      return 'data:image/png;base64, ' + this.eventObj.pic
    },
  },
  components: {
    MapsView,
    EventsList,
    PictureView,
  },
}
</script>

<style>
.head_container {
  display: flex;
  justify-content: space-between;
  align-items: center;
}
.childElem {
  margin: 2vw;
}
.pls_center_it {
  display: flex;
  align-items: center;
  justify-content: center;
}
</style>
