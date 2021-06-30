<template>
  <v-toolbar elevation="0" class="border-bottom border-dark">
    <!-- MOBILE: NAVIGATION DRAWER START-->
    <v-app-bar-nav-icon
      @click.stop="sideNav = !sideNav"
      class="d-flex d-sm-none ml-5"
    ></v-app-bar-nav-icon>
    <v-navigation-drawer
      v-model="sideNav"
      absolute
      side
      temporary
      app
      class="myNavDrawer"
    >
      <v-list-item>
        <v-list-item-content>
          <v-list-item-title class="title"
            ><img
              src="../../assets/logo_small.png"
              width="100%"
              alt="subadvisor logo"
          /></v-list-item-title>
        </v-list-item-content>
      </v-list-item>
      <v-divider></v-divider>
      <!-- DRAWER FOR MEMBER -->
      <template v-if="role == 'MEMBER'">
        <v-list>
          <v-list-group
            v-for="item in menuItemsAsMember"
            :key="item.title"
            v-model="item.active"
            :to="item.route"
          >
            <template v-slot:activator>
              <v-list-item-content class="text-decoration-none">
                <v-list-item-title
                  v-text="item.title"
                  :key="item.title"
                  :to="item.route"
                ></v-list-item-title>
              </v-list-item-content>
            </template>

            <v-list-item
              class="text-decoration-none"
              v-for="child in item.items"
              :key="child.title"
              :to="child.route"
            >
              <v-list-item-content>
                <v-list-item-title
                  v-text="child.title"
                  :key="child.title"
                  :to="child.route"
                ></v-list-item-title>
              </v-list-item-content>
            </v-list-item>
          </v-list-group>
        </v-list>
      </template>
      <!--  DRAWER FOR UNLOGGED AND VENUES -->
      <template v-else>
        <v-list>
          <v-list-group
            color="white"
            v-for="item in menuItems"
            :key="item.title"
            v-model="item.active"
            :to="item.route"
          >
            <template v-slot:activator>
              <v-list-item-content>
                <v-list-item-title
                  class="black--text"
                  v-text="item.title"
                  :key="item.title"
                  :to="item.route"
                ></v-list-item-title>
              </v-list-item-content>
            </template>

            <v-list-item
              class="black--text v-list-item--active text-decoration-none"
              v-for="child in item.items"
              :key="child.title"
              :to="child.route"
            >
              <v-list-item-content>
                <v-list-item-title
                  v-text="child.title"
                  :key="child.title"
                  :to="child.route"
                ></v-list-item-title>
              </v-list-item-content>
            </v-list-item>
          </v-list-group>
          <v-list-item
            class="text-decoration-none"
            v-for="item in subPlaces"
            :key="item.title"
            :to="item.route"
          >
            <v-list-content>
              <v-list-title>PLACES </v-list-title></v-list-content
            >
          </v-list-item>
          <template>
            <v-list-item
              class="text-decoration-none"
              v-for="item in subContact"
              :key="item.title"
              :to="item.route"
            >
              <v-list-content>
                <v-list-title>CONTACT </v-list-title></v-list-content
              >
            </v-list-item>
          </template>
          <v-list-item
            class="text-decoration-none"
            v-for="item in subAbout"
            :key="item.title"
            :to="item.route"
          >
            <v-list-content>
              <v-list-title>ABOUT </v-list-title></v-list-content
            >
          </v-list-item>
        </v-list>
      </template>
      <!-- --------------------- -->
    </v-navigation-drawer>
    <!--  MOBILE: NAVIGATION DRAWER END-->
    <!-- DESKTOP: LEFT ALIGNED ITEMS-->
    <!--EVENT-->
    <template v-if="role == 'MEMBER'">
      <v-menu offset-y>
        <template v-slot:activator="{ on, attrs }">
          <v-btn
            class="noGreyHover noGrey d-none text-decoration-none d-sm-flex ml-5 rounded-pill border border-dark"
            text
            v-bind="attrs"
            v-on="on"
          >
            Event
          </v-btn>
        </template>
        <v-list>
          <v-list-item
            v-for="item in subEventsLoggedInMember"
            :key="item.title"
            :to="item.route"
          >
            <v-list-item-title>{{ item.title }}</v-list-item-title>
          </v-list-item>
        </v-list>
      </v-menu>
    </template>
    <!-- ELSE OTHER USERS -->
    <template v-else>
      <v-menu offset-y>
        <template v-slot:activator="{ on, attrs }">
          <v-btn
            class="noGreyHover noGrey d-none d-sm-flex ml-5 rounded-pill border border-dark"
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
    </template>
    <!--PLACES-->
    <template>
      <v-menu offset-y>
        <template v-slot:activator="{ on, attrs }">
          <v-btn
            v-for="item in subPlaces"
            :key="item.title"
            :to="item.route"
            class="noGreyHover noGrey d-none d-sm-flex ml-5 text-decoration-none rounded-pill border border-dark"
            text
            v-bind="attrs"
            v-on="on"
          >
            Places
          </v-btn>
        </template>
      </v-menu>
    </template>
    <!-- DESKTOP: LEFT ALIGNED ITEMS END-->
    <v-spacer></v-spacer>
    <v-toolbar-title class="d-none d-sm-flex white--text"
      ><img src="../../assets/logo.png" width="360px" alt="subadvisor logo"
    /></v-toolbar-title>
    <v-spacer></v-spacer>
    <!-- DESKTOP: RIGHT ALIGNED ITEMS START-->
    <!--CONTACT-->
    <v-menu offset-y>
      <template v-slot:activator="{ on, attrs }">
        <v-btn
          class="noGreyHover noGrey d-none text-decoration-none d-sm-flex ml-5 rounded-pill border border-dark"
          text
          v-bind="attrs"
          v-on="on"
          v-for="item in subContact"
          :key="item.title"
          :to="item.route"
        >
          Contact
        </v-btn>
      </template>
    </v-menu>
    <!--ABOUT-->
    <v-menu offset-y>
      <template v-slot:activator="{ on, attrs }">
        <v-btn
          class="noGreyHover noGrey d-none d-sm-flex ml-5 text-decoration-none rounded-pill border border-dark"
          text
          v-bind="attrs"
          v-on="on"
          v-for="item in subAbout"
          :key="item.title"
          :to="item.route"
        >
          About
        </v-btn>
      </template>
    </v-menu>
    <!--PROFILE-->
    <v-menu offset-y class="d-none d-sm-flex">
      <template v-slot:activator="{ on, attrs }">
        <v-btn
          v-if="!authenticated || user.pic === null"
          icon
          class="mr-5 ml-5"
          v-bind="attrs"
          v-on="on"
        >
          <v-icon large class="d-sm-flex"> account_circle </v-icon>
        </v-btn>
        <v-btn
          v-else
          icon
          class="noGreyHover noGrey mr-5 ml-5"
          v-bind="attrs"
          v-on="on"
        >
          <v-avatar size="40"> <PictureView :picData="user.pic" /></v-avatar>
        </v-btn>
      </template>
      <!-- START AUTHENTICATION CONTROL -->
      <template v-if="authenticated">
        <template v-if="role == 'VENUE'">
          <v-list>
            <v-list-item
              v-for="item in subProfileLoggedInVenue"
              :key="item.title"
              :to="item.route"
              @click="logoutSubmit(item.action)"
            >
              <v-list-item-title>{{ item.title }}</v-list-item-title>
            </v-list-item>
          </v-list>
        </template>
        <template v-else-if="role == 'MEMBER'">
          <v-list>
            <v-list-item
              v-for="item in subProfileLoggedInMember"
              :key="item.title"
              :to="item.route"
              @click="logoutSubmit(item.action)"
            >
              <v-list-item-title>{{ item.title }}</v-list-item-title>
            </v-list-item>
          </v-list>
        </template>
      </template>
      <template v-else>
        <v-list>
          <v-list-item
            v-for="item in subProfile"
            :key="item.title"
            :to="item.route"
          >
            <v-list-item-title>{{ item.title }}</v-list-item-title>
          </v-list-item>
        </v-list>
      </template>
      <!-- END AUTHENTICATION CONTROL -->
    </v-menu>
    <!-- DESKTOP: RIGHT ALIGNED ITEMS END-->
  </v-toolbar>
</template>

<script>
import { mapActions, mapGetters } from 'vuex'
import PictureView from '../features/PictureView.vue'

export default {
  components: { PictureView },
  name: 'Navbar',
  data() {
    return {
      sideNav: false,
      menuItems: [
        {
          action: 'mdi-ticket',
          items: [
            { title: 'ALL EVENTS', route: '/events' },
            { title: 'POPULAR EVENTS', route: '/popularEvents' },
            { title: 'PIC OF THE WEEK', route: '/picOfTheWeek' },
          ],
          title: 'EVENTS',
        },
      ],

      menuItemsAsMember: [
        {
          action: 'mdi-ticket',
          items: [
            { title: 'YOUR CALENDER', route: '/events' },
            { title: 'ALL EVENTS', route: '/events' },
            { title: 'POPULAR EVENTS', route: '/events' },
          ],
          title: 'EVENTS',
        },
        {
          action: 'mdi-ticket',
          items: [
            { title: 'ALL PLACES', route: '/places' },
            { title: 'PIC OF THE WEEK', route: '/places' },
          ],
          title: 'PLACES',
        },
        {
          action: 'mdi-ticket',
          items: [{ title: 'Contact', route: '/contact' }],
          title: 'CONTACT',
          route: '/contact',
        },
        {
          action: 'mdi-ticket',
          items: [{ title: 'ABOUT', route: '/about' }],
          title: 'About',
        },
      ],
      subEvents: [
        { title: 'ALL EVENTS', route: '/events' },
        { title: 'POPULAR EVENTS', route: '/popularEvents' },
        { title: 'PIC OF THE WEEK', route: '/picOfTheWeek' },
      ],
      subPlacesLoggedInMember: [
        { title: 'ALL PLACES', route: '/places' },
        { title: 'PIC OF THE WEEK', route: '/places' },
      ],
      subContact: [{ title: 'CONTACT', route: '/contact' }],
      subAbout: [{ title: 'ABOUT', route: '/about' }],
      subPlaces: [{ title: 'ALL PLACES', route: '/places' }],

      subProfile: [
        { title: 'LOGIN', route: '/login' },
        { title: 'SIGNUP', route: '/signup' },
      ],
      subProfileLoggedInVenue: [
        { title: 'YOUR PROFILE', route: '/myVenueProfile' },
        { title: 'YOUR EVENTS', route: '/myEvents' },
        { title: 'CREATE EVENT', route: '/CreateEvents' },
        { title: 'LOGOUT', action: this.logout },
      ],
      subProfileLoggedInMember: [
        { title: 'YOUR PROFILE', route: '/myMemberProfile' },
        { title: 'YOUR CALENDER', route: '/events' },
        { title: 'LOGOUT', action: this.logout },
      ],
    }
  },
  computed: {
    ...mapGetters({
      authenticated: 'auth/authenticated',
      user: 'auth/user',
      role: 'auth/role',
    }),
  },
  methods: {
    ...mapActions({
      logout: 'auth/logout',
    }),

    logoutSubmit(action) {
      if (action === this.logout) {
        this.logout()
          .then(() => {
            this.$router.replace({
              name: 'home',
            })
          })
          .catch(() => {
            console.log('failed')
          })
      }
    },
  },
}
</script>

<style scoped>
i.v-icon.v-icon {
  color: black;
}
.noGrey:focus::before {
  opacity: 0 !important;
}
.noGreyHover:hover::before {
  opacity: 0 !important;
}
</style>
