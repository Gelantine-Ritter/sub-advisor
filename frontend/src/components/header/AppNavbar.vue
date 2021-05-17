<template>
  <v-toolbar flat class="border-bottom border-dark">
    <!-- navigation drawer-->
    <v-app-bar-nav-icon
      @click.stop="sideNav = !sideNav"
      class="d-flex d-sm-none"
    ></v-app-bar-nav-icon>
    <v-navigation-drawer v-model="sideNav" absolute side temporary app>
      <v-list-item>
        <v-list-item-content>
          <v-list-item-title class="title"> subadvisor </v-list-item-title>
        </v-list-item-content>
      </v-list-item>
      <v-divider></v-divider>
      <v-list dense nav>
        <v-list-item v-for="item in menuItems" :key="item.title" link>
          <v-list-item-icon>
            <v-icon>{{ item.icon }}</v-icon>
          </v-list-item-icon>
          <v-list-item-content>
            <v-list-item-title>{{ item.title }}</v-list-item-title>
          </v-list-item-content>
        </v-list-item>
      </v-list>
    </v-navigation-drawer>
    <!-- navigation drawer-->
    <!-- left aligned items-->
    <!--EVENT-->
    <v-menu offset-y>
      <template v-slot:activator="{ on, attrs }">
        <v-btn
          class="d-none d-sm-flex ml-5 rounded-pill border border-dark"
          text
          v-bind="attrs"
          v-on="on"
        >
          Event
        </v-btn>
      </template>
      <v-list>
        <v-list-item
          v-for="item in subEvents"
          :key="item.title"
          :to="item.route"
        >
          <v-list-item-title>{{ item.title }}</v-list-item-title>
        </v-list-item>
      </v-list>
    </v-menu>
    <!--PLACES-->
    <v-menu offset-y>
      <template v-slot:activator="{ on, attrs }">
        <v-btn
          class="d-none d-sm-flex ml-5 rounded-pill border border-dark"
          text
          v-bind="attrs"
          v-on="on"
        >
          Places
        </v-btn>
      </template>
      <v-list>
        <v-list-item
          v-for="item in subPlaces"
          :key="item.title"
          :to="item.route"
        >
          <v-list-item-title>{{ item.title }}</v-list-item-title>
        </v-list-item>
      </v-list>
    </v-menu>
    <!-- left aligned items-->
    <v-spacer></v-spacer>
    <v-toolbar-title class="d-none d-sm-flex white--text"
      ><img src="../../assets/logo.png" width="360px" alt="subadvisor logo"
    /></v-toolbar-title>
    <v-spacer></v-spacer>
    <!-- right aligned items-->
    <!--CONTACT-->
    <v-menu offset-y>
      <template v-slot:activator="{ on, attrs }">
        <v-btn
          class="d-none d-sm-flex ml-5 rounded-pill border border-dark"
          text
          v-bind="attrs"
          v-on="on"
        >
          Contact
        </v-btn>
      </template>
      <v-list>
        <v-list-item
          v-for="item in subContact"
          :key="item.title"
          :to="item.route"
        >
          <v-list-item-title>{{ item.title }}</v-list-item-title>
        </v-list-item>
      </v-list>
    </v-menu>
    <!--ABOUT-->
    <v-menu offset-y>
      <template v-slot:activator="{ on, attrs }">
        <v-btn
          class="d-none d-sm-flex ml-5 rounded-pill border border-dark"
          text
          v-bind="attrs"
          v-on="on"
        >
          About
        </v-btn>
      </template>
      <v-list>
        <v-list-item
          link
          v-for="item in subAbout"
          :key="item.title"
          :to="item.route"
        >
          <v-list-item-title>{{ item.title }}</v-list-item-title>
        </v-list-item>
      </v-list>
    </v-menu>
    <!--PROFILE-->
    <v-menu offset-y class="d-none d-sm-flex">
      <template v-slot:activator="{ on, attrs }">
        <v-btn icon class="ml-5" v-bind="attrs" v-on="on">
          <v-icon large class="ml-5 d-none d-sm-flex"> account_circle </v-icon>
        </v-btn>
      </template>
      <v-list>
        <v-list-item
          v-for="item in subProfile"
          :key="item.title"
          :to="item.route"
        >
          <v-list-item-title>{{ item.title }}</v-list-item-title>
        </v-list-item>
      </v-list>
    </v-menu>
    <!-- right aligned items-->
  </v-toolbar>
</template>

<script>
export default {
  name: 'Navbar',
  data() {
    return {
      sideNav: false,
      menuItems: [
        { icon: 'account_circle', title: 'My Profile' },
        { icon: 'celebration', title: 'Events' },
        { icon: 'place', title: 'Places' },
        { icon: 'contact_support', title: 'Contact' },
        { icon: 'groups', title: 'About' },
      ],
      subEvents: [
        { title: 'route to event', route: '/events' },
        { title: 'Event 2', route: '/events' },
      ],
      subPlaces: [
        { title: 'Place 1', route: '/places' },
        { title: 'Place 2', route: '/places' },
      ],
      subContact: [
        { title: 'Contact 1', route: '/contact' },
        { title: 'Contact 2', route: '/contact' },
      ],
      subAbout: [
        { title: 'About 1', route: '/about' },
        { title: 'route to about', route: '/about' },
      ],
      subProfile: [
        { title: 'Profile 1' },
        { title: 'Profile 2', route: '/profile' },
      ],
    }
  },
}
</script>

<style>
i.v-icon.v-icon {
  color: black;
}
</style>
