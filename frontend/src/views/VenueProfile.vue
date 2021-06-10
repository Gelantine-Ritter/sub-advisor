<template>
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
              <v-btn @click.stop="showDialogPrivate = true" icon class="ml-5">
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
              <v-btn @click.stop="showDialogPublic = true" icon class="ml-5">
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
    </div>
  </v-container>
</template>

<script>
import { mapGetters } from 'vuex'
import ModalPrivate from './DialogAccountUpdate.vue'
import ModalPublic from './DialogPublicPlaceUpdate.vue'

export default {
  data() {
    return {
      styleObject: { border: '2px solid #cafb03' },
      showDialogPrivate: false,
      showDialogPublic: false,
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
