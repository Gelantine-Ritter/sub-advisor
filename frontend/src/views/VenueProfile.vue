<template>
  <div>
    <template v-if="user != null">
      <v-container>
        <div class="rounded-xl mycontainer" fluid>
          <v-card center class="rounded-xl mb-7" :style="styleObject">
            <v-responsive>
              <v-img
                class="ma-5 border border-dark"
                height="250"
                src="../../public/venue2.png"
                alt=""
              />
            </v-responsive>
          </v-card>
          <div>
            <v-card class="d-flex justify-space-between mb-1" flat tile>
              <v-card class="pa-2" flat tile>
                <h4>YOUR ACCOUNT</h4>
                <p class="font-weight-thin">
                  Here you can find your private account data.
                </p>
              </v-card>
              <v-card class="pa-2" flat tile>
                <h4>
                  <v-btn
                    @click.stop="showDialogPrivate = true"
                    icon
                    class="ml-5"
                  >
                    <v-icon class="text-right" dense color="grey"
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
              <v-list-item-title> ..... </v-list-item-title>
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
              <v-list-item-subtitle> PASSWORD </v-list-item-subtitle>
              <v-list-item-title> ... </v-list-item-title>
            </v-list-item-content>
          </v-list-item>
          <v-divider></v-divider>
          <div>
            <v-card class="d-flex justify-space-between mb-1" flat tile>
              <v-card class="pa-2" flat tile>
                <h4>YOUR PLACE</h4>
                <p class="font-weight-thin">
                  Here you can edit your data for your Place.
                </p>
              </v-card>
              <v-card class="pa-2" flat tile>
                <h4>
                  <v-btn
                    @click.stop="showDialogPublic = true"
                    icon
                    class="ml-5"
                  >
                    <v-icon class="text-right" dense color="grey"
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
              <v-list-item-subtitle>DESCRIPTION</v-list-item-subtitle>
              <v-list-item-title> {{ user.info }} </v-list-item-title>
            </v-list-item-content>
          </v-list-item>

          <v-list-item two-line>
            <v-list-item-content>
              <v-list-item-subtitle>OPENING HOURS</v-list-item-subtitle>
              <v-list-item-title> ... </v-list-item-title>
            </v-list-item-content>
          </v-list-item>
          <v-list-item two-line>
            <v-list-item-content>
              <v-list-item-subtitle>ADDRESS</v-list-item-subtitle>
              <v-list-item-title> ... </v-list-item-title>
            </v-list-item-content>
          </v-list-item>

          <v-divider></v-divider>

          <v-row justify="center">
            <v-btn color="error" @click.stop="deleteDialog = true">
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
                    Let me think again about it...
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

export default {
  data() {
    return {
      styleObject: { border: '2px solid #cafb03' },
      showDialogPrivate: false,
      showDialogPublic: false,
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

<style>
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
</style>
