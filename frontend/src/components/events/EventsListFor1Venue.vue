<template>
  <v-container>
    <v-card
      @click="expand = !expand"
      center
      class="shrink ml-10 mr-10 rounded-xl md-layout md-gutter md-alignment-center"
      :style="styleObject"
    >
      <v-div class="pa-1">
        <v-row>
          <v-col cols="12" xs="12" sm="8" md="8" lg="7" xl="7">
            <v-card class="pa-2" outlined>
              {{ eventObj.title }}
              <v-list-item-subtitle>
                FROM {{ convertDate(eventObj.eventStart) }} TO
                {{ convertDate(eventObj.eventEnd) }}</v-list-item-subtitle
              >
            </v-card>
          </v-col>
          <v-col cols="12" xs="12" sm="4" md="4" lg="5" xl="5">
            <v-card class="pa-2" outlined> Tags </v-card>
          </v-col>
        </v-row>
      </v-div>
      <v-expand-transition>
        <v-card v-show="expand">
          <v-div class="pa-1">
            <v-row>
              <v-col cols="12" xs="12" sm="8" md="8" lg="8" xl="8">
                <v-card class="pa-2" outlined> {{ eventObjs[0].info }} </v-card>
              </v-col>
              <v-col cols="12" xs="12" sm="4" md="4" lg="4" xl="4">
                <!-- EVENT PICTURE START -->
                <v-card
                  outlined
                  class="pa-2 rounded-xl myPictureCard"
                  flat
                  tile
                >
                  <v-responsive>
                    <template v-if="eventObj.pic == null || eventObj.pic == ''">
                      <div class="noPicture"><h1>NO PICTURE</h1></div>
                    </template>
                    <template v-else>
                      <v-img
                        fill
                        class="myPicture"
                        :src="picDataUrl()"
                        alt=""
                      />
                    </template>
                  </v-responsive>
                </v-card>
                <!-- EVENT PICTURE END -->
              </v-col>
            </v-row>
          </v-div>
        </v-card>
      </v-expand-transition>
    </v-card>
  </v-container>
</template>

<script>
import axios from 'axios'
export default {
  props: {
    venueId: String,
  },
  data() {
    return {
      eventObjs: null,
      styleObject: { border: '2px solid #000000' },
      expand: false,
    }
  },

  mounted() {
    axios.get(`/events/?venue_id=1`).then((response) => {
      this.eventObjs = response.data
      console.log('-------------')
      console.log(this.eventObj)
    })
  },
  methods: {
    convertDate(date) {
      const date1 = new Date(Date(date))
      //    const date = dateTime.getMinutes()
      console.log(date1.getMonth())
    },
    picDataUrl() {
      return 'data:image/png;base64, ' + this.eventObj.pic
    },
  },
}
</script>

<style></style>
