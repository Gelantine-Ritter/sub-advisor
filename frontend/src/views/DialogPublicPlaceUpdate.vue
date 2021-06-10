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
              <v-col cols="12" sm="6" md="4">
                <v-list-item-subtitle>PLACE</v-list-item-subtitle>

                <v-text-field
                  ><template v-slot:label>
                    <div>
                      {{ user.name }}
                    </div>
                  </template></v-text-field
                >
              </v-col>
              <v-col cols="12">
                <v-list-item-subtitle>DESCRIPTION</v-list-item-subtitle>

                <v-text-field
                  ><template v-slot:label>
                    <div>
                      {{ user.info }}
                    </div>
                  </template></v-text-field
                >
              </v-col>
              <v-col cols="12" sm="6" md="4">
                <v-list-item-subtitle>OPENING HOURS</v-list-item-subtitle>

                <v-text-field
                  ><template v-slot:label>
                    <div>
                      {{ user.hours }}
                    </div>
                  </template></v-text-field
                >
              </v-col>
              <v-col cols="12" sm="6" md="4">
                <v-list-item-subtitle>ADDRESS</v-list-item-subtitle>

                <v-text-field
                  ><template v-slot:label>
                    <div>
                      {{ user.address }}
                    </div>
                  </template></v-text-field
                >
              </v-col>
            </v-row>
          </v-container>
        </v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn elevation="2" outlined rounded text @click="dialog = false">
            Close
          </v-btn>
          <v-btn elevation="2" outlined rounded text @click="dialog = false">
            Save
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </v-row>
</template>

<script>
import { mapGetters } from 'vuex'
export default {
  data() {
    return {}
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
        this.$emit('input', value)
      },
    },
  },
  methods: {
  },
}
</script>
