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
                  :counter="30"
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
              <v-col cols="12" sm="6">
                <v-select
                  v-model="whereTags"
                  :items="whereTagsItems"
                  attach
                  chips
                  label="LOCATION"
                  multiple
                ></v-select>
              </v-col>
              <v-col cols="12" sm="6">
                <v-select
                  v-model="eventTypeTags"
                  :items="eventTypeTagsItems"
                  attach
                  chips
                  label="EVENT TYPE"
                  multiple
                ></v-select>
              </v-col>
              <v-col cols="12" sm="6">
                <v-select
                  v-model="genreTags"
                  :items="genreTagsItems"
                  attach
                  chips
                  label="GENRE"
                  multiple
                ></v-select>
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
import { required, maxLength } from 'vuelidate/lib/validators'
import auth from './../store/auth'
import * as fileUpload from '../util/FileUpload'
import { requestProvider } from '../util/requestProvider'

export default {
  // props: 'eventId',
  mixins: [validationMixin],
  validations: {
    title: { maxLength: maxLength(30) },
    info: { maxLength: maxLength(500) },
    whereTags: { required },
    eventTypeTags: { required },
    genreTags: { required },
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
      // Tags
      whereTagsItems: ['inside', 'outside', 'online'],
      whereTags: [],
      eventTypeTagsItems: [
        'dj',
        'concert',
        'jam',
        'exhibition',
        'performance',
        'conversation',
      ],
      eventTypeTags: [],
      genreTagsItems: [
        'techno',
        'house',
        'acid',
        'rock',
        'punk',
        'hip hop',
        'classic',
        'contemporary',
        'new wave',
        'political',
      ],
      genreTags: [],
      //
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
        errors.push('Title must be at most 30 characters long')
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
    requestProvider.getEvent(eventId).then((response) => {
      this.title = response.data.title
      this.artists = response.data.artists
      this.info = response.data.info
      this.pic = response.data.pic
      this.price = response.data.price
      // ------------------------------------------
      this.fromDateVal = response.data.eventStart.split('T')[0]
      const fromTime = response.data.eventStart.split('T')[1]
      this.fromTimeVal = fromTime.substring(0, fromTime.length - 3)
      this.toDateVal = response.data.eventEnd.split('T')[0]
      const toTime = response.data.eventEnd.split('T')[1]
      this.toTimeVal = toTime.substring(0, toTime.length - 3)
      // ------------------------------------------
       this.whereTags = this.getOriginalWhereTags(response.data.tags)
       this.eventTypeTags = this.getOriginalEventTypeTags(response.data.tags)
       this.genreTags = this.getOriginalGenreTags(response.data.tags)
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
        return artistsArr1
      }
    },
    getOriginalWhereTags(originalTags) {
      originalTags.forEach((tag) => {
        if (this.whereTagsItems.includes(tag)) {
          this.whereTags.push(tag)
        }
      })
      return this.whereTagsItems
    },
    getOriginalEventTypeTags(originalTags) {
      originalTags.forEach((tag) => {
        if (this.eventTypeTagsItems.includes(tag)) {
          this.eventTypeTags.push(tag)
        }
      })
      return this.eventTypeTags
    },
    getOriginalGenreTags(originalTags) {
      originalTags.forEach((tag) => {
        if (this.genreTagsItems.includes(tag)) {
          this.genreTags.push(tag)
        }
      })
      return this.genreTags
    },
    async submit() {
      this.$v.$touch()
      this.pic = await fileUpload.handleUploadSubmit(this.file)
      requestProvider
        .updateEvent(
          this.id,
          {
            title: this.title,
            info: this.info,
            price: this.price,
            artists: this.convertArtistToArray(this.artists),
            tags: this.whereTags
              .concat(this.eventTypeTags)
              .concat(this.genreTags),
            eventStart: this.fromDateVal + 'T' + this.fromTimeVal + ':00',
            eventEnd: this.toDateVal + 'T' + this.toTimeVal + ':00',
            pic: this.pic,
          },
          { headers: { Authorization: 'Bearer ' + auth.state.token } }
        )
        .then(() => {
          this.$toast.open('Event updated!')
          this.dialog = false
          this.$router.go()
        })
        .catch((e) => {
          console.log(e)
        })
    },
  },
}
</script>

<style></style>
