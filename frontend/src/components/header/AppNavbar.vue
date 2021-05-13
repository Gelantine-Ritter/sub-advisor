<template>
  <v-toolbar flat class="border-bottom border-dark">
    <!-- left aligned items-->
    <v-menu offset-y>
      <template v-slot:activator="{ on, attrs }">
        <v-btn
          class="ml-5 rounded-pill border border-dark"
          text
          v-bind="attrs"
          v-on="on"
        >
          Event
        </v-btn>
      </template>
      <v-list>
        <v-list-item>
          <v-list-item-title>Test1</v-list-item-title>
        </v-list-item>
      </v-list>
    </v-menu>

    <v-menu offset-y>
      <template v-slot:activator="{ on, attrs }">
        <v-btn
          class="ml-5 rounded-pill border border-dark"
          text
          v-bind="attrs"
          v-on="on"
        >
          Places
        </v-btn>
      </template>
      <v-list>
        <v-list-item>
          <v-list-item-title>Test1</v-list-item-title>
        </v-list-item>
      </v-list>
    </v-menu>

    <!-- left aligned items-->
    <v-spacer></v-spacer>
    <v-toolbar-title class="white--text"
      ><img src="../../assets/logo.png" width="360px" alt="subadvisor logo"
    /></v-toolbar-title>
    <v-spacer></v-spacer>

    <!-- right aligned items-->
    <v-menu offset-y>
      <template v-slot:activator="{ on, attrs }">
        <v-btn
          class="ml-5 rounded-pill border border-dark"
          text
          v-bind="attrs"
          v-on="on"
        >
          Contact
        </v-btn>
      </template>
      <v-list>
        <v-list-item>
          <v-list-item-title>Test1</v-list-item-title>
        </v-list-item>
      </v-list>
    </v-menu>

    <v-menu offset-y>
      <template v-slot:activator="{ on, attrs }">
        <v-btn
          class="ml-5 rounded-pill border border-dark"
          text
          v-bind="attrs"
          v-on="on"
        >
          About
        </v-btn>
      </template>
      <v-list>
        <v-list-item>
          <v-list-item-title>Test1</v-list-item-title>
        </v-list-item>
      </v-list>
    </v-menu>

    <!-- right aligned items-->
    <v-menu offset-y>
      <template v-slot:activator="{ on, attrs }">
        <v-btn icon class="ml-5" v-bind="attrs" v-on="on">
          <v-icon large class="ml-5 d-none d-sm-flex"> account_circle </v-icon>
        </v-btn>
      </template>
      <v-list>
        <v-list-item>
          <v-list-item-title>Test1</v-list-item-title>
        </v-list-item>
      </v-list>
    </v-menu>
  </v-toolbar>
</template>

<script>
export default {
  name: 'Navbar',
  data() {
    return {}
  },
}
</script>

<style>
i.v-icon.v-icon {
  color: black;
}
</style>
