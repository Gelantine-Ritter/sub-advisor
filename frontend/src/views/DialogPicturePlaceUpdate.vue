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
          <v-text-field class="d-none" v-model="userData.pic"></v-text-field>
          <v-btn
            large
            block
            outlined
            elevation="1"
            class="rounded-pill myEnterBtn text-center"
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
        password: null,
        pic: null,
      },
      file: [],
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
    this.userData.pic = this.user.pic
  },
  methods: {
    ...mapActions({
      updateVenue: 'auth/updateVenue',
    }),
    updateSubmit() {
      console.log('PIC')
      console.log(this.userData.pic)
      this.updateVenue(this.userData).then(() => {
        this.$toast.open('Your picture has been updated!')
        this.dialog = false
      })
    },
    async handleUploadSubmit() {
      try {
        const fileContentsBase64 = await this.readUploadedFileAsBase64(
          this.file
        )
        this.userData.pic = fileContentsBase64.substr(
          fileContentsBase64.indexOf(',') + 1
        )
        console.log('CHECK IF THIS.USERDATA.PIC HAS BEEN UPDATED')
        console.log(this.userData.pic)
      } catch (e) {
        console.warn(e.message)
      }
    },
    
    readUploadedFileAsBase64(inputFile) {
      const temporaryFileReader = new FileReader()
      return new Promise((resolve, reject) => {
        temporaryFileReader.onerror = () => {
          temporaryFileReader.abort()
          reject(new DOMException('Problem parsing input file.'))
        }
        temporaryFileReader.onload = () => {
          resolve(temporaryFileReader.result)
        }
        temporaryFileReader.readAsDataURL(inputFile)
      })
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
