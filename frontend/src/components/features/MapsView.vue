<template>
  <div>
    <div id="map">
      <!--In the following div the HERE Map will render-->
      <v-avatar border size="250">
        <div
          id="mapContainer"
          style="height: 40vh; width: 100%"
          ref="hereMap"
        ></div
      ></v-avatar>
    </div>
  </div>
</template>

<script>
import axios from 'axios'

export default {
  props: {
    adress: Object,
  },
  data() {
    return {
      platform: null,
      apikey: process.env.VUE_APP_API_KEY_HERE,
    }
  },
  async mounted() {
    const platform = new window.H.service.Platform({
      apikey: this.apikey,
    })
    this.platform = platform
    this.initializeHereMap()
  },
  methods: {
    initializeHereMap() {
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
        '205?' +
        'key=' +
        API_KEY_BING

      //  console.log(finalUrl)

      axios.get(finalUrl).then((response) => {
        const latLngValue =
          response.data.resourceSets[0].resources[0].geocodePoints[0]
            .coordinates

        // Instantiate (and display) a map object:
        var map = new H.Map(mapContainer, maptypes.vector.normal.map, {
          zoom: 15,
          center: { lat: latLngValue[0], lng: latLngValue[1] },
        })

        addEventListener('resize', () => map.getViewPort().resize())

        //  console.log(new H.mapevents.Behavior(new H.mapevents.MapEvents(map)))

        // add UI
        H.ui.UI.createDefault(map, maptypes)

        var berlinMarker = new H.map.Marker({
          lat: latLngValue[0],
          lng: latLngValue[1],
        })
        map.addObject(berlinMarker)
      })
    },
  },
}
</script>

<style></style>
