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
        </div>
      </v-container>
    </template>
  </div>
</template>

<script>
import { mapGetters } from 'vuex'

export default {
  data() {
    return {
      styleObject: { border: '2px solid #cafb03' },
      fromDateVal: null,

      minDate: '2019-07-04',
      maxDate: '2019-08-30',
    }
  },
  computed: {
    ...mapGetters({
      user: 'auth/user',
    }),
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
