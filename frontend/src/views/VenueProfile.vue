<template>
  <div>
    <template v-if="user != null">
      <div class="rounded-xl mycontainer" fluid>
        <v-card
          center
          class="rounded-xl d-flex justify-space-between mb-1"
          flat
          tile
        >
          <v-card
            class="pa-2 rounded-xl myLogoCard"
            flat
            tile
            :style="styleObject"
          >
            <v-responsive class="myResponsivePictureContainer">
              <template v-if="user.pic == null || user.pic == ''">
                <div class="noPicture"><h1>NO PICTURE</h1></div>
              </template>
              <template v-else>
                <v-img fill class="myLogo" :src="picDataUrl()" alt="" />
              </template>
            </v-responsive>
          </v-card>
          <v-card class="pa-2" flat tile>
            <h4>
              <v-btn @click.stop="showDialogPicture = true" icon class="ml-5">
                <v-icon class="text-right" color="black">far fa-edit</v-icon>
                <ModalPicture v-model="showDialogPicture" />
              </v-btn>
            </h4>
          </v-card>
        </v-card>
        <div>
          <v-card class="d-flex justify-space-between mb-1" flat tile>
            <v-card class="pa-2" flat tile>
              <h4>YOUR ACCOUNT</h4>
              <p class="font-weight-thin">
                Here you can edit your private account data.
              </p>
            </v-card>
            <v-card class="pa-2" flat tile>
              <h4>
                <v-btn @click.stop="showDialogPrivate = true" icon class="ml-5">
                  <v-icon
                    class="text-right myEditButtonSmallScreen"
                    color="black"
                    >far fa-edit</v-icon
                  >
                  <ModalPrivate v-model="showDialogPrivate" />
                </v-btn>
              </h4>
            </v-card>
          </v-card>
        </div>
        <v-list-item two-line>
          <v-list-item-content>
            <v-list-item-subtitle>USERNAME</v-list-item-subtitle>
            <v-list-item-title> {{ user.username }}</v-list-item-title>
          </v-list-item-content>
        </v-list-item>
        <v-list-item two-line>
          <v-list-item-content>
            <v-list-item-subtitle>MAIL</v-list-item-subtitle>
            <v-list-item-title> {{ user.email }} </v-list-item-title>
          </v-list-item-content>
        </v-list-item>
        <v-list-item two-line>
          <v-list-item-content>
            <v-list-item-subtitle> MOBILE </v-list-item-subtitle>
            <v-list-item-title> {{ user.mobile }} </v-list-item-title>
          </v-list-item-content>
        </v-list-item>
        <v-divider></v-divider>
        <div>
          <v-card class="d-flex justify-space-between mb-1" flat tile>
            <v-card class="pa-2" flat tile>
              <h4>YOUR PLACE</h4>
              <p class="font-weight-thin">
                Here you can edit the public informations about your Place.
              </p>
            </v-card>
            <v-card class="pa-2" flat tile>
              <h4>
                <v-btn @click.stop="showDialogPublic = true" icon class="ml-5">
                  <v-icon class="text-right" color="black">far fa-edit</v-icon>
                  <ModalPublic v-model="showDialogPublic" />
                </v-btn>
              </h4>
            </v-card>
          </v-card>
        </div>

        <v-list-item two-line>
          <v-list-item-content>
            <v-list-item-subtitle> PLACE </v-list-item-subtitle>
            <v-list-item-title> {{ user.name }} </v-list-item-title>
          </v-list-item-content>
        </v-list-item>
        <v-list-item two-line>
          <v-list-item-content>
            <v-list-item-subtitle>WEBSITE</v-list-item-subtitle>
            <v-list-item-title> {{ user.website }} </v-list-item-title>
          </v-list-item-content>
        </v-list-item>
        <v-list-item two-line>
          <v-list-item-content>
            <v-list-item-subtitle>DESCRIPTION</v-list-item-subtitle>
            <v-list-item-title> {{ user.info }} </v-list-item-title>
          </v-list-item-content>
        </v-list-item>

        <v-list-item two-line>
          <v-list-item-content>
            <v-list-item-subtitle class="mySubOpeningHours"
              >OPENING HOURS</v-list-item-subtitle
            >
            <v-list-item-title>
              <v-row class="myRowInOpeningHours">
                <v-col
                  cols="12"
                  xs="12"
                  sm="5"
                  md="3"
                  lg="3"
                  xl="3"
                  class="myDateColInOpeningHours"
                  >MONDAY:</v-col
                >
                <v-col
                  cols="12"
                  xs="12"
                  sm="7"
                  md="9"
                  lg="9"
                  xl="9"
                  class="myHoursColInOpeningHours"
                  >{{ user.hours.monday }}</v-col
                >
              </v-row>
            </v-list-item-title>
            <v-list-item-title>
              <v-row class="myRowInOpeningHours">
                <v-col
                  cols="12"
                  xs="12"
                  sm="5"
                  md="3"
                  lg="3"
                  xl="3"
                  class="myDateColInOpeningHours"
                  >TUESDAY:</v-col
                >
                <v-col
                  cols="12"
                  xs="12"
                  sm="7"
                  md="9"
                  lg="9"
                  xl="9"
                  class="myHoursColInOpeningHours"
                  >{{ user.hours.tuesday }}</v-col
                >
              </v-row>
            </v-list-item-title>
            <v-list-item-title>
              <v-row class="myRowInOpeningHours">
                <v-col
                  cols="12"
                  xs="12"
                  sm="5"
                  md="3"
                  lg="3"
                  xl="3"
                  class="myDateColInOpeningHours"
                  >WEDNESDAY:</v-col
                >
                <v-col
                  cols="12"
                  xs="12"
                  sm="7"
                  md="9"
                  lg="9"
                  xl="9"
                  class="myHoursColInOpeningHours"
                  >{{ user.hours.wednesday }}</v-col
                >
              </v-row>
            </v-list-item-title>
            <v-list-item-title>
              <v-row class="myRowInOpeningHours">
                <v-col
                  cols="12"
                  xs="12"
                  sm="5"
                  md="3"
                  lg="3"
                  xl="3"
                  class="myDateColInOpeningHours"
                  >THURSDAY:</v-col
                >
                <v-col
                  cols="12"
                  xs="12"
                  sm="7"
                  md="9"
                  lg="9"
                  xl="9"
                  class="myHoursColInOpeningHours"
                  >{{ user.hours.thursday }}</v-col
                >
              </v-row>
            </v-list-item-title>
            <v-list-item-title>
              <v-row class="myRowInOpeningHours">
                <v-col
                  cols="12"
                  xs="12"
                  sm="5"
                  md="3"
                  lg="3"
                  xl="3"
                  class="myDateColInOpeningHours"
                  >FRIDAY:</v-col
                >
                <v-col
                  cols="12"
                  xs="12"
                  sm="7"
                  md="9"
                  lg="9"
                  xl="9"
                  class="myHoursColInOpeningHours"
                  >{{ user.hours.friday }}</v-col
                >
              </v-row>
            </v-list-item-title>
            <v-list-item-title>
              <v-row class="myRowInOpeningHours">
                <v-col
                  cols="12"
                  xs="12"
                  sm="5"
                  md="3"
                  lg="3"
                  xl="3"
                  class="myDateColInOpeningHours"
                  >SATURDAY:</v-col
                >
                <v-col
                  cols="12"
                  xs="12"
                  sm="7"
                  md="9"
                  lg="9"
                  xl="9"
                  class="myHoursColInOpeningHours"
                  >{{ user.hours.saturday }}</v-col
                >
              </v-row>
            </v-list-item-title>
            <v-list-item-title>
              <v-row class="myRowInOpeningHours">
                <v-col
                  cols="12"
                  xs="12"
                  sm="5"
                  md="3"
                  lg="3"
                  xl="3"
                  class="myDateColInOpeningHours"
                  >SUNDAY:</v-col
                >
                <v-col
                  cols="12"
                  xs="12"
                  sm="7"
                  md="9"
                  lg="9"
                  xl="9"
                  class="myHoursColInOpeningHours"
                  >{{ user.hours.sunday }}</v-col
                >
              </v-row>
            </v-list-item-title>
          </v-list-item-content>
        </v-list-item>
        <v-list-item two-line>
          <v-list-item-content>
            <v-list-item-subtitle>ADDRESS</v-list-item-subtitle>
            <v-list-item-title>
              {{ user.address.street }}
              {{ user.address.number }}</v-list-item-title
            >
            <v-list-item-title>
              {{ user.address.plz }} {{ user.address.city }}
            </v-list-item-title>
          </v-list-item-content>
        </v-list-item>

        <v-divider></v-divider>

        <v-row justify="center">
          <v-btn
            class="myDeleteButton"
            outlined
            rounded
            text
            @click.stop="deleteDialog = true"
          >
            DELETE YOUR ACCOUNT
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

                <v-btn color="error" outlined @click="deleteProfileSubmit">
                  Yes
                </v-btn>
              </v-card-actions>
            </v-card>
          </v-dialog>
        </v-row>
      </div>
    </template>
    <template v-else>
      <div class="rounded-xl mycontainer" fluid>
        <h1>YOU ARE ACTUALY NOT ALLOWED TO SEE THIS :/</h1>
      </div>
    </template>
  </div>
</template>

<script>
import { mapGetters, mapActions } from 'vuex'
import ModalPrivate from './DialogAccountUpdate.vue'
import ModalPublic from './DialogPublicPlaceUpdate.vue'
import ModalPicture from './DialogPicturePlaceUpdate.vue'

export default {
  data() {
    return {
      styleObject: { border: '2px solid #cafb03' },
      showDialogPrivate: false,
      showDialogPublic: false,
      showDialogPicture: false,
      deleteDialog: false,
    }
  },
  computed: {
    ...mapGetters({
      user: 'auth/user',
    }),
  },
  components: {
    ModalPrivate,
    ModalPublic,
    ModalPicture,
  },
  methods: {
    ...mapActions({
      deleteProfile: 'auth/deleteProfile',
    }),
    picDataUrl() {
      return 'data:image/png;base64, ' + this.user.pic
    },
    deleteProfileSubmit() {
      this.deleteProfile().then(() => {
        this.$router.replace({
          name: 'home',
        })
      })
    },
  },
}
</script>

<style scoped>
.myLogoCard {
  width: 250px;
  height: 250px;
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
.myRowInOpeningHours {
  margin-bottom: 5px;
}
.mySubOpeningHours {
  margin-bottom: 20px !important;
}
@media screen and (max-width: 600px) {
  .myDateColInOpeningHours {
    padding-bottom: 0;
  }
  .myHoursColInOpeningHours {
    padding-top: 0;
  }
  .myLogoCard {
    width: 150px;
    height: 150px;
  }
  .noPicture h1 {
    line-height: 150px;
  }
}
</style>
