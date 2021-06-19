<template>
  <v-container>
    <v-span v-for="(events, index) in eventObjs" :key="events.venueId"
      >#{{ index }}: {{ events.title }}</v-span
    >
    <v-span v-for="events in eventObjs" :key="events">{{ event.name }}</v-span>
    <v-card
      v-if="!checkIfEvents"
      @click="expand = !expand"
      center
      class="shrink ml-10 mr-10 rounded-xl md-layout md-gutter md-alignment-center"
      :style="styleObject"
    >
      <div class="pa-1">
        <v-row>
          <v-col cols="12" xs="12" sm="8" md="8" lg="7" xl="7">
            <v-card class="pa-2" outlined>
              {{ this.title }}
              <v-list-item-subtitle>
                FROM {{ convertDate(this.eventStart) }} TO
                {{ convertDate(this.eventEnd) }}</v-list-item-subtitle
              >
            </v-card>
          </v-col>
          <v-col cols="12" xs="12" sm="4" md="4" lg="5" xl="5">
            <v-card class="pa-2" outlined> Tags </v-card>
          </v-col>
        </v-row>
      </div>
      <v-expand-transition>
        <v-card v-show="expand">
          <div class="pa-1">
            <v-row>
              <v-col cols="12" xs="12" sm="8" md="8" lg="8" xl="8">
                <v-card class="pa-2" outlined>
                  {{ this.info }}
                </v-card>
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
                    <template v-if="this.pic == null || this.pic == ''">
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
          </div>
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
      eventObjs: [],
      styleObject: { border: '2px solid #000000' },
      expand: false,
    }
  },

  mounted() {
    axios.get(`/events/?venue_id=1`).then((response) => {
      const eventObjs = response.data
      const checkIfEvents = Object.values(eventObjs)
      console.log('IF EVENT', checkIfEvents)
      this.title = eventObjs[0].title
      this.info = eventObjs[0].info
      this.pic = eventObjs[0].pic
      this.eventStart = eventObjs[0].eventStart
      this.eventEnd = eventObjs[0].eventEnd
      console.log('IDID', this.venueId)
      //    Grundstruktur für v-for
      for (let i = 0; i < eventObjs.length; i++) {
        console.log(eventObjs[i])
        console.log(eventObjs[i].title)
        console.log('EVENTOBJS', eventObjs)
      }
    })
  },
  methods: {
    convertDate(date) {
      const date1 = new Date(Date(date))
      //    const date = dateTime.getMinutes()
      const convertMyDate =
        date1.getFullYear() +
        '/' +
        date1.getMonth() +
        '/' +
        date1.getDay() +
        ' - ' +
        date1.getHours() +
        ':' +
        date1.getMinutes()
      return convertMyDate
    },
    picDataUrl() {
      return 'data:image/png;base64, ' + this.eventObjs[0].pic
    },
  },
}
</script>

<style></style>
