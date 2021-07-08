<template>
  <v-container v-if="eventObjs">
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
                <v-row class="ma-1">
                <h6 class="mr-1 font-weight-bold text--disabled">{{ event.date }} //</h6>
                <h6 class="mr-1 font-weight-bold text--disabled"> {{ event.eventStart }} -
                  {{ event.eventEnd }} // </h6>
                <h6 class="font-weight-bold text--disabled"><ParticipantsIcon  :number="event.amountOfGuests"/>
                </h6>
                </v-row>
                <v-row class="ma-1">
                  <h6>{{ event.title.toUpperCase() }}</h6> 
                   <v-list-item-subtitle> 
                  WITH: {{ event.artists.toUpperCase() }}
                  </v-list-item-subtitle>
                  </v-row>
              </v-card>
            </v-col>
            <v-col cols="12" xs="12" sm="4" md="4" lg="5" xl="5">
              <v-card class="pa-2" tile elevation="0">
                <v-chip class="ma-1" outlined>{{ event.price }}€</v-chip>
                <v-chip
                  v-for="tag in event.tags"
                  :key="tag"
                  class="ma-1"
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
                {{ event.info }}
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
                class="mb-6 rounded-pill text-decoration-none"
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
import { DateConverter } from '../../util/DateConverter'
import ParticipantsIcon from '../events/Participants.vue'

export default {
  data() {
    return {
      eventObjs: null,
      styleObject: { border: '2px solid #000000' },
      expand: false,
    }
  },
    components:{
    ParticipantsIcon
  },
    props: {
    eventObjsList: Array,
  },

  mounted() {
    const myList = this.eventObjsList
    this.eventObjs = this.reformatDateAndArray(myList)
  },
  methods: {
    picDataUrl(pic) {
      return 'data:image/png;base64, ' + pic
    },
    redirectToEvent(id) {
      location.replace(`/events/${id}`)
    },
    convertArtists(artistsArr) {
      return artistsArr.join(', ')
    },
    reformatDateAndArray(eventList) {
      const finalList = eventList
      for (let i = 0; i < eventList.length; i++) {
        finalList[i] = {
          ...eventList[i],
          eventStart: DateConverter.getTime(eventList[i].eventStart),
          eventEnd: DateConverter.getTime(eventList[i].eventEnd),
          date: DateConverter.getDate(eventList[i].date),
          artists: this.convertArtists(eventList[i].artists),
        }
      }
      return finalList
    },
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
