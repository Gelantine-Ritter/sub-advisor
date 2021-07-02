<template>
  <v-container>
    <div>
      <EventsList class="mt-5" :venueId="this.user.id" />
    </div>
  </v-container>
</template>

<script>
import EventsList from '../components/events/EventsListFor1Venue.vue'
import { mapGetters } from 'vuex'

export default {
  data() {
    return {
      test: null,
    }
  },
  computed: {
    ...mapGetters({
      user: 'auth/user',
    }),
  },

  components: {
    EventsList,
  },
}
</script>

<style></style>
