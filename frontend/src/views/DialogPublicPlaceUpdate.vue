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
              <v-col cols="12" sm="6" md="6">
                <v-list-item-subtitle class="text-h6"
                  >PLACE</v-list-item-subtitle
                >
                <v-text-field
                  type="text"
                  v-model="userData.name"
                  :error-messages="nameErrors"
                  @input="$v.userData.name.$touch()"
                  @blur="$v.userData.name.$touch()"
                >
                </v-text-field>
              </v-col>
              <v-col cols="12">
                <v-list-item-subtitle class="text-h6"
                  >WEBSITE</v-list-item-subtitle
                >
                <v-text-field
                  type="text"
                  v-model="userData.website"
                  :error-messages="websiteErrors"
                  @input="$v.userData.website.$touch()"
                  @blur="$v.userData.website.$touch()"
                >
                </v-text-field>
              </v-col>
              <v-col cols="12">
                <v-textarea
                  type="text"
                  v-model="userData.info"
                  :error-messages="infoErrors"
                  @input="$v.userData.info.$touch()"
                  @blur="$v.userData.info.$touch()"
                >
                  <template v-slot:label>
                    <div>DESCRIPTION</div>
                  </template>
                </v-textarea>
              </v-col>
              <v-col cols="12" sm="7" md="7">
                <v-list-item-subtitle class="text-h6"
                  >ADDRESS</v-list-item-subtitle
                >
                <v-list-item-title>
                  <v-row>
                    <v-col cols="12" xs="4" sm="3" md="3" lg="3" xl="3"
                      >STREET:</v-col
                    >
                    <v-col cols="12" xs="8" sm="9" md="9" lg="9" xl="9">
                      <v-text-field
                        type="text"
                        v-model="userData.address.street"
                        :error-messages="streetErrors"
                        @input="$v.userData.address.street.$touch()"
                        @blur="$v.userData.address.street.$touch()"
                      >
                      </v-text-field>
                    </v-col>
                  </v-row>
                </v-list-item-title>
                <v-list-item-title>
                  <v-row>
                    <v-col cols="12" xs="4" sm="3" md="3" lg="3" xl="3"
                      >NUMBER:</v-col
                    >
                    <v-col cols="12" xs="8" sm="9" md="9" lg="9" xl="9">
                      <v-text-field
                        type="text"
                        v-model="userData.address.number"
                        :error-messages="numberErrors"
                        @input="$v.userData.address.number.$touch()"
                        @blur="$v.userData.address.number.$touch()"
                      >
                      </v-text-field>
                    </v-col>
                  </v-row>
                </v-list-item-title>
                <v-list-item-title>
                  <v-row>
                    <v-col cols="12" xs="4" sm="3" md="3" lg="3" xl="3"
                      >CITY:</v-col
                    >
                    <v-col cols="12" xs="8" sm="9" md="9" lg="9" xl="9">
                      <v-text-field
                        type="text"
                        v-model="userData.address.city"
                        :error-messages="cityErrors"
                        @input="$v.userData.address.city.$touch()"
                        @blur="$v.userData.address.city.$touch()"
                      >
                      </v-text-field>
                    </v-col>
                  </v-row>
                </v-list-item-title>
                <v-list-item-title>
                  <v-row>
                    <v-col cols="12" xs="4" sm="3" md="3" lg="3" xl="3"
                      >PLZ:</v-col
                    >
                    <v-col cols="12" xs="8" sm="9" md="9" lg="9" xl="9">
                      <v-text-field
                        type="text"
                        v-model="userData.address.plz"
                        :error-messages="plzErrors"
                        @input="$v.userData.address.plz.$touch()"
                        @blur="$v.userData.address.plz.$touch()"
                      >
                      </v-text-field>
                    </v-col>
                  </v-row>
                </v-list-item-title>
              </v-col>
              <v-col cols="12" sm="5" md="5">
                <v-list-item-subtitle class="text-h6"
                  >OPENING HOURS</v-list-item-subtitle
                >
                <v-list-item-title>
                  <v-row>
                    <v-col cols="6">MONDAY:</v-col>
                    <v-col cols="6">
                      <v-text-field type="text"> </v-text-field>
                    </v-col>
                  </v-row>
                </v-list-item-title>
                <v-list-item-title>
                  <v-row>
                    <v-col cols="6">TUESDAY:</v-col>
                    <v-col cols="6">
                      <v-text-field
                        type="text"
                        v-model="userData.hours.tuesday"
                      >
                      </v-text-field>
                    </v-col>
                  </v-row>
                </v-list-item-title>
                <v-list-item-title>
                  <v-row>
                    <v-col cols="6">WEDNESDAY:</v-col>
                    <v-col cols="6">
                      <v-text-field
                        type="text"
                        v-model="userData.hours.wednesday"
                      >
                      </v-text-field>
                    </v-col>
                  </v-row>
                </v-list-item-title>
                <v-list-item-title>
                  <v-row>
                    <v-col cols="6">THURSDAY:</v-col>
                    <v-col cols="6">
                      <v-text-field
                        type="text"
                        v-model="userData.hours.thursday"
                      >
                      </v-text-field>
                    </v-col>
                  </v-row>
                </v-list-item-title>
                <v-list-item-title>
                  <v-row>
                    <v-col cols="6">FRIDAY:</v-col>
                    <v-col cols="6">
                      <v-text-field type="text" v-model="userData.hours.friday">
                      </v-text-field>
                    </v-col>
                  </v-row>
                </v-list-item-title>
                <v-list-item-title>
                  <v-row>
                    <v-col cols="6">SATURDAY:</v-col>
                    <v-col cols="6">
                      <v-text-field
                        type="text"
                        v-model="userData.hours.saturday"
                      >
                      </v-text-field>
                    </v-col>
                  </v-row>
                </v-list-item-title>
                <v-list-item-title>
                  <v-row>
                    <v-col cols="6">SUNDAY:</v-col>
                    <v-col cols="6">
                      <v-text-field type="text" v-model="userData.hours.sunday">
                      </v-text-field>
                    </v-col>
                  </v-row>
                </v-list-item-title>
              </v-col>
            </v-row>
          </v-container>
        </v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn elevation="1" outlined rounded text @click="dialog = false">
            Cancel
          </v-btn>
          <v-btn
            elevation="1"
            outlined
            rounded
            text
            :disabled="$v.$invalid"
            @click="updateSubmit"
          >
            Save
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </v-row>
</template>

<script>
import {
  required,
  minLength,
  maxLength,
  url,
  numeric,
  between,
  alpha,
} from 'vuelidate/lib/validators'

import { mapGetters, mapActions } from 'vuex'
export default {
  data() {
    return {
      userData: {
        password: null,
        name: null,
        info: null,
        hours: {},
        website: null,
        address: {},
      },
    }
  },
  validations: {
    userData: {
      name: { required, minLength: minLength(2) },
      website: { required, url },
      info: {
        required,
        maxLength: maxLength(500),
        alpha,
        minLength: minLength(200),
      },
      address: {
        street: {
          required,
          minLength: minLength(4),
          maxLength: maxLength(30),
          alpha,
        },
        number: { required, numeric, between: between(1, 300) },
        city: {
          required,
          maxLength: maxLength(58),
          minLength: minLength(3),
          alpha,
        },
        plz: { required, numeric, between: between(10000, 99999) },
      },
    },
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
    nameErrors() {
      const errors = []
      if (!this.$v.userData.name.$dirty) return errors
      !this.$v.userData.name.required && errors.push('Name is required.')
      !this.$v.userData.name.minLength &&
        errors.push('Name must have at least 2 letters ')
      return errors
    },
    websiteErrors() {
      const errors = []
      if (!this.$v.userData.name.$dirty) return errors
      !this.$v.userData.website.url &&
        errors.push('Website must be a valid URL, e.g. https://mysite.com')
      return errors
    },
    infoErrors() {
      const errors = []
      if (!this.$v.userData.info.$dirty) return errors
      !this.$v.userData.info.maxLength &&
        errors.push('Description must have at most 500 letters ')
      !this.$v.userData.info.required && errors.push('Description is required.')
      !this.$v.userData.info.minLength &&
        errors.push('Tell your guests a little bit more about your place ;]')

      return errors
    },
    numberErrors() {
      const errors = []
      if (!this.$v.userData.address.number.$dirty) return errors
      !this.$v.userData.address.number.numeric &&
        errors.push('Number should be a number - obviously')
      !this.$v.userData.address.number.required &&
        errors.push('Number is required.')
      !this.$v.userData.address.number.between &&
        errors.push('Not a real house number ....')
      return errors
    },
    streetErrors() {
      const errors = []
      if (!this.$v.userData.address.street.$dirty) return errors
      !this.$v.userData.address.street.maxLength &&
        errors.push('Streetname must have at most 30 letters ')
      !this.$v.userData.address.street.minLength &&
        errors.push('Streetname must have at least 4 letters ')
      !this.$v.userData.address.street.required &&
        errors.push('Street is required.')
      !this.$v.userData.address.city.alpha && errors.push('No numbers allowed')
      return errors
    },
    cityErrors() {
      const errors = []
      if (!this.$v.userData.address.city.$dirty) return errors
      !this.$v.userData.address.city.maxLength &&
        errors.push(
          'There is no city name longer then Llanfairpwllgwyngyllgogerychwyrndrobwllllantysiliogogogoch'
        )
      !this.$v.userData.address.city.minLength &&
        errors.push('City must have at least 4 letters.')
      !this.$v.userData.address.city.required &&
        errors.push('City is required.')
      !this.$v.userData.address.city.alpha && errors.push('No numbers allowed')
      return errors
    },
    plzErrors() {
      const errors = []
      if (!this.$v.userData.address.plz.$dirty) return errors
      !this.$v.userData.address.plz.between &&
        errors.push('This is not a real PLZ')
      !this.$v.userData.address.plz.numeric &&
        errors.push('PLZ should be a number')
      !this.$v.userData.address.plz.required && errors.push('PLZ is required.')
      return errors
    },
  },
  mounted: function () {
    this.userData.name = this.user.name
    this.userData.info = this.user.info
    this.userData.hours = this.user.hours
    this.userData.website = this.user.website
    this.userData.address = this.user.address
  },
  methods: {
    ...mapActions({
      updateVenue: 'auth/updateVenue',
    }),
    updateSubmit() {
      this.$v.$touch()
      this.updateVenue(this.userData).then(() => {
        this.$toast.open('Your public page has been updated!')
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
