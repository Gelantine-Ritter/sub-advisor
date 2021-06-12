<template>
  <div>
    <template v-if="user != null">
      <v-container>
        <div class="rounded-xl mycontainer" fluid>
          <v-card center class="rounded-xl d-flex justify-space-between mb-1" flat tile >
            <v-card class="pa-2 rounded-xl myLogoCard" flat tile :style="styleObject">
            <v-responsive>
              <v-img 
                contain
                class="myLogo"
                height="150"
                src="../../public/venue2.png"
                alt=""
              />
            </v-responsive>
            </v-card>
              <v-card class="pa-2" flat tile>
                <h4>
                  <v-btn
                    @click.stop="showDialogPicture = true"
                    icon
                    class="ml-5"
                  >
                    <v-icon class="text-right" color="black"
                      >far fa-edit</v-icon
                    >
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
                  <v-btn
                    @click.stop="showDialogPrivate = true"
                    icon
                    class="ml-5"
                  >
                    <v-icon class="text-right" color="black"
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
              <v-list-item-subtitle> PASSWORD </v-list-item-subtitle>
              <v-list-item-title type="password"> {{user.password}} </v-list-item-title>
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
              <v-list-item-title> {{user.mobile}} </v-list-item-title>
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
                  <v-btn
                    @click.stop="showDialogPublic = true"
                    icon
                    class="ml-5"
                  >
                    <v-icon class="text-right" color="black"
                      >far fa-edit</v-icon
                    >
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
              <v-list-item-subtitle>OPENING HOURS</v-list-item-subtitle>
              <v-list-item-title> <v-row>
                <v-col cols="3">MONDAY:</v-col>
                <v-col >{{ user.hours.monday }}</v-col>
              </v-row>
              </v-list-item-title>
              <v-list-item-title> <v-row>
                <v-col cols="3">TUESDAY:</v-col>
                <v-col >{{ user.hours.tuesday }}</v-col>
              </v-row>
              </v-list-item-title>
              <v-list-item-title> <v-row>
                <v-col cols="3">WEDNESDAY:</v-col>
                <v-col >{{ user.hours.wednesday }}</v-col>
              </v-row>
              </v-list-item-title>
              <v-list-item-title> <v-row>
                <v-col cols="3">THURSDAY:</v-col>
                <v-col >{{ user.hours.thursday }}</v-col>
              </v-row>
              </v-list-item-title>
              <v-list-item-title> <v-row>
                <v-col cols="3">FRIDAY:</v-col>
                <v-col >{{ user.hours.friday }}</v-col>
              </v-row>
              </v-list-item-title>
              <v-list-item-title> <v-row>
                <v-col cols="3">SATURDAY:</v-col>
                <v-col >{{ user.hours.saturday }}</v-col>
              </v-row>
              </v-list-item-title>
              <v-list-item-title> <v-row>
                <v-col cols="3">SUNDAY:</v-col>
                <v-col >{{ user.hours.sunday }}</v-col>
              </v-row>
              </v-list-item-title>
            </v-list-item-content>
          </v-list-item>
          <v-list-item two-line>
            <v-list-item-content>
              <v-list-item-subtitle>ADDRESS</v-list-item-subtitle>
              <v-list-item-title> {{ user.address.street }}  {{ user.address.number }}</v-list-item-title>
              <v-list-item-title> {{ user.address.plz }} {{ user.address.city }} </v-list-item-title>
            </v-list-item-content>
          </v-list-item>

          <v-divider></v-divider>

          <v-row justify="center">
            <v-btn class="myDeleteButton" outlined rounded text @click.stop="deleteDialog = true">
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
      </v-container>
    </template>
    <template v-else>
      <v-container>
        <div class="rounded-xl mycontainer" fluid>
          <h1>YOU ARE ACTUALY NOT ALLOWED TO SEE THIS :/</h1>
        </div>
      </v-container>
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
    ModalPicture
  },
  methods: {
    ...mapActions({
      deleteProfile: 'auth/deleteProfile',
    }),
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

<style >
.mycontainer {
  border: solid 2px black;
  width: auto;
  margin-top: 5vw;
  margin-left: 15vw;
  margin-right: 15vw;
  margin-bottom: 5vw;
  background: white;
  padding: 3vw;
}
.myDeleteButton{
  margin: 30px;
}

.myLogo{
  object-fit: scale-down;
}
.myLogoCard{
  width: 80%;
  padding: 2%;
  margin-bottom: 8%;
}
</style>

