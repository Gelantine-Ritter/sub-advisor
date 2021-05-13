<template>
  <nav>
    <v-navigation-drawer v-model="sideNav" absolute bottom temporary>
      <v-list>
        <v-list-item v-for="item in menuItems" :key="item.title">
          <v-list-item-action>
            <v-icon>{{ item.icon }}</v-icon>
          </v-list-item-action>
          <v-list-item-content> {{ item.title }} </v-list-item-content>
          <v-spacer></v-spacer>
        </v-list-item>
      </v-list>
    </v-navigation-drawer>
    <v-toolbar flat class="border-bottom border-dark">
      <!-- left aligned items-->
      <v-app-bar-nav-icon
        @click.stop="sideNav = !sideNav"
        class="d-flex d-sm-none"
      ></v-app-bar-nav-icon>
      <v-toolbar-item class="d-none d-sm-flex">
        <v-btn class="ml-5 rounded-pill border border-dark" text>
          Events
        </v-btn>

        <v-btn class="ml-5 rounded-pill border border-dark" text>
          Places
        </v-btn></v-toolbar-item
      >

      <!-- left aligned items-->
      <v-spacer></v-spacer>
      <v-toolbar-title class="white--text"
        ><img src="../../assets/logo.png" width="360px" alt="subadvisor logo"
      /></v-toolbar-title>
      <v-spacer></v-spacer>
      <!-- right aligned items-->
      <v-toolbar-item class="d-none d-sm-flex">
        <v-btn class="mx-4 rounded-pill border border-dark" text>
          Contact
        </v-btn>
        <v-btn class="mx-4 rounded-pill border border-dark" text> About </v-btn>

        <v-icon large class="ml-5 d-none d-sm-flex"> account_circle </v-icon>
      </v-toolbar-item>

      <!-- right aligned items-->
    </v-toolbar>
    <!-- TEST DROPDOWN -->
    <div id="app">
      <v-app id="inspire">
        <div class="text-center">
          <v-menu offset-y>
            <template v-slot:activator="{ on, attrs }">
              <v-btn
                class="mx-4 rounded-pill border border-dark"
                text
                v-bind="attrs"
                v-on="on"
              >
                Events
              </v-btn>
            </template>
            <v-list>
              <v-list-item v-for="(item, index) in subsEvents" :key="index">
                <v-list-item-title>{{ item.title }}</v-list-item-title>
              </v-list-item>
            </v-list>
          </v-menu>
        </div>
      </v-app>
    </div>
  </nav>
</template>

<script>
export default {
  name: 'Navbar',
  data() {
    return {
      sideNav: false,
      menuItems: [
        { icon: 'account_circle', title: 'My Profile' },
        { title: 'Events' },
        { title: 'Places' },
        { title: 'About' },
        { title: 'Contact' },
      ],
      subsEvents: [{ title: 'Test1' }, { title: 'Test2' }],
    }
  },
}
</script>

<style>
i.v-icon.v-icon {
  color: black;
}
</style>
