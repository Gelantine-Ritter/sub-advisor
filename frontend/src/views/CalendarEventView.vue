<template>
  <div v-if="currentEventObject">
    <div class="rounded-xl mycontainer" fluid>
      <v-row center>
        <!-- venueName, adress, ... -->
        <v-col cols="12">
          <h2>{{ currentVenueName }}</h2>
          <div>
            <h5>{{ currentEventObject.title }}</h5>
          </div>
        </v-col>
      </v-row>
      <v-row center>
        <v-col cols="12" xs="12" sm="7" md="7" lg="7" xl="7">
          <div>Doors: {{ currentEventObject.start }}</div>
          <div>Date: {{ currentEventObject.date }}</div>
        </v-col>
        <v-col
          cols="12"
          xs="12"
          sm="5"
          md="5"
          lg="5"
          xl="5"
          class="text-center"
        >
          Price: {{ currentEventObject.price }} €
        </v-col>
      </v-row>
      <v-row center>
        <v-col cols="12" xs="12" sm="7" md="7" lg="7" xl="7">
          <div>
            <p class="infoTruncate"> {{ currentEventObject.info }}</p>
            <p>...</p>
            <br />
          </div>
          <br />
          <v-btn
            large
            block
            outlined
            elevation="1"
            class="rounded-pill text-decoration-none myEnterBtn"
            @click="redirectToEvent(currentEventObject.id)"
          >
            GO TO EVENT
          </v-btn>
        </v-col>
        <!-- tags, price, ... -->
        <v-col
          cols="12"
          xs="12"
          sm="5"
          md="5"
          lg="5"
          xl="5"
          class="text-center"
        >
          <v-card class="pa-2 rounded-xl myLogoCard" flat tile>
            <v-responsive class="myResponsivePictureContainer">
              <v-img
                fill
                class="myPicture"
                :src="picDataUrl(currentEventObject.pic)"
                alt=""
              />
            </v-responsive>
          </v-card>
        </v-col>
      </v-row>
    </div>
  </div>
</template>

<script>
// import DateConverter from '../util/DateConverter.js'

export default {
  props: {
    currentEventObject: Object,
    currentVenueName: String,
  },
  methods: {
    picDataUrl(pic) {
      return 'data:image/png;base64, ' + pic
    },
    redirectToEvent(id) {
      location.replace(`/events/${id}`)
    },
  },
}
</script>

<style scoped>
.mycontainer {
  width: 100%;
  margin: 0;
  height: 490px;
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
  .skipIcon {
    color: #cafb03;
  }
}
</style>
