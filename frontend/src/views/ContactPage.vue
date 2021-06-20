<template>
 
  <v-container class="rounded-xl mycontainer" fluid>
       <h1 class="h1Style text-center display-3 font-weight-medium">CONTACT US</h1>
    <div>
      <v-form>
        <v-row>
          <v-col cols="12" sm="6">
            <v-text-field 
              v-model="name"
              label="NAME"
          ></v-text-field>
          </v-col>
          <v-col cols="12" sm="6">
            <v-text-field 
              v-model="mail"
              label="MAIL"
            ></v-text-field>
          </v-col>
          <v-col cols="12">
            <v-textarea
              v-model="message"
            >
              <template v-slot:label>
                <div>MESSAGE</div>
              </template>
            </v-textarea>
          </v-col>
        </v-row>
         <v-btn
                width="100%"
                flat
                color="rounded-pill black white--text"
                >SUBMIT
              </v-btn>
      </v-form>
    </div>
  </v-container>
</template>

<script>
export default {


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