<template>
  <div v-if="dataFinishedLoading()">
    <v-container my-5>
      <h1
        class="h1Style text-center display-3 font-weight-medium"
        :class="[$vuetify.breakpoint.mdAndUp ? 'display-4' : 'display-2']"
      >
        {{ venueObj.name }}
      </h1>

      <div class="rounded-xl mycontainer" fluid>
        <v-container>
          <v-row center>
            <!-- venueName, adress, ... -->
            <v-col cols="12" xs="12" sm="12" md="8" lg="8" xl="8">
              <v-row class="ma-1">
                <h5 class="mr-1 font-weight-bold text--disabled">
                  {{ eventObj.date }} //
                </h5>
                <h5 class="mr-1 font-weight-bold text--disabled">
                  {{ eventObj.eventStart }} - {{ eventObj.eventEnd }} //
                </h5>
                <h5 class="mr-1 font-weight-bold text--disabled">
                  € {{ eventObj.price }} //
                </h5>
                <h5 class="mr-1 font-weight-bold text--disabled">
                  <ParticipantsIcon :number="this.eventObj.amountOfGuests" />
                </h5>
              </v-row>
              <h2>{{ eventObj.title.toUpperCase() }}</h2>
              <div v-if="venueObj.address.street">
                <v-row class="ma-1">
                  <div>
                    <h5>
                      {{ venueObj.address.street }}
                      {{ venueObj.address.number }}
                      {{ venueObj.address.plz }}
                      {{ venueObj.address.city }}
                    </h5>
                  </div>
                </v-row>
                <v-row class="mt-1">
                  <MapsView :adress="venueObj.address" />
                </v-row>
              </div>
            </v-col>
          </v-row>

          <v-row center>
            <v-col cols="12" xs="12" sm="12" md="12" lg="8" xl="8">
              <div>
                <p>{{ eventObj.info }}</p>
                
              </div>
            </v-col>
            <!-- tags, price, ... -->
            <v-col
              cols="12"
              xs="12"
              sm="12"
              md="12"
              lg="4"
              xl="4"
              class="text-center"
            >
              <v-card class="pa-2 rounded-xl myLogoCard" flat tile>
                <v-responsive class="myResponsivePictureContainer">
                  <template v-if="eventObj.pic == null || eventObj.pic == ''">
                  </template>
                  <template v-else>
                    <v-img
                      fill
                      class="myLogo"
                      :src="picDataUrl(eventObj.pic)"
                      alt=""
                    />
                  </template>
                </v-responsive>
              </v-card>
              <v-row justify="center">
                <!-- EDIT EVENT BUTTON START -->
                <template
                  class="flex-column"
                  v-if="user != null && user.id == eventObj.venueId"
                >
                  <v-card class="pa-2 flex-column" flat tile>
                    <h4>
                      <v-btn
                        @click.stop="showDialogEditEvent = true"
                        icon
                        class="ml-5"
                      >
                        <v-icon
                          class="text-right myEditButtonSmallScreen"
                          color="black"
                          >far fa-edit</v-icon
                        >
                        <ModalEditEvent v-model="showDialogEditEvent" />
                      </v-btn>
                    </h4>
                  </v-card>
                </template>
                <template v-else> </template>
                <!-- EDIT EVENT BUTTON END -->
              </v-row>

              <v-row>
                <!-- DELETE EVENT BUTTON START -->
                <template v-if="user != null && user.id == eventObj.venueId">
                  <v-row justify="center">
                    <v-btn
                      class="myDeleteButton"
                      outlined
                      rounded
                      text
                      @click.stop="deleteDialog = true"
                    >
                      DELETE THIS EVENT
                    </v-btn>
                    <v-dialog v-model="deleteDialog" max-width="500">
                      <v-card>
                        <v-card-title class="text-h4 justify-center">
                          Are you really sure you want to delete your account?
                        </v-card-title>
                        <v-card-actions>
                          <v-spacer></v-spacer>
                          <v-btn
                            elevation="0"
                            color="light"
                            @click="deleteDialog = false"
                          >
                            Let me think about it...
                          </v-btn>
                          <v-btn
                            color="error"
                            outlined
                            @click="deleteEventSubmit"
                          >
                            Yes
                          </v-btn>
                        </v-card-actions>
                      </v-card>
                    </v-dialog>
                  </v-row>
                </template>

                <template v-else> </template>
                <!-- DELETE EVENT BUTTON END -->
                <!-- JOIN EVENT BUTTON & SHOW PARTICIPANTS START -->
                <v-row v-if="role === 'MEMBER'">
                  <v-col>
                    <v-row justify="center">
                      <template>
                        <v-row justify="center">
                          <v-dialog
                            v-model="dialog"
                            scrollable
                            max-width="300px"
                            ><template v-slot:activator="{ on, attrs }">
                              <v-btn
                                :disabled="!ifParticipants"
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
                                  <v-list-item-content>
                                    <v-list-item-title
                                      v-for="guest in eventObj.guests"
                                      :key="guest.guests"
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
                      <!-- JOIN EVENT BUTTON & SHOW PARTICIPANTS END -->
                    </v-row>
                  </v-col>
                </v-row>
              </v-row>
            </v-col>
          </v-row>
        </v-container>
      </div>
    </v-container>
  </div>
</template>

<script>
import ModalEditEvent from './DialogEditEvent.vue'
import { mapGetters, mapActions } from 'vuex'
import { requestProvider } from '../util/requestProvider'
import { DateConverter } from '../util/DateConverter'
import MapsView from '../components/features/MapsView.vue'
import ParticipantsIcon from '../components/events/Participants.vue'

export default {
  data() {
    return {
      eventObj: null,
      venueObj: null,
      showDialogEditEvent: false,
      deleteDialog: false,

      styleObject: { border: '2px solid #000000' },

      isActive: false,
      dialog: false,
      ifParticipants: true,
    }
  },
  beforeCreate() {
    const eventId = this.$route.params.id
    requestProvider.getEvent(eventId).then((response) => {
      this.eventObj = {
        ...response.data,
        eventStart: DateConverter.getTime(response.data.eventStart),
        eventEnd: DateConverter.getTime(response.data.eventEnd),
        date: DateConverter.getDate(response.data.date),
      }
      // show participants if there are any
      if (this.eventObj.guests.length < 1) {
        this.ifParticipants = false
      }
      if (this.role === 'MEMBER') {
        this.alreadyJoined()
      }
      requestProvider.getVenue(this.eventObj.venueId).then((responseVenue) => {
        this.venueObj = responseVenue.data
      })
    })
  },
  computed: {
    ...mapGetters({
      authenticated: 'auth/authenticated',
      user: 'auth/user',
      role: 'auth/role',
    }),
  },
  components: {
    ModalEditEvent,
    MapsView,
    ParticipantsIcon,
  },
  methods: {
    picDataUrl(pic) {
      return 'data:image/png;base64, ' + pic
    },
    redirectBackwards() {
      history.back()
    },
    dataFinishedLoading() {
      if (this.eventObj !== null && this.venueObj !== null) return true
      return false
    },
    ...mapActions({
      deleteEvent: 'auth/deleteEvent',
    }),
    deleteEventSubmit() {
      this.deleteEvent(this.eventObj.id).then(() => {
        this.$router.replace({
          name: 'home',
        })
      })
    },
    toggle() {
      const eventId = this.eventObj.id
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
      const arr = this.eventObj.guests
      arr.forEach((elem) => {
        if (elem === localStorage.getItem('user')) {
          this.isActive = true
        }
      })
    },
    getEvents() {
      const eventId = this.$route.params.id
      requestProvider.getEvent(eventId).then((response) => {
        this.eventObj = {
          ...response.data,
          eventStart: DateConverter.getTime(response.data.eventStart),
          eventEnd: DateConverter.getTime(response.data.eventEnd),
          date: DateConverter.getDate(response.data.date),
          guests: response.data.guests,
        }
        if (this.eventObj.guests.length < 1) {
          this.ifParticipants = false
        } else {
          this.ifParticipants = true
        }
      })
    },
  },
}
</script>

<style scoped>
.main-card {
  border: '2px solid #000000';
}
.head_container {
  display: flex;
  justify-content: space-between;
  align-items: center;
}
.myLogoCard {
  width: 100%;
  height: auto;
  padding: 2%;
  margin-bottom: 8%;
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
.myLogo {
  height: 100%;
}

@media screen and (max-width: 600px) {
  .myLogoCard {
    width: 100%;
    height: auto;
  }
  .noPicture h1 {
    line-height: 150px;
  }
}
</style>
