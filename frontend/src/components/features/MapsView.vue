<template>
  <div>
    <div class="text-center">
      <v-dialog v-model="dialog" width="80vw">
        <template v-slot:activator="{ on, attrs }">
          <v-btn
            color="rounded-pill border border-dark"
            v-bind="attrs"
            v-on="on"
          >
            Show on Map
          </v-btn>
        </template>

        <v-card>
          <div id="map">
            <div
              id="mapContainer"
              style="height: 60vh; width: 80vw"
              ref="hereMap"
            ></div>
          </div>
        </v-card>
      </v-dialog>
    </div>
  </div>
</template>

<script>

import {requestProvider} from '../../util/requestProvider'

export default {

  props: {
    adress: Object,
  },
  data() {
    return {
      dialog: null,
      platform: null,
      apikey: process.env.VUE_APP_API_KEY_HERE,
    }
  },
  updated() {
    const platform = new window.H.service.Platform({
      apikey: this.apikey,
    })
    this.platform = platform
    this.initMap()
  },
  methods: {
    initMap() {
      const mapContainer = this.$refs.hereMap
      const H = window.H
      // Obtain the default map types from the platform object
      var maptypes = this.platform.createDefaultLayers()

      // GET LAT LONG VALUES
      const url = 'http://dev.virtualearth.net/REST/v1/Locations/DE/'
      const plz = this.adress.plz
      const city = this.adress.city
      const num = this.adress.number
      const street = this.adress.street
      const API_KEY_BING = process.env.VUE_APP_API_KEY_BING

      const finalUrl =
        url +
        plz +
        '/' +
        city +
        '/' +
        street +
        '%20' +
        num +
        '?' +
        'key=' +
        API_KEY_BING

      requestProvider.getMapData(finalUrl)
        .then((response) => {
                  const latLngValue =
          response.data.resourceSets[0].resources[0].geocodePoints[0]
            .coordinates

        // Instantiate (and display) a map object:
        var map = new H.Map(mapContainer, maptypes.vector.normal.map, {
          zoom: 15,
          center: { lat: latLngValue[0], lng: latLngValue[1] },
        })

        addEventListener('resize', () => map.getViewPort().resize())

        // Add control - log muss leider drin bleiben, sorry @GG :/
        console.log(new H.mapevents.Behavior(new H.mapevents.MapEvents(map)))

        var marker = new H.map.Marker({
          lat: latLngValue[0],
          lng: latLngValue[1],
        })

        map.addObject(marker)
      })
    },
  },
}
</script>

<style scoped>
#mapContainer {
  overflow-y: hidden;
}
</style>
