<template>
  <v-row justify="center">
    <v-dialog v-model="dialog" persistent max-width="600px">
      <v-card>
        <v-card-title>
          <span class="text-h5">EDIT YOUR EVENT</span>
        </v-card-title>
        <!-- START -->
        <v-form>
          <v-container fluid>
            <v-row>
              <v-col cols="12" sm="6">
                <v-text-field
                  v-model="title"
                  :counter="20"
                  :error-messages="titleErrors"
                  label="TITLE"
                  @input="$v.title.$touch()"
                  @blur="$v.title.$touch()"
                ></v-text-field>
              </v-col>
              <v-col cols="12" sm="6">
                <v-text-field
                  v-model="artists"
                  label="ARTISTS"
                  @input="$v.artists.$touch()"
                  @blur="$v.artists.$touch()"
                ></v-text-field>
              </v-col>
              <v-col cols="12" sm="6">
                <v-text-field
                  v-model="price"
                  label="PRICE"
                  @input="$v.price.$touch()"
                  @blur="$v.price.$touch()"
                ></v-text-field>
              </v-col>
              <v-col cols="12" sm="6">
                <v-card flat>
                  <div class="rounded-xl mycontainer2" fluid>
                    <v-file-input
                      label="UPLOAD PICTURE"
                      prepend-icon="mdi-camera"
                      v-model="file"
                    ></v-file-input>
                  </div>
                </v-card>
              </v-col>

              <v-col cols="12">
                <v-textarea
                  v-model="info"
                  :counter="500"
                  :error-messages="infoErrors"
                  @input="$v.info.$touch()"
                  @blur="$v.info.$touch()"
                >
                  <template v-slot:label>
                    <div>DESCRIPTION</div>
                  </template>
                </v-textarea>
              </v-col>
              <!-- DATEPICKER START -->

              <v-col cols="12" sm="6">
                <v-menu
                  v-model="fromDateMenu"
                  :close-on-content-click="false"
                  :nudge-right="40"
                  transition="scale-transition"
                  offset-y
                  max-width="290px"
                  min-width="290px"
                >
                  <template v-slot:activator="{ on }">
                    <v-text-field
                      label="FROM"
                      readonly
                      :value="fromDateDisp"
                      v-on="on"
                    ></v-text-field>
                  </template>
                  <v-date-picker
                    locale="en-in"
                    v-model="fromDateVal"
                    no-title
                    @input="fromDateMenu = false"
                  ></v-date-picker>
                </v-menu>
                <!-- DATEPICKER END -->
              </v-col>
              <v-col cols="12" sm="6">
                <!-- DATEPICKER StART -->

                <v-menu
                  v-model="toDateMenu"
                  :close-on-content-click="false"
                  :nudge-right="40"
                  transition="scale-transition"
                  offset-y
                  max-width="290px"
                  min-width="290px"
                >
                  <template v-slot:activator="{ on }">
                    <v-text-field
                      label="TO"
                      readonly
                      :value="toDateDisp"
                      v-on="on"
                    ></v-text-field>
                  </template>
                  <v-date-picker
                    locale="en-in"
                    v-model="toDateVal"
                    no-title
                    @input="toDateMenu = false"
                  ></v-date-picker>
                </v-menu>

                <!-- DATEPICKER END -->
              </v-col>

              <!-- TIMEPICKER StART -->
              <v-col cols="12" sm="6">
                <v-menu
                  ref="menu1"
                  v-model="fromTimeMenu"
                  :close-on-content-click="false"
                  :nudge-right="40"
                  :return-value.sync="fromTimeVal"
                  transition="scale-transition"
                  offset-y
                  max-width="290px"
                  min-width="290px"
                >
                  <template v-slot:activator="{ on, attrs }">
                    <v-text-field
                      v-model="fromTimeVal"
                      label="FROM"
                      readonly
                      v-bind="attrs"
                      v-on="on"
                    ></v-text-field>
                  </template>
                  <v-time-picker
                    v-if="fromTimeMenu"
                    v-model="fromTimeVal"
                    color="black"
                    @click:minute="$refs.menu1.save(fromTimeVal)"
                  ></v-time-picker>
                </v-menu>
                <!-- TIMEPICKER END -->
              </v-col>
              <!-- TIMEPICKER StART -->

              <v-col cols="12" sm="6">
                <v-menu
                  ref="menu2"
                  v-model="toTimeMenu"
                  :close-on-content-click="false"
                  :nudge-right="40"
                  :return-value.sync="toTimeVal"
                  transition="scale-transition"
                  offset-y
                  max-width="290px"
                  min-width="290px"
                >
                  <template v-slot:activator="{ on, attrs }">
                    <v-text-field
                      v-model="toTimeVal"
                      label="TO"
                      readonly
                      v-bind="attrs"
                      v-on="on"
                    ></v-text-field>
                  </template>
                  <v-time-picker
                    v-if="toTimeMenu"
                    v-model="toTimeVal"
                    color="black"
                    @click:minute="$refs.menu2.save(toTimeVal)"
                  ></v-time-picker>
                </v-menu>
              </v-col>
              <!-- TIMEPICKER END -->
            </v-row>
          </v-container>
        </v-form>
        <!-- END -->
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn elevation="1" outlined rounded text @click="dialog = false">
            Close
          </v-btn>
          <v-btn elevation="1" outlined rounded text @click="submit">
            Save
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </v-row>
</template>

<script>
import { validationMixin } from 'vuelidate'
import { maxLength } from 'vuelidate/lib/validators'
import axios from 'axios'
import auth from './../store/auth'
import * as fileUpload from '../util/FileUpload'

export default {
  // props: 'eventId',
  mixins: [validationMixin],
  validations: {
    title: { maxLength: maxLength(20) },
    info: { maxLength: maxLength(500) },
  },
  data() {
    return {
      //  textfields
      title: '',
      artists: [],
      info: '',
      price: '',
      //  date
      fromDateMenu: false,
      fromDateVal: null,
      toDateMenu: false,
      toDateVal: null,
      //  Time
      fromTimeVal: null,
      fromTimeMenu: false,
      toTimeVal: null,
      toTimeMenu: false,
      pic: '',

      file: [],
      id: '',

      styleObject: { border: '2px solid #cafb03' },
    }
  },
  props: {
    value: Boolean,
  },
  computed: {
    dialog: {
      get() {
        return this.value
      },
      set(value) {
        console.log(value)
        this.$emit('input', value)
      },
    },
    fromDateDisp() {
      return this.fromDateVal
    },
    toDateDisp() {
      return this.toDateVal
    },
    titleErrors() {
      const errors = []
      if (!this.$v.title.$dirty) return errors
      !this.$v.title.maxLength &&
        errors.push('Title must be at most 20 characters long')
      return errors
    },
    infoErrors() {
      const errors = []
      if (!this.$v.info.$dirty) return errors
      !this.$v.info.maxLength &&
        errors.push('Description must be at most 500 characters long')
      return errors
    },
  },
  mounted() {
    const eventId = this.$route.params.id
    this.id = eventId
    axios.get('/events/' + eventId).then((response) => {
      this.title = response.data.title
      this.artists = response.data.artists
      this.fromDateVal = response.data.eventStart.split('T')[0]
      const fromTime = response.data.eventStart.split('T')[1]
      this.fromTimeVal = fromTime.substring(0, fromTime.length - 3)
      this.toDateVal = response.data.eventEnd.split('T')[0]
      const toTime = response.data.eventEnd.split('T')[1]
      this.toTimeVal = toTime.substring(0, toTime.length - 3)
      this.info = response.data.info
      this.pic = response.data.pic
      this.price = response.data.price
    })
  },
  methods: {
    convertArtistToArray(artists) {
      if (typeof artists === 'string') {
        const artistsArr = artists.split(',')
        //    map: trim jeden Artist aus Array
        const artistsArr1 = artistsArr.map((artist) => {
          return artist.trim()
        })
        console.log('TEST2', artistsArr1)
        return artistsArr1
      }
    },
    async submit() {
      this.$v.$touch()
      this.pic = await fileUpload.handleUploadSubmit(this.file)
      axios
          .put(
            '/events/' + this.id,
            {
              title: this.title,
              info: this.info,
              artists: this.convertArtistToArray(this.artists),
              price: this.price,
              eventStart: this.fromDateVal + 'T' + this.fromTimeVal + ':00',
              eventEnd: this.toDateVal + 'T' + this.toTimeVal + ':00',
              pic: this.pic,
            },
            { headers: { Authorization: 'Bearer ' + auth.state.token } }
          )
          .then(() => {
            this.$toast.open('Event updated!')
            this.dialog = false
            // this.$router.go()
          })
          .catch((e) => {
            console.log(e)
          })
      
    },
  },
}
</script>

<style></style>
