<template>
  <div v-if="venueObj != null">
    <v-container my-5>
      <h6 class="mb-10 font-weight-bold text--disabled text-center">
        DISCOVER NEW PLACES WITH SUB ADVISOR. HERE IS YOUR RANDOM PIC:
      </h6>

      <h1
        class="h1Style text-center display-3 font-weight-medium"
        :class="[$vuetify.breakpoint.mdAndUp ? 'display-4' : 'display-2']"
      >
        {{ venueObj.name.toUpperCase() }}
      </h1>

      <div center class="rounded-xl mycontainer" fluid>
        <v-container>
          <v-row>
            <!-- header info -->
            <v-col xs="12" sm="8" md="8" lg="8" xl="8">
              <div>
                {{ venueObj.website }}
              </div>
              <div>
                {{ venueObj.address.street }}
                {{ venueObj.address.number }},
                {{ venueObj.address.plz }}
                {{ venueObj.address.city }}
              </div>
              <v-row class="mt-1">
                <MapsView :adress="venueObj.address" />
              </v-row>
            </v-col>
          </v-row>
          <v-row>
            <!-- description -->
            <v-col cols="12" xs="12" sm="8" md="8" lg="8" xl="8">
              {{ venueObj.info }}
            </v-col>
            <!-- venue img, map button -->
            <v-col cols="12" xs="12" sm="4" md="4" lg="4" xl="3">
              <v-row class="pls_center_it">
                <template v-if="venueObj.pic == null || venueObj.pic == ''">
                  <div></div>
                </template>
                <template v-else>
                  <v-card class="pa-2 rounded-xl myLogoCard" flat tile>
                    <v-responsive class="myResponsivePictureContainer">
                      <v-img
                        fill
                        class="myLogo"
                        :src="picDataUrl(venueObj.pic)"
                        alt=""
                      />
                    </v-responsive>
                  </v-card>
                </template>
              </v-row>
              <v-row class="ml-10 mr-10 mt-5"> </v-row>
            </v-col>
          </v-row>
        </v-container>
      </div>
      <EventsList class="mt-5" :venueId="this.venueId" />
    </v-container>
  </div>
</template>

<script>
import MapsView from '../components/features/MapsView.vue'
// import PictureView from '../components/features/PictureView.vue'
import EventsList from '../components/events/EventsListFor1Venue.vue'
import { requestProvider } from '../util/requestProvider'

export default {
  data() {
    return {
      styleObject: { border: '2px solid #000000' },
      fetchedAdressData: false,
      venueObj: null,
      venueId: null,
    }
  },
  mounted() {
    requestProvider.getVenues().then((response) => {
      var rdmVenue =
        response.data[Math.floor(Math.random() * response.data.length)]
      this.venueObj = rdmVenue
      this.venueId = rdmVenue.id
    })
  },
  methods: {
    redirectBackwards() {
      history.back()
    },
    picDataUrl(pic) {
      return 'data:image/png;base64, ' + pic
    },
  },
  components: {
    MapsView,
    EventsList,
    // PictureView,
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
  margin: auto;
}
.myLogoCard {
  width: 150px;
  height: 150px;
  padding: 2%;
  margin-bottom: 8%;
  border: 0;
}
.myResponsivePictureContainer {
  height: 100%;
  width: 100%;
}

.myLogo {
  height: 100%;
}
@media screen and (max-width: 600px) {
  h6.mb-10.font-weight-bold.text--disabled {
    color: white !important;
  }
}
</style>
