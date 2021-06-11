<template>
  <v-row justify="center">
    <v-dialog v-model="dialog" persistent max-width="600px">
      <v-card>
        <v-card-title>
          <span class="text-h5">YOUR PLACE</span>
        </v-card-title>
        <v-card-text>
          <v-container>
            <v-row>
              <v-col cols="12" sm="6" md="4">
                <v-list-item-subtitle>PLACE</v-list-item-subtitle>
                <v-text-field type="text" v-model="userData.name">
                  <label> {{ user.name }} </label>
                </v-text-field>
              </v-col>
              <v-col cols="12">
                <v-list-item-subtitle>DESCRIPTION</v-list-item-subtitle>
                <v-text-field type="text" v-model="userData.info">
                  <label> {{ user.info }} </label>
                </v-text-field>
              </v-col>
              <v-col cols="12" sm="6" md="4">
                <v-list-item-subtitle>OPENING HOURS</v-list-item-subtitle>
                <v-text-field
                  ><template v-slot:label>
                    <div>
                      {{ user.hours }}
                    </div>
                  </template></v-text-field
                >
              </v-col>
              <v-col cols="12" sm="6" md="4">
                <v-list-item-subtitle>ADDRESS</v-list-item-subtitle>

                <v-text-field
                  ><template v-slot:label>
                    <div>
                      {{ user.address }}
                    </div>
                  </template></v-text-field
                >
              </v-col>
            </v-row>
          </v-container>
        </v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn elevation="2" outlined rounded text @click="dialog = false">
            Close
          </v-btn>
          <v-btn elevation="2" outlined rounded text @click="updateSubmit">
            Save
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </v-row>
</template>

<script>
import { mapGetters, mapActions } from 'vuex'
export default {
  data() {
    return {
      userData: {
        password: 'password',
        name: '',
        info: '',
        email: '',
        mobile: '',
        hours: {},
        website: '',
        address: {},
        pic: '',
      }
    }
  },
  props: {
    value: Boolean,
  },
  computed: {
    ...mapGetters({
      user: 'auth/user',
    }),
    dialog: {
      get() {
        return this.value
      },
      set(value) {
        console.log(value)
        this.$emit('input', value)
      },
    },
  },
  mounted: function () {
    this.userData.name = this.user.name
    this.userData.info = this.user.info
    this.userData.email = this.user.email
    this.userData.mobile = this.user.mobile
    this.userData.hours = this.user.hours
    this.userData.website = this.user.website
    this.userData.address = this.user.address
    this.userData.pic = this.user.pic
    // this.userData.password = this.user.password //this.user.password returns undefined
  },
  methods: {
    ...mapActions({
      updateAccountVenue: 'auth/updateAccountVenue',
    }),
    updateSubmit() {
      console.log('in DialogPublicPlaceUpdate.vue: registrateSubmit has been called.')
      console.log('here are the data that will be sent')
      console.log(
        this.userData
      )
      this.updateAccountVenue(
        this.userData
      )
    },
  },
}
</script>
