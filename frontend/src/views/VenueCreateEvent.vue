<template>
  <div>
    <template v-if="user != null">
      <v-container>
        <div class="rounded-xl mycontainer" fluid>
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
                    :error-messages="artistErrors"
                    @blur="$v.artists.$touch()"
                    @input="$v.artists.$touch()"
                  ></v-text-field>
                </v-col>
                <v-col cols="12" sm="6">
                  <v-text-field
                    v-model="price"
                    label="PRICE"
                    :error-messages="priceErrors"
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
                    :counter="1500"
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
            <v-card-actions>
              <v-btn
                outlined
                rounded
                class="mr-4"
                @click="submit"
                :disabled="$v.$invalid"
              >
                submit
              </v-btn>
              <v-btn outlined rounded @click="clear"> clear </v-btn>
            </v-card-actions>
          </v-form>
        </div>
      </v-container>
    </template>
  </div>
</template>

<script>
import { mapGetters } from 'vuex'

import {
  required,
  maxLength,
  numeric,
  minLength,
  between,
} from 'vuelidate/lib/validators'
import auth from './../store/auth'
import * as fileUpload from '../util/FileUpload'
import { requestProvider } from '../util/requestProvider'

export default {
  validations: {
    title: { required, maxLength: maxLength(20) },
    info: { required, maxLength: maxLength(1500), minLength: minLength(100) },
    price: { required, numeric, between: between(0, 100) },
    //  pic:{},
    artists: { required },
    fromDateMenu: { required },
    fromDateVal: { required },
    toDateMenu: { required },
    toDateVal: { required },
    fromTimeVal: { required },
    toTimeVal: { required },
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
      pic: '',
      file: [],
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
      styleObject: { border: '2px solid #cafb03' },
    }
  },
  computed: {
    ...mapGetters({
      user: 'auth/user',
    }),
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
        errors.push('Title must have at most 20 letters')
      !this.$v.title.required && errors.push('Title is required.')

      return errors
    },
    infoErrors() {
      const errors = []
      if (!this.$v.info.$dirty) return errors
      !this.$v.info.maxLength &&
        errors.push('Description must have at most 1500 letters. ')
      !this.$v.info.minLength &&
        errors.push('Tell your guests a bit more about your event ;]')
      !this.$v.info.required && errors.push('Description is required.')
      return errors
    },
    priceErrors() {
      const errors = []
      if (!this.$v.price.$dirty) return errors
      !this.$v.price.between &&
        errors.push('That is pretty expensive, stay fair!')
      !this.$v.price.numeric && errors.push('Price should be a number')
      !this.$v.price.required && errors.push('Price is required.')
      return errors
    },
    artistErrors() {
      const errors = []
      if (!this.$v.artists.$dirty) return errors
      !this.$v.artists.required && errors.push('Artists are required.')
      return errors
    },
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
    async submit() {
      this.$v.$touch()
      this.pic = await fileUpload.handleUploadSubmit(this.file)
      requestProvider
        .postEvent(
          {
            venueId: this.user.id,
            title: this.title,
            info: this.info,
            artists: this.convertArtistToArray(this.artists),
            tags: this.whereTags
              .concat(this.eventTypeTags)
              .concat(this.genreTags), 
            price: this.price,
            eventStart: this.fromDateVal + 'T' + this.fromTimeVal + ':00',
            eventEnd: this.toDateVal + 'T' + this.toTimeVal + ':00',
            pic: this.pic,
          },
          { headers: { Authorization: 'Bearer ' + auth.state.token } }
        )
        .then(() => {
          this.clear()
          this.$toast.open('Your Event was created!')
          // TODO: change to my events
          location.replace('/events')
        })
        .catch((e) => {
          console.log(e)
        })
    },

    clear() {
      this.$v.$reset()
      this.title = ''
      this.artists = ''
      this.price = ''
      this.tags = null
      this.info = null
      this.fromDateVal = null
      this.toDateVal = null
      this.fromTimeVal = null
      this.toTimeVal = null
      this.checkbox = false
    },
  },
}
</script>

<style></style>
