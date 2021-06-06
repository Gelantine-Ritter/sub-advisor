<template>
  <v-container my-5>
    <h1 class="h1Style text-center display-3 font-weight-medium">PLACES</h1>

    <v-layout row wrap>
      <v-flex xs12 sm6 md4 lg3 v-for="venue in venues1" :key="venue.name">
        <div>
          <v-card
            center
            class="rounded-xl text-center ma-7"
            :style="styleObject"
          >
            <v-responsive class="pt-4">
              <v-img
                class="ma-5 border border-dark"
                height="250"
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
                >{{venue.name}}
              </v-btn>
            </v-card-actions>
          </v-card>
        </div>
      </v-flex>
    </v-layout>
  </v-container>
</template>

<script>
import axios from 'axios'

export default {
  data() {
    return {
      styleObject: { border: '2px solid #cafb03' },
      venues1: [],
    }
  },
  mounted() {
    axios.get('/venues/')
        .then(response => {
            this.venues1 = response.data;
        })
  },
  methods: {
    redirectToPlaceDetail(placeId) {
      console.log("redirect", placeId);
      this.$router.push({
        path: `/places/${placeId}`,
      })
    },
  },
}
</script>

<style>
.h1Style {
  color: #cafb03;
  text-shadow: 1px 1px 1px black, 1px -1px 1px black, -1px 1px 1px black,
    -1px -1px 1px black;
}
</style>
