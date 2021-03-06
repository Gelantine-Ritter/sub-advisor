<template>
  <v-container v-if="eventObjs">
    <h1 class="h1Style text-center display-1 font-weight-medium">
      UPCOMING EVENTS
    </h1>
    <v-expansion-panels>
      <v-expansion-panel
        class="shrink rounded-xl md-layout md-gutter md-alignment-center"
        :style="styleObject"
        v-for="event in eventObjs"
        :key="event.id"
      >
        <v-expansion-panel-header>
          <v-row>
            <v-col cols="12" xs="12" sm="8" md="8" lg="7" xl="7">
              <v-card class="pa-4" tile flat>
                <v-row class="ma-1">
                  <h6 class="mr-1 font-weight-bold text--disabled">
                    {{ event.date }} //
                  </h6>
                  <h6 class="mr-1 font-weight-bold text--disabled">
                    {{ event.eventStart }} - {{ event.eventEnd }} //
                  </h6>
                  <h6 class="font-weight-bold text--disabled">
                    <ParticipantsIcon :number="event.amountOfGuests" />
                  </h6>
                </v-row>
                <h6>{{ event.title.toUpperCase() }}</h6>
                <v-list-item-subtitle>
                  WITH: {{ event.artists.toUpperCase() }}
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
            <v-col cols="12" xs="12" sm="8" md="8" lg="7" xl="7">
              <v-card flat tile class="pa-4">
                <p class="infoTruncate">{{ event.info }}</p>
                <p>...</p>
              </v-card>
            </v-col>
            <v-col cols="12" xs="12" sm="4" md="4" lg="5" xl="5">
              <!-- EVENT PICTURE START -->
              <v-card class="pa-2 rounded-xl myPictureCard" flat tile>
                <v-responsive>
                  <template v-if="event.pic == null || event.pic == ''">
                  </template>
                  <template v-else>
                    <v-img
                      fill
                      class="myPicture"
                      :src="picDataUrl(event.pic)"
                      alt=""
                    />
                  </template>
                </v-responsive>
              </v-card>
              <!-- EVENT PICTURE END -->
              <v-row> </v-row>
            </v-col>
            <v-row class="justify-center">
              <v-btn
                outlined
                elevation="1"
                class="my-6 rounded-pill text-decoration-none"
                @click="redirectToEvent(event.id)"
                >Go to Event</v-btn
              >
            </v-row>
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
import ParticipantsIcon from '../events/Participants.vue'

export default {
  data() {
    return {
      eventObjs: null,
      styleObject: { border: '2px solid #000000' },
      expand: false,
    }
  },
  props: {
    venueId: String,
  },
  components: {
    ParticipantsIcon,
  },

  mounted() {
    let venueId
    venueId = this.$route.params.id
    if (!venueId) {
      venueId = this.venueId
    }
    requestProvider.getEventsForVenue(venueId).then((response) => {
      const eventList = DateConverter.sortEventArrayByStartingTime(
        response.data
      )
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
    picDataUrl(pic) {
      return 'data:image/png;base64, ' + pic
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

<style>
.myPictureCard {
  width: 350px;
  height: auto;
  padding: 2%;
  margin: auto;
  margin-bottom: 8%;
  border: 0px;
}
.myResponsivePictureContainer {
  height: 100%;
  width: 100%;
}
.noPicture {
  height: 100%;
  width: 100%;
  background: black;
  line-height: 100%;
}
.noPicture h1 {
  font-size: 100%;
  text-align: center;
  line-height: 250px;
  color: white;
}
.myPicture {
  height: 100%;
}

@media screen and (max-width: 600px) {
  .myPictureCard {
    width: 100%;
    height: auto;
  }
  .noPicture h1 {
    line-height: 150px;
  }
}
</style>
