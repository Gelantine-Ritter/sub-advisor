<template>
  <div>
    <v-container my-5 class="mySpecialContainer">
      <!-- Tags -->
      <v-row v-if="tagCollection" class="d-flex justify-center align-center">
        <v-col cols="6" xs="6" sm="6" md="2" lg="2" xl="2">
          <v-text-field v-model="selectedWord" clearable label="search">
          </v-text-field>
        </v-col>
        <v-col cols="6" xs="6" sm="6" md="2" lg="2" xl="2">
          <v-text-field
            clearable
            v-model="selectedVenue"
            label="venue"
          ></v-text-field>
        </v-col>

        <v-col cols="6" xs="6" sm="6" md="2" lg="2" xl="2">
          <v-select
            placeholder="tags"
            clearable
            :items="tagCollection"
            v-model="selectedValue"
          />
        </v-col>

        <v-col cols="6" xs="6" sm="6" md="2" lg="2" xl="2">
          <v-text-field
            clearable
            v-model="selectedPrice"
            label="max. price"
          ></v-text-field>
        </v-col>

        <!-- DATEPICKER -->
        <v-col cols="6" xs="6" sm="6" md="2" lg="2" xl="2">
          <v-menu
            v-model="menu2"
            :close-on-content-click="false"
            :nudge-right="40"
            transition="scale-transition"
            offset-y
            min-width="auto"
          >
            <template v-slot:activator="{ on, attrs }">
              <v-text-field
                v-model="selectedDate"
                label="date"
                readonly
                v-bind="attrs"
                v-on="on"
                clearable
              ></v-text-field>
            </template>
            <v-date-picker v-model="selectedDate" clearable> </v-date-picker>
          </v-menu>
        </v-col>
        <!--DATEPICKER ENDE-->

        <v-col cols="6" xs="6" sm="6" md="2" lg="2" xl="2">
          <v-btn outlined rounded v-on:click="buttonClicked"> Search </v-btn>
        </v-col>
      </v-row>
      <v-row v-if="displayNavigation">
        <v-col style="display: flex; justify-content: center" cols="2" xs="2" sm="2" md="5" lg="5" xl="5">
          <v-btn
            outlined
            rounded
            style="background-color: none; border: none"
            v-on:click="manipulateDate(-1)"
          >
            {{ signPrev }}
          </v-btn>
        </v-col>
        <v-col cols="8" xs="8" sm="8" md="2" lg="2" xl="2">
          <h1 style="display: flex; justify-content: center">
            {{ displayDate() }}
          </h1>
        </v-col>
        <v-col style="display: flex; justify-content: center" cols="2" xs="2" sm="2" md="5" lg="5" xl="5">
          <v-btn
            outlined
            rounded
            style="background-color: none; border: none"
            v-on:click="manipulateDate(1)"
            >{{ signNext }}</v-btn
          >
        </v-col>
      </v-row>

      <div v-if="eventObjects">
        <EventList
          :key="keyToRerenderComponent"
          :eventObjsList="eventObjects"
        />
      </div>
    </v-container>
  </div>
</template>

<script>
import { TagProvider } from '../util/TagProvider'
import { requestProvider } from '../util/requestProvider'
import EventList from '../components/events/EventList.vue'
import { DateConverter } from '../util/DateConverter'
import moment from 'moment'
moment().format()

export default {
  data() {
    return {
      eventObjects: null,
      tagGroupTitles: null,
      tagCollection: null,

      keyToRerenderComponent: 0,

      selectedValue: null,
      selectedVenue: null,
      selectedPrice: null,
      selectedDate: null,
      selectedWord: null,

      globalDate: null,

      manipulatedDate: 0,

      displayNavigation: true,

      menu: false,
      modal: false,
      menu2: false,

      signNext: '>>',
      signPrev: '<<',
    }
  },
  mounted() {
    this.reloadFilterResults()

    this.tagCollection = [
      { value: null, text: 'tag' },
      // { value: null, text: 'Location', disabled: true },
      ...TagProvider.whereTags,
      // { value: null, text: 'Type', disabled: true },
      ...TagProvider.eventTypeTags,
      // { value: null, text: 'Genre', disabled: true },
      ...TagProvider.genreTags,
    ]
  },
  methods: {
    async reloadFilterResults() {
      // TAG
      let tagValue
      if (this.selectedValue === 'tag') {
        tagValue = null
      } else {
        tagValue = this.selectedValue
      }

      // PRICE
      let priceValue
      if (this.selectedPrice) {
        priceValue = this.selectedPrice
      }

      // SEARCHWORD
      let searchWordValue
      if (this.selectedWord) {
        searchWordValue = this.selectedWord
      }

      // VENUE
      let searchVenue
      if (this.selectedVenue) {
        searchVenue = this.selectedVenue
      }

      // DATE
      if (this.selectedDate) {
        this.date = this.selectedDate
      } else {
        this.date = DateConverter.getTodayDate()
      }

      if (searchWordValue || priceValue || tagValue || searchVenue) {
        if (!this.selectedDate) {
          this.date = null
          this.displayNavigation = false
        } else {
          this.displayNavigation = true
        }
      }

      this.date = DateConverter.getManipulatedDate(
        this.date,
        this.manipulatedDate
      )

      // display Value
      this.globalDate = this.date

      // Initial Request to 8080
      await requestProvider
        .getEventsForDateVenueTag(searchVenue, this.date, tagValue)
        .then(async (response) => {
          let myData = response.data

          // Filter by price
          if (priceValue) {
            const userPrice = parseFloat(priceValue.replace(',', '.'))
            myData = await myData.filter((myEvent) => {
              return parseFloat(myEvent.price) <= userPrice
            })
          }

          let wordFoundArray = []

          // Filter by searchword
          wordFoundArray = await myData.filter((myEvent) => {
            if (searchWordValue === null || searchWordValue === undefined) {
              return myEvent
            }
            if (
              myEvent.title
                .toLowerCase()
                .includes(searchWordValue.toLowerCase())
            ) {
              return myEvent
            }

            let foundArtist
            // filter by artistarr
            myEvent.artists.forEach((artist) => {
              if (
                artist.toLowerCase().includes(searchWordValue.toLowerCase())
              ) {
                foundArtist = myEvent
              }
            })

            if (foundArtist) return myEvent
          })

          this.eventObjects = wordFoundArray
          // force the component to rerender
          this.keyToRerenderComponent += 1
        })
    },
    buttonClicked() {
      this.manipulatedDate = 0
      this.reloadFilterResults()
    },

    manipulateDate(value) {
      this.manipulatedDate += value
      this.reloadFilterResults()
    },

    displayDate() {
      if (this.globalDate === DateConverter.getTodayDate()) return 'TODAY'
      return DateConverter.getDate(this.globalDate)
    },
  },
  components: {
    EventList,
  },
}
</script>

<style>
option:disabled {
  background-color: rgb(245, 245, 245);
  font-weight: 1000;
  color: black;
}
@media screen and (max-width: 600px) {
  .mySpecialContainer {
    background: white;
    padding: 10px;
    min-height: 70vh;
  }
}

</style>
