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
                <v-text-field type="text" v-model="userData.name">
                </v-text-field>
              </v-col>
              <v-col cols="12">
                <v-list-item-subtitle class="text-h6"
                  >WEBSITE</v-list-item-subtitle
                >
                <v-text-field type="text" v-model="userData.website">
                </v-text-field>
              </v-col>
              <v-col cols="12">
                <v-list-item-subtitle class="text-h6"
                  >DESCRIPTION</v-list-item-subtitle
                >
                <v-text-field type="text" v-model="userData.info">
                </v-text-field>
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
                      <v-text-field type="text" v-model="userData.address.city">
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
                      <v-text-field type="text" v-model="userData.address.plz">
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
                      <v-text-field type="text" v-model="userData.hours.monday">
                      </v-text-field>
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
        name: null,
        info: null,
        hours: {},
        website: null,
        address: {},
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
    this.userData.hours = this.user.hours
    this.userData.website = this.user.website
    this.userData.address = this.user.address
  },
  methods: {
    ...mapActions({
      updateVenue: 'auth/updateVenue',
    }),
    updateSubmit() {
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
