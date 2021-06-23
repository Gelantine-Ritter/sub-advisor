<template>
  <v-container class="rounded-xl mycontainer" fluid>
    <h1 class="marginHeading h1Style text-center display-3 font-weight-medium">
      CONTACT US
    </h1>
    <div>
      <form ref="contactform" @submit.prevent="sendEmail">
        <v-row>
          <v-col cols="12" sm="6">
            <v-text-field
              name="from_name"
              v-model="from_name"
              :error-messages="nameErrors"
              @input="$v.from_name.$touch()"
              @blur="$v.from_name.$touch()"
              label="NAME"
            ></v-text-field>
          </v-col>
          <v-col cols="12" sm="6">
            <v-text-field
              v-model="reply_to"
              name="reply_to"
              :error-messages="emailErrors"
              @input="$v.reply_to.$touch()"
              @blur="$v.reply_to.$touch()"
              label="MAIL"
            ></v-text-field>
          </v-col>
          <v-col cols="12">
            <v-textarea
              v-model="message"
              name="message"
              :error-messages="messageErrors"
              @input="$v.message.$touch()"
              @blur="$v.message.$touch()"
            >
              <template v-slot:label>
                <div>MESSAGE</div>
              </template>
            </v-textarea>
          </v-col>
        </v-row>
        <v-btn
          type="submit"
          outlined
          rounded
          class="mr-4"
          :disabled="$v.$invalid"
        >
          submit
        </v-btn>
      </form>
    </div>
  </v-container>
</template>

<script>
import emailjs from 'emailjs-com'
import { validationMixin } from 'vuelidate'
import { required, minLength, email } from 'vuelidate/lib/validators'

export default {
  data() {
    return {
      from_name: '',
      reply_to: '',
      message: '',
    }
  },

  mixins: [validationMixin],
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
    nameErrors() {
      const errors = []
      if (!this.$v.from_name.$dirty) return errors
      !this.$v.from_name.minLength &&
        errors.push('Name must have at least 3 letters')
      !this.$v.from_name.required && errors.push('Name is required.')
      return errors
    },
    emailErrors() {
      const errors = []
      if (!this.$v.reply_to.$dirty) return errors
      !this.$v.reply_to.email && errors.push('Must be valid e-mail')
      !this.$v.reply_to.required && errors.push('E-mail is required')
      return errors
    },
    messageErrors() {
      const errors = []
      if (!this.$v.message.$dirty) return errors
      !this.$v.message.minLength &&
        errors.push('The message must be at least 50 characters long.')
      !this.$v.message.required && errors.push('Message is required.')
      return errors
    },
  },

  methods: {
    sendEmail: (e) => {
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
            location.reload()
          },
          (error) => {
            console.log('FAILED...', error)
          }
        )
    },
  },
}
</script>

<style scoped>
.marginHeading {
  margin-bottom: 10vh;
}
</style>
