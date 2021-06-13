<template>
  <v-row justify="center">
    <v-dialog v-model="dialog" persistent max-width="600px">
      <v-card>
        <v-card-title>
          <span class="text-h5">YOUR ACCOUNT</span>
        </v-card-title>
        <v-card-text>
          <v-container>
            <v-row>
              <v-col cols="12" sm="6" md="6">
                <v-list-item-subtitle class="text-h6">E-MAIL</v-list-item-subtitle>

                <v-text-field type="text" v-model="userData.email">
                  <label> {{ user.email }} </label>
                </v-text-field>
              </v-col>
              <v-col cols="12" sm="6" md="6">
                <v-list-item-subtitle class="text-h6">MOBILE</v-list-item-subtitle>
                <v-text-field type="text" v-model="userData.mobile">
                  <label> {{ user.mobile }} </label>
                </v-text-field>
              </v-col>
              <v-col cols="12">
              <v-list-item-subtitle class="text-h6">PASSWORD</v-list-item-subtitle>
                <v-text-field type="password" v-model="userData.password">
                  <label> {{ user.password }} </label>
                </v-text-field>            
              </v-col>
              <!--
              <v-col cols="12">
                <v-list-item-subtitle class="text-h6">CONFIRM PASSWORD</v-list-item-subtitle>
                <v-text-field type="password" v-model="confirm_password">
                </v-text-field> 
              </v-col>
              -->
            </v-row>
          </v-container>
        </v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn elevation="1" outlined rounded text @click="dialog = false">
            Close
          </v-btn>
          <v-btn elevation="1" outlined rounded text @click="updateSubmit">
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
        // password: '',
        name: '',
        info: '',
        email: '',
        mobile: '',
        hours: {},
        website: '',
        address: {},
        pic: '',
        id: '',
        role: '',
      },
      confirm_password: '',
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
    this.userData.id = this.user.id
    this.userData.role = this.user.role
    // this.userData.password = this.user.password //this.user.password returns undefined
  },
  methods: {
    ...mapActions({
      updateVenue: 'auth/updateVenue',
    }),
    updateSubmit() {
      this.updateVenue(this.userData).then(() => {
        this.$toast.open('Your data have been updated!')
        this.dialog = false
      })
    },
  },
}
</script>

<style scoped>
.v-card__subtitle,
.v-card__text {
  line-height: 2rem;
}
.v-application .text-h6 {
  margin-bottom: 10px;
}
.v-text-field {
  padding-top: 0;
  margin-top: 0;
}
</style>
