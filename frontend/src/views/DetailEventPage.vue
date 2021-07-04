<template>
  <div v-if="dataFinishedLoading()">
    <v-container my-5>
    <h1
      class="h1Style text-center display-3 font-weight-medium"
      :class="[$vuetify.breakpoint.mdAndUp ? 'display-4' : 'display-2']"
    >
      {{ eventObj.title }}
    </h1>

    <div class="rounded-xl mycontainer" fluid>
      <v-container>
        <v-row center>
          <!-- venueName, adress, ... -->
          <v-col cols="12" xs="12" sm="12" md="8" lg="8" xl="8">
              <h2>{{ venueObj.name }}</h2>
              <div>
                <h5>
                  {{ venueObj.address.street }}
                  {{ venueObj.address.number }},
                  {{ venueObj.address.plz }}
                  {{ venueObj.address.city }}
                </h5>
              </div>
          </v-col>
          <v-col cols="12" xs="12" sm="12" md="4" lg="4" xl="4">
                    <MapsView :adress="venueObj.address" />

          </v-col>
        </v-row>
        <v-row center>
              <v-col cols="12" xs="12" sm="8" md="8" lg="8" xl="8">
              <div>
                Doors: {{ eventObj.eventStart }}
              </div>
              <div>
                Date: {{ eventObj.date }}
              </div>
              </v-col>
              <v-col cols="12" xs="12" sm="4" md="4" lg="4" xl="4" class="text-center">
            Price: {{ eventObj.price }} Euro
              </v-col>
      </v-row>
        <v-row center>  
                        <v-col cols="12" xs="12" sm="12" md="12" lg="8" xl="8">
            <div>
            <!-- <p>{{ eventObj.info }}</p> -->
            Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Sed euismod nisi porta lorem mollis. Ipsum nunc aliquet bibendum enim facilisis gravida neque. Sed augue lacus viverra vitae congue eu consequat. Proin sed libero enim sed faucibus turpis in eu. Diam maecenas ultricies mi eget mauris pharetra et ultrices neque. Scelerisque fermentum dui faucibus in. Luctus venenatis lectus magna fringilla urna porttitor rhoncus dolor purus. Elementum facilisis leo vel fringilla est ullamcorper. Donec ac odio tempor orci dapibus ultrices in iaculis. Sit amet risus nullam eget felis eget nunc. Ac turpis egestas integer eget aliquet nibh. Et tortor at risus viverra adipiscing at in tellus. Et tortor consequat id porta nibh venenatis. Sem nulla pharetra diam sit amet. Quis vel eros donec ac odio tempor orci dapibus. Consequat ac felis donec et odio pellentesque.
            <br /><br />
            Libero id faucibus nisl tincidunt eget nullam non. Est pellentesque elit ullamcorper dignissim cras tincidunt. Consectetur purus ut faucibus pulvinar elementum. Est velit egestas dui id ornare arcu. Egestas purus viverra accumsan in nisl nisi scelerisque. Nec ultrices dui sapien eget mi proin. Risus viverra adipiscing at in. Consectetur adipiscing elit duis tristique sollicitudin nibh sit amet commodo. Nunc sed velit dignissim sodales ut eu. Volutpat commodo sed egestas egestas fringilla phasellus. Elementum sagittis vitae et leo duis ut diam quam. Odio facilisis mauris sit amet massa vitae tortor.
          </div>
          </v-col>
          <!-- tags, price, ... -->
          <v-col cols="12" xs="12" sm="12" md="12" lg="4" xl="4" class="text-center">
            
            <v-card
            class="pa-2 rounded-xl myLogoCard"
            flat
            tile
          >
            <v-responsive class="myResponsivePictureContainer">
              <template v-if="eventObj.pic == null || eventObj.pic == ''">
              </template>
              <template v-else>
                <v-img fill class="myLogo" :src="picDataUrl(eventObj.pic)" alt="" />
              </template>
            </v-responsive>
          </v-card>
            <v-row justify="center">
          <!-- EDIT EVENT BUTTON START -->
          <template
            class="flex-column"
            v-if="user != null && user.id == eventObj.venueId"
          >
            <v-card class="pa-2 flex-column" flat tile>
              <h4>
                <v-btn
                  @click.stop="showDialogEditEvent = true"
                  icon
                  class="ml-5"
                >
                  <v-icon
                    class="text-right myEditButtonSmallScreen"
                    color="black"
                    >far fa-edit</v-icon
                  >
                  <ModalEditEvent v-model="showDialogEditEvent" />
                </v-btn>
              </h4>
            </v-card>
          </template>
          <template v-else> </template>
          <!-- EDIT EVENT BUTTON END -->
        </v-row>

        <v-row>
          <!-- DELETE EVENT BUTTON START -->
          <template v-if="user != null && user.id == eventObj.venueId">
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
                    <v-btn
                      elevation="0"
                      color="light"
                      @click="deleteDialog = false"
                    >
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

          <template v-else> </template>
          <!-- DELETE EVENT BUTTON END -->
          <!-- JOIN EVENT BUTTON START -->
          <v-btn
            outlined
            elevation="1"
            class="ma-2 rounded-pill text-decoration-none"
            v-if="role == 'MEMBER'"
            @click="toggle"
            :class="{active:isActive}"
            >{{isActive ? 'LEAVE EVENT' : 'JOIN EVENT'}}</v-btn
          >
          <!-- JOIN EVENT BUTTON END -->

        </v-row>
          </v-col>
        </v-row>

       

        
      </v-container>
    </div>
    </v-container>
  </div>
</template>

<script>
import ModalEditEvent from './DialogEditEvent.vue'
import { mapGetters, mapActions } from 'vuex'
import { requestProvider } from '../util/requestProvider'
import { DateConverter } from '../util/DateConverter'
import MapsView from '../components/features/MapsView.vue'

export default {
  data() {
    return {
      eventObj: null,
      venueObj: null,
      showDialogEditEvent: false,
      deleteDialog: false,

      styleObject: { border: '2px solid #000000' },
      
      isActive: false

    }
  },
  beforeCreate() {
    const eventId = this.$route.params.id
    requestProvider.getEvent(eventId).then((response) => {
      this.eventObj = {
        ...response.data,
        eventStart: DateConverter.getTime(response.data.eventStart),
        eventEnd: DateConverter.getTime(response.data.eventEnd),
        date: DateConverter.getDate(response.data.date),
      }
      this.alreadyJoined()

      requestProvider.getVenue(this.eventObj.venueId).then((responseVenue) => {
        this.venueObj = responseVenue.data
      })
    })
  },

  
  computed: {
    ...mapGetters({
      authenticated: 'auth/authenticated',
      user: 'auth/user',
      role: 'auth/role',
    }),
  },
  components: {
    ModalEditEvent,
    MapsView,
  },
  methods: {
    picDataUrl(pic) {
      return 'data:image/png;base64, ' + pic
    },
    redirectBackwards() {
      history.back()
    },
    dataFinishedLoading() {
      if (this.eventObj !== null && this.venueObj !== null) return true
      return false
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
    toggle() {
      const eventId = this.eventObj.id
      const memberId = this.user.id
      
      
      if (!this.isActive) {
         this.isActive = true;
         //   JOIN EVENT
         requestProvider.joinEvent(memberId, eventId)

      } else {
        this.isActive = false;
         requestProvider.leaveEvent(memberId, eventId)
         
        
      }
    },
    alreadyJoined(){
      const arr = this.eventObj.guests
      arr.forEach(elem => {
        if(elem === this.user.username){
          this.isActive = true
        }
      });

    }
  

  },
}
</script>

<style scoped>
.main-card {
  border: '2px solid #000000';
}
.head_container {
  display: flex;
  justify-content: space-between;
  align-items: center;
}
.myLogoCard {
  width: 100%;
  height: auto;
  padding: 2%;
  margin-bottom: 8%;
}
.myResponsivePictureContainer {
  height: 100%;
  width: 100%;
}
.noPicture {
  height: 100%;
  width: 100%;
  background: black;
  line-height: 100%;
}
.noPicture h1 {
  font-size: 100%;
  text-align: center;
  line-height: 250px;
  color: white;
}
.myLogo {
  height: 100%;
}

@media screen and (max-width: 600px) {
  .myLogoCard {
    width: 100%;
    height: auto;
  }
  .noPicture h1 {
    line-height: 150px;
  }
}
</style>
