<template>
  <v-container class="rounded-xl mycontainer" fluid>
    <h1 class="h1Style text-center display-3 font-weight-medium">CONTACT US</h1>
    <div>
      <v-form @submit.prevent="sendEmail">
        <v-row>
          <v-col cols="12" sm="6">
            <v-text-field v-model="from_name" label="NAME"></v-text-field>
          </v-col>
          <v-col cols="12" sm="6">
            <v-text-field v-model="reply_to" label="MAIL"></v-text-field>
          </v-col>
          <v-col cols="12">
            <v-textarea v-model="message">
              <template v-slot:label>
                <div>MESSAGE</div>
              </template>
            </v-textarea>
          </v-col>
        </v-row>
        <input type="submit" value="Send" />
      </v-form>
    </div>
  </v-container>
</template>

<script>
import emailjs from 'emailjs-com'

export default {
  data() {
    return {
      from_name: '',
      mail: '',
      message: '',
    }
  },

  methods: {
    sendEmail(e) {
      emailjs
        .sendForm(
          'service_3jepy7a',
          'template_69fu98a',
          e.target,
          'user_i2XDe74FgVZ1VEc62xset'
        )
        .then(
          (result) => {
            console.log('SUCCESS!', result.status, result.text)
          },
          (error) => {
            console.log('FAILED...', error)
          }
        )
    },
  },
}
</script>

<style>
.h1Style {
  color: #cafb03;
  text-shadow: 1px 1px 1px black, 1px -1px 1px black, -1px 1px 1px black,
    -1px -1px 1px black;
  margin-bottom: 10vh;
}
</style>
