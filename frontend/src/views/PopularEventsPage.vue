<template>
  <v-container my-5>
    <h1
      class="h1Style text-center display-3 font-weight-medium"
      :class="[$vuetify.breakpoint.mdAndUp ? 'display-4' : 'display-2']"
    >
      MOST POPULAR EVENT
    </h1>
    <div class="rounded-xl mycontainer" fluid>
      <v-container>
        <v-row v-if="dateObj">
          <v-col cols="12" xs="12" sm="8" md="8" lg="7" xl="7">
            <v-card class="pa-4" tile flat>
              <v-row class="ma-1">
                <h6 class="mr-1 font-weight-bold text--disabled">
                  {{ dateObj.date }} //
                </h6>
                <h6 class="mr-1 font-weight-bold text--disabled">
                  {{ dateObj.eventStart }} - {{ dateObj.eventEnd }} //
                </h6>
                <h6 class="font-weight-bold text--disabled">
                  <ParticipantsIcon :number="highest.amountOfGuests" />
                </h6>
              </v-row>
              <v-row class="ma-1">
                <h6>{{ highest.title.toUpperCase() }}</h6>
                <v-list-item-subtitle v-if="venueObj">
                  WHERE {{ venueObj.name }}: {{ venueObj.address.street }}
                  {{ venueObj.address.number }} {{ venueObj.address.plz }}
                  {{ venueObj.address.city }} // WITH: {{ dateObj.artists }}
                  <MapsView :adress="venueObj.address" />
                </v-list-item-subtitle>
              </v-row>
            </v-card>
          </v-col>
          <v-col cols="12" xs="12" sm="4" md="4" lg="5" xl="5">
            <v-card class="pa-2" tile elevation="0">
              <v-chip class="ma-1" outlined>{{ highest.price }}€</v-chip>
              <v-chip
                v-for="tag in highest.tags"
                :key="tag"
                class="ma-1"
                outlined
                >{{ tag }}</v-chip
              >
            </v-card>
          </v-col>
          <v-col>
            <v-row>
              <div>
                {{ highest.info }}
              </div>
            </v-row>
          </v-col>
          <v-col cols="12" xs="12" sm="4" md="4" lg="5" xl="5">
            <!-- EVENT PICTURE START -->
            <v-card class="pa-2 rounded-xl myPictureCard" flat tile>
              <v-responsive>
                <template v-if="highest.pic == null || highest.pic == ''">
                </template>
                <template v-else>
                  <v-img
                    fill
                    class="myPicture"
                    :src="picDataUrl(highest.pic)"
                    alt=""
                  />
                </template>
              </v-responsive>
            </v-card>
            <!-- EVENT PICTURE END -->
            <v-row> </v-row>
          </v-col>
        </v-row>
        <!-- JOIN EVENT BUTTON & SHOW PARTICIPANTS START -->

        <v-row v-if="role === 'MEMBER'">
          <v-col>
            <v-row justify="center">
              <template>
                <v-row justify="center">
                  <v-dialog v-model="dialog" scrollable max-width="300px"
                    ><template v-slot:activator="{ on, attrs }">
                      <v-btn
                        outlined
                        elevation="0"
                        class="ma-5 rounded-pill text-decoration-none"
                        v-bind="attrs"
                        v-on="on"
                        @click="getEvents"
                      >
                        Show Participants
                      </v-btn>
                    </template>
                    <v-card>
                      <v-card-title class="justify-center"
                        >PARTICIPANTS</v-card-title
                      >
                      <v-divider></v-divider>
                      <v-card-text style="height: 300px">
                        <v-list-item>
                          <v-list-item-content v-if="highest">
                            <v-list-item-title
                              v-for="guest in highest.guests"
                              :key="guest"
                              >{{ guest }}</v-list-item-title
                            >
                          </v-list-item-content>
                        </v-list-item>
                      </v-card-text>
                      <v-divider></v-divider>
                      <v-card-actions>
                        <v-btn
                          elevation="0"
                          outlined
                          rounded
                          text
                          @click="dialog = false"
                        >
                          Close
                        </v-btn>
                      </v-card-actions>
                    </v-card>
                  </v-dialog>
                </v-row>
              </template>
            </v-row>
            <v-row v-if="role === 'MEMBER'" justify="center">
              <v-btn
                outlined
                elevation="1"
                class="ma-5 rounded-pill text-decoration-none"
                v-if="role == 'MEMBER'"
                @click="toggle"
                :class="{ active: isActive }"
                >{{ isActive ? 'LEAVE EVENT' : 'JOIN EVENT' }}</v-btn
              >
            </v-row>
          </v-col>
        </v-row>
        <!-- JOIN EVENT BUTTON & SHOW PARTICIPANTS END -->
      </v-container>
    </div>
  </v-container>
</template>

<script>
import { requestProvider } from '../util/requestProvider'
import { DateConverter } from '../util/DateConverter'
import ParticipantsIcon from '../components/events/Participants.vue'
import { mapGetters } from 'vuex'
import MapsView from '../components/features/MapsView.vue'

export default {
  data() {
    return {
      dateObj: null,
      eventObj: null,
      highest: null,
      venueObj: null,

      styleObject: { border: '2px solid #000000' },
      isActive: false,
      dialog: false,
      ifParticipants: true,

      searchDay: null,
    }
  },
  components: {
    ParticipantsIcon,
    MapsView,
  },
  computed: {
    ...mapGetters({
      authenticated: 'auth/authenticated',
      user: 'auth/user',
      role: 'auth/role',
    }),
  },

  async created() {
    await this.getEventsForDay()
  },
  methods: {
    getEventsForDay() {
      if (this.searchDay === null) this.searchDay = DateConverter.getTodayDate()
      requestProvider
        .getEventsForDateVenueTag(null, this.searchDay, null)
        .then((response) => {
          if (response.data.length > 0) {
            this.fillEvents(response.data)
          } else {
            this.searchDay = DateConverter.addDays(this.searchDay, 1)
            this.getEventsForDay()
          }
        })
    },

    fillEvents(events) {
      let bestVisitedEvent = {}
      let eventMaxAmount = 0

      events.forEach((event) => {
        if (event.amountOfGuests >= eventMaxAmount) {
          eventMaxAmount = event.ammountOfGuests
          bestVisitedEvent = event
        }
      })

      requestProvider
        .getVenue(bestVisitedEvent.venueId)
        .then((responseVenue) => {
          this.venueObj = responseVenue.data
        })

      //  check if member already joined this event
      if (this.role === 'MEMBER') {
        this.alreadyJoined()
      }

      // convert Date + Time + Artists
      this.dateObj = {
        eventStart: DateConverter.getTime(bestVisitedEvent.eventStart),
        eventEnd: DateConverter.getTime(bestVisitedEvent.eventEnd),
        date: DateConverter.getDate(bestVisitedEvent.date),
        artists: this.convertArtists(bestVisitedEvent.artists),
      }

      this.highest = bestVisitedEvent
    },
    convertArtists(artistsArr) {
      return artistsArr.join(', ')
    },
    picDataUrl(pic) {
      return 'data:image/png;base64, ' + pic
    },
    toggle() {
      const eventId = this.highest.id
      const memberId = this.user.id
      if (!this.isActive) {
        this.isActive = true
        //   join event
        requestProvider.joinEvent(memberId, eventId).then(() => {
          this.getEvents()
        })
      } else {
        //    leave event
        this.isActive = false
        requestProvider.leaveEvent(memberId, eventId).then(() => {
          this.getEvents()
        })
      }
    },
    alreadyJoined() {
      const arr = this.highest.guests
      arr.forEach((elem) => {
        if (elem === this.user.username) {
          this.isActive = true
        }
      })
    },
    getEvents() {
      const eventId = this.highest.id
      requestProvider.getEvent(eventId).then((response) => {
        this.highest = {
          ...response.data,
          eventStart: DateConverter.getTime(response.data.eventStart),
          eventEnd: DateConverter.getTime(response.data.eventEnd),
          date: DateConverter.getDate(response.data.date),
          guests: response.data.guests,
        }
      })
    },
  },
}
</script>

<style></style>
