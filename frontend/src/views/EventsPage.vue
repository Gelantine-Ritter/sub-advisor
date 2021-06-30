<template>
  <div>
    <v-container my-5>
      <!-- Tags -->
      <v-row v-if="tagCollection">
        <v-col>
          <v-text-field v-model="searchWord" label="search"> </v-text-field>
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
                v-model="dates"
                label="date"
                readonly
                v-bind="attrs"
                v-on="on"
                clearable
              ></v-text-field>
            </template>
            <v-date-picker v-model="dates" clearable range> </v-date-picker>
          </v-menu>
        </v-col>
        <!--DATEPICKER ENDE-->

        <v-col>
          <v-btn outlined rounded v-on:click="reloadFilterResults">
            Refresh
          </v-btn>
        </v-col>
      </v-row>
      <!-- Heading Date -->
      <v-row>
        <!-- EventList -->
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
      searchWord: null,

      dates: null,
      menu: false,
      modal: false,
      menu2: false,
    }
  },
  computed: {
    dateRangeText() {
      return this.dates.join('---')
    },
  },
  mounted() {
    this.selectedDate = DateConverter.getTodayDate()
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
      let tagValue
      if (this.selectedValue === 'tag') {
        tagValue = null
      } else {
        tagValue = this.selectedValue
      }
      await requestProvider
        .getEventsForDateVenueTag(null, null, tagValue)
        .then(async (response) => {
          let myData = response.data

          // Filter by price
          if (this.selectedPrice) {
            const userPrice = parseFloat(this.selectedPrice.replace(',', '.'))
            myData = await myData.filter((myEvent) => {
              return parseFloat(myEvent.price) <= userPrice
            })
          }
          // Filter by date
          myData = await myData.filter((myEvent) => {
            if (this.dates === null) return myEvent
            if (this.dates.length > 1) {
              const sortedDates = this.sortDates(this.dates)
              if (
                moment(myEvent.date).isAfter(moment(sortedDates[0])) &&
                moment(myEvent.date).isBefore(moment(sortedDates[1]))
              ) {
                return myEvent
              }
              return null
            } else if (this.dates.length < 2) {
              if (moment(myEvent.date).isSame(moment(this.dates[0])))
                return myEvent
              return null
            } else {
              return myEvent
            }
          })
          // Filter by searchword
          myData = await myData.filter((myEvent) => {
            console.log('SEARCHWORDFILTER')
            if (this.searchWord === null) return myEvent
            if (
              myEvent.title
                .toLowerCase()
                .includes(this.searchWord.toLowerCase())
            )
              return myEvent

            // filter by artistarr
            let eventToReturn = null
            myEvent.artists.forEach((artist) => {
              if (
                artist.toLowerCase().includes(this.searchWord.toLowerCase())
              ) {
                eventToReturn = myEvent
              }
            })
            return eventToReturn
          })
          this.eventObjects = myData
          // force the component to rerender
          this.keyToRerenderComponent += 1
        })
    },
    // puts earlier date first
    sortDates(dateArr) {
      if (moment(dateArr[0]).isBefore(moment(dateArr[1]))) return dateArr
      return [dateArr[1], dateArr[0]]
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
