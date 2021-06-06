<template>
  <v-container>
    <div class="rounded-xl mycontainer" fluid>
      <h4>GENERAL <v-icon dense color="grey">far fa-edit</v-icon></h4>

      <v-list-item two-line>
        <v-list-item-content>
          <v-list-item-subtitle> PLACE </v-list-item-subtitle>
          <v-list-item-title> {{ auth.user.name }} </v-list-item-title>
        </v-list-item-content>
      </v-list-item>
      <v-list-item two-line>
        <v-list-item-content>
          <v-list-item-subtitle>USERNAME</v-list-item-subtitle>
          <v-list-item-title> ..... </v-list-item-title>
        </v-list-item-content>
      </v-list-item>
      <v-list-item two-line>
        <v-list-item-content>
          <v-list-item-subtitle>MAIL</v-list-item-subtitle>
          <v-list-item-title> {{ auth.user.email }} </v-list-item-title>
        </v-list-item-content>
      </v-list-item>
      <v-divider></v-divider>

      <v-list-item two-line>
        <v-list-item-content>
          <v-list-item-subtitle>DESCRIPTION</v-list-item-subtitle>
          <v-list-item-title> {{ auth.user.info }} </v-list-item-title>
        </v-list-item-content>
      </v-list-item>
      <span>edit</span>
    </div>
  </v-container>
</template>

<script>
import { mapState } from 'vuex'
export default {
  computed: {
    ...mapState(['auth']),
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
  padding: 5vw;
}
</style>
