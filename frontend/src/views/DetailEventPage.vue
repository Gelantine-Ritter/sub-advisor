<template>
  <div v-if="dataFinishedLoading()">
    <h1
      class="h1Style text-center display-3 font-weight-medium"
      :class="[$vuetify.breakpoint.mdAndUp ? 'display-4' : 'display-2']"
    >
      {{ eventObj.title }}
    </h1>

    <v-card
      center
      class="ml-10 mr-10 rounded-xl md-layout md-gutter md-alignment-center"
      :style="styleObject"
    >
      <v-container>
        <v-row center>
          <!-- venueName, adress, ... -->
          <v-col cols="12" xs="12" sm="8" md="8" lg="8" xl="8">
            <div>
              {{ venueObj.pic }}
            </div>
            <div>
              <h2>{{ venueObj.name }}</h2>
              <div>
                <h5>
                  {{ venueObj.address.street }}
                  {{ venueObj.address.number }},
                  {{ venueObj.address.plz }}
                  {{ venueObj.address.city }}
                </h5>
              </div>
              <div>
                Doors: {{ eventObj.eventStart }}
              </div>
              <div>
                Date: {{ eventObj.date }}
              </div>
            </div>
          </v-col>
          <!-- tags, price, ... -->
          <v-col cols="12" xs="12" sm="4" md="4" lg="4" xl="4">
            <MapsView :adress="venueObj.address" />
            Price: {{ eventObj.price }} Euro

          </v-col>
        </v-row>

        <v-row>
          <v-col>
            <p>{{ eventObj.info }}</p>
          </v-col>
        </v-row>

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
                    <v-btn color="error" outlined @click="deleteEventSubmit">
                      Yes
                    </v-btn>
                  </v-card-actions>
                </v-card>
              </v-dialog>
            </v-row>
          </template>

          <template v-else> </template>
          <!-- DELETE EVENT BUTTON END -->
        </v-row>
      </v-container>
    </v-card>
  </div>
</template>

<script>
import ModalEditEvent from './DialogEditEvent.vue'
import { mapGetters, mapActions } from 'vuex'
import { requestProvider } from '../util/requestProvider'
import { DateConverter } from '../util/DateConverter'
import MapsView from '../components/features/MapsView.vue'

export default {
  data() {
    return {
      eventObj: null,
      venueObj: null,
      showDialogEditEvent: false,
      deleteDialog: false,

      styleObject: { border: '2px solid #000000' },
    }
  },
  beforeCreate() {
    const eventId = this.$route.params.id
    requestProvider.getEvent(eventId).then((response) => {
      this.eventObj = {
          ...response.data,
          eventStart: DateConverter.getTime(response.data.eventStart),
          eventEnd: DateConverter.getTime(response.data.eventEnd),
          date: DateConverter.getDate(response.data.date)
 }

      requestProvider.getVenue(this.eventObj.venueId).then((responseVenue) => {
        this.venueObj = responseVenue.data
      })
    })
  },
  computed: {
    ...mapGetters({
      user: 'auth/user',
    }),
  },
  components: {
    ModalEditEvent,
    MapsView,
  },
  methods: {
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
    picDataUrl() {
      return 'data:image/png;base64, ' + this.eventObj.pic
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
.myPicture {
  height: 100%;
}
.myPictureCard {
  padding: 2%;
  margin-bottom: 8%;
}
.noPicture {
  height: 100%;
  background: black;
  line-height: 100%;
}
.noPicture h1 {
  font-size: 70%;
  text-align: center;
  line-height: 100%;
  color: white;
}
@media screen and (max-width: 600px) {
  .noPicture {
    height: 100px;
    width: 100px;
    background: black;
    line-height: 100px;
  }
  .noPicture h1 {
    font-size: 70%;
    text-align: center;
    line-height: 100px;
    color: white;
  }
}
</style>
