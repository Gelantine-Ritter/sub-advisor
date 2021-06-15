<template>
  <v-row justify="center">
    <v-dialog v-model="dialog" persistent max-width="600px">
      <v-card>
        <v-card-title>
          <span class="text-h5">YOUR LOGO</span>
        </v-card-title>
        <div class="rounded-xl mycontainer2" fluid>
          <h1 class="h12">FILE UPLOAD</h1>
          <v-file-input
            label="File input"
            prepend-icon="mdi-camera"
            v-model="file"
          ></v-file-input>
          <!-- <v-text-field >
            <label> {{ user.pic }} </label>
          </v-text-field> -->
          <v-btn
            large
            block
            outlined
            elevation="1"
            class="rounded-pill myEnterBtn text-center"
            v-model="userData.pic"
            @click="handleUploadSubmit"
            >UPLOAD</v-btn
          >
        </div>
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
      file: [],
      pic64: '',
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
      console.log('PIC')
      console.log(this.userData.pic)
      this.updateVenue(this.userData).then(() => {
        this.$toast.open('Your data have been updated!')
        this.dialog = false
      })
    },
    handleUploadSubmit() {
      var userDataCopy = this.userData
      console.log(this.file)
      console.log(this.file.name)
      var reader = new FileReader()
      // reader.readAsBinaryString(this.file)
      reader.readAsDataURL(this.file) // AS BASE64
      reader.onload = function () {
        console.log(reader.result)
        console.log('userdatacopy before adding the picture to the object')
        console.log(userDataCopy)
        userDataCopy.pic = reader.result
        console.log('userDataCopy.pic')
        console.log(userDataCopy.pic)
      }
    },
  },
}
</script>

<style scoped>
.mycontainer2 {
  border: solid 2px black;
  width: auto;
  margin-top: 5%;
  margin-left: 15%;
  margin-right: 15%;
  margin-bottom: 5%;
  background: white;
  padding: 5%;
}
.h12 {
  font-size: 4vw;
  color: black;
  text-align: center;
}
.myEnterBtn {
  background: black;
  color: white;
  font-size: 125%;
}
</style>
