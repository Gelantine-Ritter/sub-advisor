<template>
  <v-container my-5>
    <h1 class="h1Style text-center display-3 font-weight-medium">PLACES</h1>
    <h1>
      <v-btn @click="testMethod">Test</v-btn>
    </h1>
    <v-layout row wrap>
      <v-flex xs12 sm6 md4 lg3 v-for="event in venues1" :key="event.name">
        <div>
          <v-card
            center
            class="rounded-xl text-center ma-7"
            :style="styleObject"
          >
            <v-responsive class="pt-4">
              <v-img
                class="ma-5 border border-dark"
                height="250"
                src="../../public/venue2.png"
                alt=""
              />
            </v-responsive>
            <v-card-actions>
              <v-btn width="100%" flat color="rounded-pill black white--text"
                >United we Stream
              </v-btn>
            </v-card-actions>
          </v-card>
        </div>
      </v-flex>
    </v-layout>
  </v-container>
</template>

<script>
import axios from 'axios'

export default {
  data() {
    return {
      styleObject: { border: '2px solid #cafb03' },
      venues1: [
        { name: 'Event 1', image1: '../../public/venue1.png' },
        { name: 'Event 2', image1: '../../public/venue2.png' },
        { name: 'Event 3', image1: '../../public/venue3.jpeg' },
        { name: 'Event 4', image1: '../../public/venue4.jpeg' },
        { name: 'Event 5', image1: '../../public/venue1.png' },
        { name: 'Event 6', image1: '../../public/venue2.png' },
      ],
    }
  },
  methods: {
    testMethod() {
      console.log('Test clicked')
      axios.get('/venues').then((err, response) => {
        if (err) {
          console.log(err)
        } else if (!response) {
          console.log('SchadeMarmelade')
        } else {
          console.log(response)
        }
      })
    },
  },
}
</script>

<style>
.h1Style {
  color: #cafb03;
  text-shadow: 1px 1px 1px black, 1px -1px 1px black, -1px 1px 1px black,
    -1px -1px 1px black;
}
</style>
