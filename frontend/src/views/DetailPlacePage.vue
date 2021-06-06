<template>
  <v-container>
    <v-container class="head_container">
      <a href="../" class="lastPage">&lt;&lt;</a>
      <h1 class="h1Style text-center display-3 font-weight-medium">
        {{ venueObj.name }}
      </h1>
      <p></p>
    </v-container>
    <v-card
      center
      class="ml-10 rounded-xl md-layout md-gutter md-alignment-center"
      :style="styleObject"
    >
      <v-row gutters>
        <v-col cols="12" sm="6" md="8">
          <div class="childElem md-layout-item text-left">
            {{ venueObj.email }}
          </div>
        </v-col>
        <v-col cols="6" md="4">
          <div class="md-layout-item text-left" outlined tile></div>
        </v-col>
      </v-row>
      <v-row no-gutters>
        <v-col cols="12" sm="6" md="8">
          <div class="childElem md-layout-item text-left">
            {{ venueObj.info }}
          </div>
        </v-col>
        <v-col cols="6" md="4">
          <div class="md-layout-item text-left" outlined tile>
            <v-img
              class="ma-5 border border-dark"
              height="250"
              src="../../public/venue2.png"
              alt=""
            />
          </div>
        </v-col>
      </v-row>

      <v-card-actions> </v-card-actions>
    </v-card>
  </v-container>
</template>

<script>
import axios from 'axios'

export default {
  data() {
    return {
      styleObject: { border: '2px solid #000000' },
      venueObj: {
        name: 'SampleName',
        email: 'SampleMail',
        info: 'SampleInfo',
      },
    }
  },
  mounted() {
    const param = this.$route.params.id
    axios.get(`/venues/${param}`)
        .then(response => {
            this.venueObj.name = response.data.name
            this.venueObj.email = response.data.email
            this.venueObj.info = response.data.info
        })
  },
}
</script>

<style>
.head_container {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.h1Style {
  color: #cafb03;
  text-shadow: 1px 1px 1px black, 1px -1px 1px black, -1px 1px 1px black,
    -1px -1px 1px black;
}

.lastPage {
  text-decoration: none;
  font-size: 1.5em;
  color: black;
}

.lastPage:visited {
  color: black;
  text-decoration: none;
}

.lastPage:hover {
  color: #cafb03;
  text-decoration: none;
}

.childElem {
  margin: 2vw;
}
</style>
