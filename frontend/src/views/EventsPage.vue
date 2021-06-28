<template>
  <div class="rounded-xl mycontainer" fluid>
    <!-- Tags -->
    <v-row v-if="tagCollection">
      <v-col>
        <b-form-select
          v-model="selectedValue"
          :options="tagCollection"
        ></b-form-select>
      </v-col>
      <v-col>
        <b-form-input
          v-model="selectedPrice"
          placeholder="max. Price"
        ></b-form-input>
      </v-col>
      <v-col>
        <v-btn outlined rounded v-on:click="reloadFilterResults">
          Refresh
        </v-btn>
      </v-col>
    </v-row>
    <!-- Heading Date -->
    <v-row> {{ selectedValue }} </v-row>
    <v-row>
      <!-- EventList -->
      <div v-if="eventObjects">
        <EventList
          :key="keyToRerenderComponent"
          :eventObjsList="eventObjects"
        />
      </div>
    </v-row>
  </div>
</template>

<script>
import { TagProvider } from '../util/TagProvider'
import { requestProvider } from '../util/requestProvider'
import EventList from '../components/events/EventList.vue'
import { DateConverter } from '../util/DateConverter'

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
    }
  },
  computed: {},
  mounted() {
    this.selectedDate = DateConverter.getTodayDate()
    this.reloadFilterResults()

    this.tagCollection = [
      { value: null, text: '--- Choose Tag ---' },
      { value: null, text: 'Location', disabled: true },
      ...TagProvider.whereTags,
      { value: null, text: 'Type', disabled: true },
      ...TagProvider.eventTypeTags,
      { value: null, text: 'Genre', disabled: true },
      ...TagProvider.genreTags,
    ]
  },
  methods: {
    async reloadFilterResults() {
      await requestProvider
        .getEventsForDateVenueTag(null, null, this.selectedValue)
        .then((response) => {
          const myData = response.data
          let myNewData
          // Filter by price
          if (this.selectedPrice) {
            const userPrice = parseFloat(this.selectedPrice.replace(',', '.'))
            myNewData = myData.filter((myEvent) => {
              return parseFloat(myEvent.price) <= userPrice
            })
          }
          if (myNewData) {
            this.eventObjects = myNewData
          } else {
            this.eventObjects = myData
          }
          // force the component to rerender
          this.keyToRerenderComponent += 1
        })
    },
  },
  components: {
    EventList,
  },
}
</script>

<style>
</style>
