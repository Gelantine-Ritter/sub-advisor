<template>
  <v-container v-if="eventObjs">
    <h1 class="h1Style text-center display-1 font-weight-medium">
      UPCOMING EVENTS
    </h1>
    <v-expansion-panels>
      <v-expansion-panel
        class="shrink ml-10 mr-10 rounded-xl md-layout md-gutter md-alignment-center"
        :style="styleObject"
        v-for="event in eventObjs"
        :key="event.id"
      >
        <v-expansion-panel-header>
          <v-row>
            <v-col cols="12" xs="12" sm="8" md="8" lg="7" xl="7">
              <v-card class="pa-4" tile flat>
                <h6>{{ event.title }}</h6>
                <v-list-item-subtitle>
                  Time: {{ event.eventStart }} -
                  {{ event.eventEnd }}</v-list-item-subtitle
                >
                <v-list-item-subtitle>
                  Date: {{ event.date }}
                </v-list-item-subtitle>
                <v-list-item-subtitle>
                  Acts: {{ event.artists }}
                </v-list-item-subtitle>
              </v-card>
            </v-col>
            <v-col cols="12" xs="12" sm="4" md="4" lg="5" xl="5">
              <v-card class="pa-2" tile elevation="0">
                <v-chip class="ma-3" outlined>{{ event.price }}€</v-chip>
                <v-chip
                  v-for="tag in event.tags"
                  :key="tag"
                  class="ma-3"
                  outlined
                  >{{ tag }}</v-chip
                >
              </v-card>
            </v-col>
          </v-row>
        </v-expansion-panel-header>
        <v-expansion-panel-content>
          <v-row>
            <v-col cols="12" xs="12" sm="8" md="8" lg="8" xl="8">
              <v-card flat tile class="pa-4">
                {{ event.info }}
              </v-card>
              <v-btn
                outlined
                elevation="1"
                class="rounded-pill text-decoration-none"
                @click="redirectToEvent(event.id)"
                >Go to Event</v-btn
              >
              <v-btn
                outlined
                elevation="1"
                class="rounded-pill text-decoration-none"
                v-if="role == 'MEMBER'"
                >JOIN EVENT</v-btn
              >
            </v-col>
            <v-col cols="12" xs="12" sm="4" md="4" lg="4" xl="4">
              <!-- EVENT PICTURE START -->
              <v-card outlined class="pa-2 rounded-xl myPictureCard" flat tile>
                <v-responsive>
                  <template v-if="event.pic == null || event.pic == ''">
                    <div class="noPicture"><h1>NO PICTURE</h1></div>
                  </template>
                  <template v-else>
                    <v-img fill class="myPicture" :src="picDataUrl()" alt="" />
                  </template>
                </v-responsive>
              </v-card>
              <!-- EVENT PICTURE END -->
              <v-row> </v-row>
            </v-col>
          </v-row>
        </v-expansion-panel-content>
      </v-expansion-panel>
    </v-expansion-panels>
  </v-container>
</template>

<script>
import { requestProvider } from '../../util/requestProvider'
import { DateConverter } from '../../util/DateConverter'
import { mapGetters } from 'vuex'

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
    const venueId = this.$route.params.id
    requestProvider.getEventsForVenue(venueId).then((response) => {
      const eventList = response.data
      for (let i = 0; i < eventList.length; i++) {
        eventList[i] = {
          ...eventList[i],
          eventStart: DateConverter.getTime(eventList[i].eventStart),
          eventEnd: DateConverter.getTime(eventList[i].eventEnd),
          date: DateConverter.getDate(eventList[i].date),
          artists: this.convertArtists(eventList[i].artists),
        }
      }
      this.eventObjs = eventList
    })
  },
  methods: {
    convertArtists(artistsArr) {
      return artistsArr.join(', ')
    },
    picDataUrl() {
      return 'data:image/png;base64, ' + this.eventObjs.pic
    },
    redirectToEvent(id) {
      location.replace(`/events/${id}`)
    },
  },
  computed: {
    ...mapGetters({
      authenticated: 'auth/authenticated',
      role: 'auth/role',
    }),
  },
}
</script>

<style></style>
