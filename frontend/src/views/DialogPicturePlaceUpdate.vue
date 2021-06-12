<template>
  <v-row justify="center">
    <v-dialog v-model="dialog" persistent max-width="600px">
      <v-card>
        <v-card-title>
          <span class="text-h5">YOUR LOGO</span>
        </v-card-title>
        
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
      },
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
      updateVenue: 'auth/updateVenue',
    }),
    updateSubmit() {
      this.updateVenue(this.userData).then(() => {
        this.$toast.open('Your data has been updated!')
        this.dialog = false
      })
    },
  },
}
</script>

<style scoped>

</style>
