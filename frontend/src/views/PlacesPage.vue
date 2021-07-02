<template>
  <v-container my-5>
    <h1 class="h1Style text-center display-3 font-weight-medium">PLACES</h1>

    <v-layout row wrap>
      <v-flex
        xs12
        sm6
        md6
        lg3
        v-for="venue in venues"
        :key="venue.name"
        d-flex
        justify-space-around
      >
        <div>
          <v-card
            center
            class="rounded-xl text-center ma-7 myLogoCard"
            :style="styleObject"
          >
            <v-responsive class="myResponsivePictureContainer">
              <template v-if="venue.pic == null || venue.pic == ''">
                <div class="noPicture"></div>
              </template>
              <template v-else>
                <v-img
                  fill
                  class="myLogo"
                  :src="picDataUrl(venue.pic)"
                  alt=""
                />
              </template>
            </v-responsive>
            <v-card-actions>
              <v-btn
                @click="redirectToPlaceDetail(venue.id)"
                width="100%"
                elevation="0"
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
import { requestProvider } from '../util/requestProvider'

export default {
  data() {
    return {
      styleObject: { border: '2px solid #cafb03' },
      venues: null,
    }
  },
  mounted() {
    requestProvider.getVenues().then((response) => {
      this.venues = response.data
    })
  },
  methods: {
    picDataUrl(pic) {
      return 'data:image/png;base64, ' + pic
    },
    redirectToPlaceDetail(placeId) {
      this.$router.push({
        path: `/places/${placeId}`,
      })
    },
  },
}
</script>

<style scoped>
.myLogoCard {
  width: 250px;
  height: 250px;
  padding: 2%;
  margin-bottom: 8%;
}
.noPicture {
  height: 100%;
  width: 100%;
  background: black;
  line-height: 100%;
}
.myLogo {
  height: 100%;
}
.myResponsivePictureContainer {
  height: 80%;
  width: 100%;
}
</style>
