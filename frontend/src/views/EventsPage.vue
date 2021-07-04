<template>
  <div>
    <v-container my-5>
      <!-- Tags -->
      <v-row v-if="tagCollection">
        <v-col>
          <v-text-field v-model="selectedWord" label="search"> </v-text-field>
        </v-col>

        <v-col>
          <v-select
            placeholder="tags"
            :items="tagCollection"
            v-model="selectedValue"
          />
        </v-col>

        <!--

      <v-col>
        <b-form-select
          v-model="selectedValue"
          :options="tagCollection"
        ></b-form-select>
      </v-col>
 -->
        <v-col>
          <v-text-field
            v-model="selectedPrice"
            label="max. price"
          ></v-text-field>
        </v-col>

        <!-- DATEPICKER -->
        <v-col>
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

        <v-col>
          <v-btn outlined rounded v-on:click="buttonClicked">
            Refresh
          </v-btn>
        </v-col>
      </v-row>
      <!-- Heading Date -->
      <v-row>
        <!-- EventList -->
      </v-row>

      <v-row>
        <v-col
          v-if="displayNavigateButtons"
          style="display: flex; justify-content: center"
        >
          <v-btn
            outlined
            rounded
            style="background-color: none; border: none"
            v-on:click="manipulateDate(-1)"
          >
            {{ signPrev }}
          </v-btn>
        </v-col>
        <v-col>
          <h1 style="display: flex; justify-content: center">
            {{ displayDate() }}
          </h1>
        </v-col>
        <v-col
          v-if="displayNavigateButtons"
          style="display: flex; justify-content: center"
        >
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
      selectedPrice: null,
      selectedDate: null,
      selectedWord: null,

      globalDate: null,

      manipulatedDate: 0,

      displayNavigateButtons: true,

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

      // DATE
      if (this.selectedDate) {
        this.date = DateConverter.getManipulatedDate(this.selectedDate, this.manipulatedDate)
      } else {
        this.date = DateConverter.getManipulatedDate(DateConverter.getTodayDate(), this.manipulatedDate)
      }

      // display Value
      this.globalDate = this.date

      if (searchWordValue || priceValue || tagValue ) {
        if (!this.selectedDate) {
          this.date = null
        }
      }


      this.date = DateConverter.getManipulatedDate(this.date, this.manipulatedDate)

      // Initial Request to 8080
      await requestProvider
        .getEventsForDateVenueTag(null, this.date, tagValue)
        .then(async (response) => {
          let myData = response.data

          // Filter by price
          if (priceValue) {
            const userPrice = parseFloat(priceValue.replace(',', '.'))
            myData = await myData.filter((myEvent) => {
              return parseFloat(myEvent.price) <= userPrice
            })
          }

          // Filter by searchword
          myData = await myData.filter((myEvent) => {
            if (searchWordValue === null || searchWordValue === undefined) return myEvent
            if (myEvent.title.toLowerCase().includes(searchWordValue.toLowerCase())) return myEvent

            // filter by artistarr
            myEvent.artists.forEach((artist) => {
              if (artist.toLowerCase().includes(searchWordValue.toLowerCase())) {
                return myEvent
              }
            })
          })

          this.eventObjects = myData
          // force the component to rerender
          this.keyToRerenderComponent += 1
        })
    },
    buttonClicked () {
      this.manipulatedDate = 0;
      this.reloadFilterResults()
    },

    manipulateDate(value) {
      this.manipulatedDate += value
      this.reloadFilterResults()
    },

    displayDate() {
      return this.globalDate
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
</style>
