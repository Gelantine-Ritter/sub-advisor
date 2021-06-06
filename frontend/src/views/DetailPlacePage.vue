<template>
  <v-container>
    <h1 class="h1Style text-center display-3 font-weight-medium">
      {{ venueObj.name }}
    </h1>
    <v-card center class="responsive rounded-xl text-center ma-7" :style="styleObject">
     
      <v-row no-gutters>
        <v-col cols="12" sm="6" md="8">
          <div class="responsive pa-md-4 mx-lg-auto text-left ">
            {{venueObj.email}}
          </div>
        </v-col>
        <v-col cols="6" md="4">
          <div class="pa-2" outlined tile>  </div>
        </v-col>
      </v-row>
      <v-row no-gutters>
        <v-col cols="12" sm="6" md="8">
          <div class="responsive pa-md-4 mx-lg-auto text-left ">
            {{ venueObj.info }}
          </div>
        </v-col>
        <v-col cols="6" md="4">
          <div class="pa-2" outlined tile>
            <v-img
              class="responsive ma-5 border border-dark"
              height="250"
              src="../../public/venue2.png"
              alt=""
            />
          </div>
        </v-col>
      </v-row>

      <v-card-actions> </v-card-actions>
    </v-card>
  </v-container>
</template>

<script>
export default {
  data() {
    return {
      styleObject: { border: '2px solid #000000' },
      venueObj: {
        name: 'SampleName',
        email: 'SampleMail',
        info: getLorem,
      },
    }
  },
}

const getLorem = (words) => {
    return (
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum."
    )
}
</script>

<style>
.h1Style {
  color: #cafb03;
  text-shadow: 1px 1px 1px black, 1px -1px 1px black, -1px 1px 1px black,
    -1px -1px 1px black;
}
</style>