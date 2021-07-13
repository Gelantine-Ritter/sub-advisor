<template>
  <div>
    <v-container my-5>
      <v-row class="justify-center">
        <v-col cols="10">
          <h1 class="h1Style text-left">
            YOUR
            <br />
            CALENDAR
          </h1>
        </v-col>
      </v-row>
      <br />
      <v-col cols="12">
        <v-row class="justify-space-between align-center">
          <v-col
            cols="12"
            sm="12"
            md="4"
            lg="4"
            xl="4"
            class="text-center justify-center d-none d-md-flex"
          >
            <v-date-picker
              class="myCalendar"
              rounded-xl
              color="black"
              is-dark
              is-range
              no-title
              v-model="dateValue"
              @change="fetchEventsForDate"
            ></v-date-picker>
          </v-col>
          <v-col
            cols="12"
            sm="12"
            md="4"
            lg="4"
            xl="4"
            class="text-center align-center d-lg-none"
          >
            <v-menu
              :close-on-content-click="false"
              :nudge-right="40"
              transition="scale-transition"
              offset-y
              max-width="290px"
              min-width="290px"
            >
              <template v-slot:activator="{ on }">
                <v-text-field
                  large
                  block
                  outlined
                  class="rounded-pill centered-input myDateField"
                  label=" DATE"
                  color="black"
                  readonly
                  v-on="on"
                  v-model="dateValue"
                ></v-text-field>
              </template>
              <v-date-picker
                locale="en-in"
                rounded-xl
                color="black"
                is-dark
                is-range
                no-title
                v-model="dateValue"
                @change="fetchEventsForDate"
              ></v-date-picker>
            </v-menu>
          </v-col>
          <v-col
            cols="12"
            sm="12"
            md="8"
            lg="8"
            xl="8"
            v-if="eventObjects.length > 0"
          >
          <v-row class="align-center">
            <v-col
              cols="1"
              sm="1"
              md="1"
              lg="1"
              xl="1"
              class="text-right"
              @click="changeSwipeEventValue(-1)"
            >
              <ButtonLeft v-if="showButtonLeft" :key="rerenderButtonL" />
            </v-col>
            <v-col cols="10" sm="10" md="10" lg="10" xl="10">
              <CalendarEventView
                :key="keyToRenderComponent"
                :currentEventObject="currentEventObject"
                :currentVenueName="currentVenueName"
              />
            </v-col>
            <v-col
              cols="1"
              sm="1"
              md="1"
              lg="1"
              xl="1"
              class="text-left"
              @click="changeSwipeEventValue(1)"
            >
              <ButtonRight v-if="showButtonRight" :key="rerenderButtonR" />
            </v-col>
          </v-row>
          </v-col>
          <v-col
            cols="12"
            sm="12"
            md="8"
            lg="8"
            xl="8"
            v-if="eventObjects.length === 0"
          >
          <v-row class="align-center">
            <v-col
              cols="1"
              sm="1"
              md="1"
              lg="1"
              xl="1"
              class="text-right"
            >
              <ButtonLeft class="myDisabledButton" />
            </v-col>
            <v-col cols="10" sm="10" md="10" lg="10" xl="10">
              <div class="rounded-xl mycontainer text-center noEventsContainer align-center " fluid>
                <v-img fill height="100%" width="100%" src="../assets/tv-noise.jpeg" alt="Welcome to SUB" class="rounded-xl"/>
                <h1 class="h1Style noEventsText"> NO EVENTS </h1>
              </div>
            </v-col>
            <v-col
              cols="1"
              sm="1"
              md="1"
              lg="1"
              xl="1"
              class="text-left"
            >
              <ButtonRight class="myDisabledButton" />
            </v-col>
          </v-row>
          </v-col>
        </v-row>
      </v-col>
    </v-container>
  </div>
</template>

<script>
import { requestProvider } from '../util/requestProvider'

import { mapGetters } from 'vuex'
import CalendarEventView from './CalendarEventView'
import ButtonLeft from '../components/schnickschnack/ButtonLeft.vue'
import ButtonRight from '../components/schnickschnack/ButtonRight.vue'

export default {
  data() {
    return {
      eventObjects: [],

      currentEventObject: null,
      currentVenueName: null,

      dateValue: null,

      swipeEventValue: 0,

      // keys to rerender content
      rerenderButtonL: -1000,
      rerenderButtonR: 1000,
      keyToRenderComponent: 0,

      // change to Show Buttons
      showButtonLeft: false,
      showButtonRight: false,
    }
  },
  methods: {
    async fetchEventsForDate() {
      if (this.dateValue) {
        await requestProvider
          .getEventsForDateVenueTag(null, this.dateValue, null)
          .then(async (response) => {
            let allEventsForThatDay = response.data
            allEventsForThatDay = allEventsForThatDay.filter((event) => {
              if (event.guests.includes(this.user.username)) {
                return event
              }
            })

            if (allEventsForThatDay.length > 1) {
              this.showButtonLeft = true
              this.showButtonRight = true
            } else {
              this.showButtonLeft = false
              this.showButtonRight = false
            }

            this.eventObjects = allEventsForThatDay

            if (this.eventObjects.length > 0) {
              // PREPARE OBJET FOR COMPONENT
              this.getCurrentEventAndVenueName().then(() => {
                // FINAL UPDATE
                this.displayContent()
              })
            }
          })
      }
    },
    async getCurrentEventAndVenueName() {
      // if User swiped (left/right buttons)
      if (typeof this.eventObjects[this.swipeEventValue] === 'undefined') {
        this.swipeEventValue > 0
          ? (this.swipeEventValue = 0)
          : (this.swipeEventValue = this.eventObjects.length - 1)
      }
      if (this.eventObjects[this.swipeEventValue] === 'undefined') return null

      await this.getVenueNameForEvent(
        this.eventObjects[this.swipeEventValue]
      ).then((venueName) => {
        this.currentEventObject = this.eventObjects[this.swipeEventValue]
        this.currentVenueName = venueName
      })
    },

    async getVenueNameForEvent(event) {
      const a = await requestProvider.getVenue(event.venueId)
      return a.data.name
    },
    changeSwipeEventValue(num) {
      this.swipeEventValue += num
      this.getCurrentEventAndVenueName()
      this.displayContent()
    },
    displayContent() {
      this.rerenderButtonL += 1
      this.rerenderButtonR += 1
      this.keyToRenderComponent += 1
    },
  },
  computed: {
    ...mapGetters({
      user: 'auth/user',
    }),
  },
  components: {
    CalendarEventView,
    ButtonLeft,
    ButtonRight,
  },
}
</script>

<style scoped>
.mycontainer {
  width: 100%;
  margin: 0;
}
::v-deep .v-input__slot {
  background: white !important;
}

.myCalendar {
  border: solid 2px black;
  border-radius: 24px;
}
.myLogoCard {
  width: 100%;
  padding: 2%;
  margin-bottom: 8%;
}
.noPicture {
  height: 100%;
  width: 100%;
  background: black;
}
.noPicture h1 {
  font-size: 100%;
  text-align: center;
  line-height: 8.5em;
  color: white;
}
.myLogo {
  height: 100%;
}
.myDisabledButton{
  opacity: 0.2;
}
.noEventsContainer{
  height: 490px;
  padding:0;
}
.noEventsText{
  position: relative;
  top: -285px;
  padding: 20px;
  background: white;
}
@media (min-width: 960px) {
  .container {
    max-width: 95vw !important;
  }
}
@media screen and (max-width: 600px) {
  .myLogoCard {
    width: 100%;
    height: auto;
  }
  .noPicture h1 {
    line-height: 150px;
  }
  ::v-deep .v-label--active{
    color: #cafb03 !important;
    font: large !important;
    font: bold !important;
  }
}
</style>
