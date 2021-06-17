<template>
  <v-container>
    <v-container>
      <a @click="redirectBackwards" class="lastPage">&lt;&lt;</a>
      <h1 class="h1Style text-center display-3 font-weight-medium">
        {{ eventObj.title }}
      </h1>
    </v-container>
    
    <v-card
      center
      class="ml-10 rounded-xl md-layout md-gutter md-alignment-center"
      :style="styleObject"
    >
    <!-- EVENT PICTURE START -->
    <v-card
              class="pa-2 rounded-xl myPictureCard"
              flat
              tile
              
            >
              <v-responsive>
                <template v-if="this.eventObj.pic == null || this.eventObj.pic == ''">
                  <div class="noPicture"><h1>NO PICTURE</h1></div>
                </template>
                <template v-else>
                  <v-img fill class="myPicture" :src="picDataUrl()" alt="" />
                </template>
              </v-responsive>
            </v-card>
    <!-- EVENT PICTURE END -->
    <!-- EDIT EVENT BUTTON START -->
      <template v-if="user!= null && user.id == eventObj.venueId">
        <v-card class="pa-2" flat tile>
          <h4>
            <v-btn @click.stop="showDialogEditEvent = true" icon class="ml-5">
              <v-icon class="text-right myEditButtonSmallScreen" color="black"
                >far fa-edit</v-icon
              >
              <ModalEditEvent v-model="showDialogEditEvent"/>
            </v-btn>
          </h4>
        </v-card>
      </template>
      <template v-else>
      </template>
    <!-- EDIT EVENT BUTTON END -->
      <row container gutter="{12}">
        <column xs="{12}" md="{4}" lg="{3}" v-if="venueLoaded">
          <h2>{{ venueObj.name }}</h2>
          <column xs="{12}" md="{4}" lg="{3}">
            {{ venueObj.adress.street }}
            {{ venueObj.adress.number }}
            {{ venueObj.adress.plz }}
            {{ venueObj.adress.city }}
          </column>
        </column>
        <column xs="{12}" md="{4}" lg="{3}"> {{ eventObj.price }} Euro </column>
      </row>

      <row container gutter="{12}">
        <column xs="{12}" md="{4}" lg="{3}">
          <column xs="{12}" md="{4}" lg="{3}">
            {{ eventObj.info }}
          </column>
          {{ venueObj.website }}
        </column>
        <!-- <column xs="{12}" md="{4}" lg="{3}"> {{ eventObj.pic }} </column> -->
      </row>
      <!-- DELETE EVENT BUTTON START -->
    <template v-if="user!= null && user.id == eventObj.venueId">
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
              <v-btn elevation="0" color="light" @click="deleteDialog = false">
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
    <template v-else>
    </template>
    <!-- DELETE EVENT BUTTON END -->
    </v-card>
  </v-container>
</template>

<script>
import axios from 'axios'
import { mapGetters, mapActions } from 'vuex'
import Vue from 'vue'
import { Row, Column } from 'vue-grid-responsive'
import ModalEditEvent from './DialogEditEvent.vue'

Vue.component('row', Row)
Vue.component('column', Column)
export default {
  data() {
    return {
      eventObj: {
        id: null,
        title: null,
        artists: null,
        eventStart: null,
        eventEnd: null,
        info: null,
        modifiedAt: null,
        pic: null,
        price: null,
      },
      venueObj: {
        name: null,
        adress: {
          city: null,
          number: null,
          plz: null,
          street: null,
        },
        website: null,
        email: null,
      },

      venueLoaded: false,
      showDialogEditEvent: false,
      deleteDialog: false,

      styleObject: { border: '2px solid #000000' },
    }
  },
  mounted() {
    const eventId = this.$route.params.id
    axios.get(`/events/${eventId}`).then((response) => {
      this.eventObj.id = response.data.id
      this.eventObj.title = response.data.title
      this.eventObj.artists = response.data.artists
      this.eventObj.venueId = response.data.venueId
      this.eventObj.eventStart = response.data.eventStart
      this.eventObj.eventEnd = response.data.eventEnd
      this.eventObj.info = response.data.info
      this.eventObj.modifiedAt = response.data.modifiedAt
      this.eventObj.pic = response.data.pic
      this.eventObj.price = response.data.price

      axios.get('/venues/' + this.eventObj.venueId).then((responseVenue) => {
        this.venueObj.name = responseVenue.data.name
        console.log(responseVenue.data.address)
        this.venueObj.adress.city = responseVenue.data.address.city
        this.venueObj.adress.street = responseVenue.data.address.street
        this.venueObj.adress.number = responseVenue.data.address.number
        this.venueObj.adress.plz = responseVenue.data.address.plz
        this.venueObj.website = responseVenue.data.website
        this.venueObj.email = responseVenue.data.email
        this.venueLoaded = true
        console.log('Updated')
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
  },
  methods: {
    redirectBackwards() {
      history.back()
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

<style>
.main-card {
  border: '2px solid #000000';
}
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
.myDeleteButton {
  margin: 30px;
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
