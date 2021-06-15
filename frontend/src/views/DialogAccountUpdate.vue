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
              <v-col cols="12">
                <v-list-item-subtitle class="text-h6"
                  >USERNAME</v-list-item-subtitle
                >

                <v-text-field type="text" v-model="userData.username">
                </v-text-field>
              </v-col>
              <v-col cols="12" sm="6" md="6">
                <v-list-item-subtitle class="text-h6"
                  >E-MAIL</v-list-item-subtitle
                >

                <v-text-field type="text" v-model="userData.email">
                </v-text-field>
              </v-col>
              <v-col cols="12" sm="6" md="6">
                <v-list-item-subtitle class="text-h6"
                  >MOBILE</v-list-item-subtitle
                >
                <v-text-field type="text" v-model="userData.mobile">
                </v-text-field>
              </v-col>
              <v-col cols="12">
                <v-list-item-subtitle class="text-h6"
                  >WANT TO CHANGE YOUR PASSWORD?</v-list-item-subtitle
                >
                <v-text-field type="password" v-model="userData.password" :rules="passwordRules">
                </v-text-field>
              </v-col>
              
              <v-col cols="12">
                <v-list-item-subtitle class="text-h6">CONFIRM PASSWORD</v-list-item-subtitle>
                <v-text-field type="password" v-model="confirm_password">
                </v-text-field> 
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
        username: null,
        password: null,
        email: null,
        mobile: null,
      },
      confirm_password: null,
      passwordRules: [
        (v) => !v || v.length >= 8 || 'Min 8 characters',
        // (v) => (v && !!v.trim()) || 'Seriously? just a blank password?',
      ],
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
    this.userData.username = this.user.username
    this.userData.email = this.user.email
    this.userData.mobile = this.user.mobile
  },

  methods: {
    ...mapActions({
      updateVenue: 'auth/updateVenue',
    }),
    updateSubmit() {
      if (this.userData.password!=null && this.userData.password.length <=8){
        this.$toast.open('Passwords must hast minimum 8 characters!')
        this.dialog = true
      }
      else if(this.confirm_password!==this.userData.password){
        this.$toast.open('Passwords do not match!')
        this.dialog = true
      } else {
        this.updateVenue(this.userData).then(() => {
        this.$toast.open('Your data have been updated!')
        this.dialog = false
        })
      }
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
