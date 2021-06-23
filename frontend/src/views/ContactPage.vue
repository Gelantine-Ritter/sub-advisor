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
import { required, minLength, email } from 'vuelidate/lib/validators'

export default {
  data() {
    return {
      from_name: '',
      reply_to: '',
      message: '',
    }
  },
  validations: {
    from_name: {
      required,
      minLength: minLength(3),
    },
    reply_to: {
      required,
      email,
    },
    message: {
      required,
      minLength: minLength(50),
    },
  },

  computed: {
    emailErrors() {
      const errors = []
      if (!this.$v.reply_to.$dirty) return errors
      !this.$v.reply_to.email && errors.push('Must be valid e-mail')
      !this.$v.reply_to.required && errors.push('E-mail is required')
      return errors
    },
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
