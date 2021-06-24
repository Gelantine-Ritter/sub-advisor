<template>
  <v-container my-5>
    <h1 class="h1Style text-center display-3 font-weight-medium">PLACES</h1>

    <v-layout row wrap>
      <v-flex xs12 sm6 md4 lg3 v-for="venue in venues" :key="venue.name">
        <div>
          <v-card
            center
            class="rounded-xl text-center ma-7"
            min-width="250"
            :style="styleObject"
          >
            <v-responsive>
              <v-img
                contain
                class="ma-5 border border-dark"
                height="200"
                src="../../public/venue2.png"
                alt=""
              />
            </v-responsive>
            <v-card-actions>
              <v-btn
                @click="redirectToPlaceDetail(venue.id)"
                width="100%"
                flat
                color="rounded-pill black white--text"
                >{{ venue.name }}
              </v-btn>
            </v-card-actions>
          </v-card>
        </div>
      </v-flex>
    </v-layout>
  </v-container>
</template>
../util/RequestProvider
<script>
import {requestProvider} from '../util/requestProvider'

export default {
  data() {
    return {
      styleObject: { border: '2px solid #cafb03' },
      venues: null,
    }
  },
  mounted() {
    requestProvider.getVenues()
      .then((response) => {
        this.venues = response.data
      })
  },
  methods: {
    redirectToPlaceDetail(placeId) {
      this.$router.push({
        path: `/places/${placeId}`,
      })
    },
  },
}
</script>

<style scoped></style>
