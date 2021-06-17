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
                    :error-messages="requiredErrors"
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

                <v-col cols="12">
                  <v-checkbox
                    v-model="checkbox"
                    :error-messages="checkboxErrors"
                    label="ARE YOU SURE?"
                    required
                    @change="$v.checkbox.$touch()"
                    @blur="$v.checkbox.$touch()"
                  ></v-checkbox>
                </v-col>
              </v-row>
            </v-container>
            <v-card-actions>
              <v-btn class="mr-4" @click="submit"> submit </v-btn>
              <v-btn @click="clear"> clear </v-btn>
            </v-card-actions>
          </v-form>
        </div>
      </v-container>
    </template>
  </div>
</template>

<script>
import { mapGetters } from 'vuex'
import { validationMixin } from 'vuelidate'
import { required, maxLength } from 'vuelidate/lib/validators'
import axios from 'axios'
import auth from './../store/auth'

export default {
  mixins: [validationMixin],
  validations: {
    title: { required, maxLength: maxLength(20) },
    info: { required, maxLength: maxLength(500) },
    artists: { required },
    price: { required },
    fromDateVal: { required },
    toDateVal: { required },
    fromTimeVal: { required },
    toTimeVal: { required },

    checkbox: {
      checked(val) {
        return val
      },
    },
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

      checkbox: false,
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
    checkboxErrors() {
      const errors = []
      if (!this.$v.checkbox.$dirty) return errors
      !this.$v.checkbox.checked && errors.push('You must agree to continue!')
      return errors
    },
    titleErrors() {
      const errors = []
      if (!this.$v.title.$dirty) return errors
      !this.$v.title.maxLength &&
        errors.push('Title must be at most 20 characters long')
      !this.$v.title.required && errors.push('Title is required.')
      return errors
    },
    infoErrors() {
      const errors = []
      if (!this.$v.info.$dirty) return errors
      !this.$v.info.maxLength &&
        errors.push('Description must be at most 500 characters long')
      !this.$v.info.required && errors.push('Description is required.')
      return errors
    },
    requiredErrors() {
      const errors = []
      if (!this.$v.fromTimeVal.$dirty || !this.$v.fromTimeVal.$dirty)
        return errors
      !this.$v.toDateVal.required ||
        (!this.$v.fromDateVal.required && errors.push('Fiel is required.'))
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
        console.log('TEST2', artistsArr1)
        return artistsArr1
      }
    },
    submit() {
      this.$v.$touch()

      //  const eventstart = this.fromDateVal + 'T' + this.fromTimeVal + ':00'
      //  const eventend = this.toDateVal + 'T' + this.toTimeVal + ':00'
      console.log('-----------------------------')
      const userID = this.user.id
      console.log(userID)
      console.log('-----------------------------')

      //  console.log(eventstart)
      //  console.log(eventend)
      axios
        .post(
          '/events/',
          {
            venueId: this.user.id,
            title: this.title,
            info: this.info,
            artists: this.convertArtistToArray(this.artists),
            price: this.price,
            eventStart: this.fromDateVal + 'T' + this.fromTimeVal + ':00',
            eventEnd: this.toDateVal + 'T' + this.toTimeVal + ':00',
            pic: null,
          },
          { headers: { Authorization: 'Bearer ' + auth.state.token } }
        )
        .then((response) => {
          console.log(response.data)
        })
        .catch((e) => {
          //  this.e.push(e)
          console.log(e)
        })
    },

    clear() {
      this.$v.$reset()
      this.title = ''
      this.artists = ''
      this.price = ''
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
