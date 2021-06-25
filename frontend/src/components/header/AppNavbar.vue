<template>
  <v-toolbar flat class="border-bottom border-dark">
    <!-- navigation drawer-->
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
      <!-- NAV DRAWER FOR VENUE -->
      <template v-if="role == 'VENUE'">
        <v-list>
          <v-list-group
            v-for="item in menuItemsAsVenue"
            :key="item.title"
            v-model="item.active"
            :to="item.route"
          >
            <template v-slot:activator>
              <v-list-item-content>
                <v-list-item-title
                  v-text="item.title"
                  :key="item.title"
                  :to="item.route"
                ></v-list-item-title>
              </v-list-item-content>
            </template>

            <v-list-item
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
      <!-- --------------------- -->
      <!-- NAV DRAWER FOR MEMBER -->
      <template v-else-if="role == 'MEMBER'">
        <v-list>
          <v-list-group
            v-for="item in menuItemsAsMember"
            :key="item.title"
            v-model="item.active"
            :to="item.route"
          >
            <template v-slot:activator>
              <v-list-item-content>
                <v-list-item-title
                  v-text="item.title"
                  :key="item.title"
                  :to="item.route"
                ></v-list-item-title>
              </v-list-item-content>
            </template>

            <v-list-item
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
      <!-- --------------------- -->
      <!-- NAV DRAWER FOR UNLOGGED -->
      <template v-else>
        <v-list>
          <v-list-group
            v-for="item in menuItems"
            :key="item.title"
            v-model="item.active"
            :to="item.route"
          >
            <template v-slot:activator>
              <v-list-item-content>
                <v-list-item-title
                  v-text="item.title"
                  :key="item.title"
                  :to="item.route"
                ></v-list-item-title>
              </v-list-item-content>
            </template>

            <v-list-item
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
      <!-- --------------------- -->
    </v-navigation-drawer>
    <!-- navigation drawer-->
    <!-- left aligned items-->
    <!--EVENT-->
    <template v-if="role == 'VENUE'">
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
            v-for="item in subEventsLoggedInPlace"
            :key="item.title"
            :to="item.route"
          >
            <v-list-item-title>{{ item.title }}</v-list-item-title>
          </v-list-item>
        </v-list>
      </v-menu>
    </template>
    <template v-else-if="role == 'MEMBER'">
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
            v-for="item in subEventsLoggedInMember"
            :key="item.title"
            :to="item.route"
          >
            <v-list-item-title>{{ item.title }}</v-list-item-title>
          </v-list-item>
        </v-list>
      </v-menu>
    </template>
    <template v-else>
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
    </template>
    <!--PLACES-->
    <template v-if="role == 'VENUE'">
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
            v-for="item in subPlacesLoggedInPlace"
            :key="item.title"
            :to="item.route"
          >
            <v-list-item-title>{{ item.title }}</v-list-item-title>
          </v-list-item>
        </v-list>
      </v-menu>
    </template>
    <template v-else-if="role == 'MEMBER'">
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
            v-for="item in subPlacesLoggedInMember"
            :key="item.title"
            :to="item.route"
          >
            <v-list-item-title>{{ item.title }}</v-list-item-title>
          </v-list-item>
        </v-list>
      </v-menu>
    </template>
    <template v-else>
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
    </template>
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
        <v-btn
          v-if="!authenticated"
          icon
          class="mr-5 ml-5"
          v-bind="attrs"
          v-on="on"
        >
          <v-icon large class="d-sm-flex"> account_circle </v-icon>
        </v-btn>
        <v-btn v-else icon class="mr-5 ml-5" v-bind="attrs" v-on="on">
          <v-avatar size="40"> <PictureView :picData="user.pic" /></v-avatar>
        </v-btn>
      </template>
      <!-- START AUTHENTICATION CONTROL -->
      <template v-if="authenticated">
        <v-list>
          <v-list-item
            v-for="item in subProfileLoggedIn"
            :key="item.title"
            :to="item.route"
            @click="logoutSubmit(item.action)"
          >
            <v-list-item-title>{{ item.title }}</v-list-item-title>
          </v-list-item>
        </v-list>
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
    <!-- right aligned items-->
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
            { title: 'All Events', route: '/events' },
            { title: 'Popular Events', route: '/events' },
          ],
          title: 'Events',
        },
        {
          action: 'mdi-ticket',
          items: [
            { title: 'All Places', route: '/places' },
            { title: 'Pic of the week', route: '/places' },
          ],
          title: 'Places',
        },
        {
          action: 'mdi-ticket',
          items: [{ title: 'Contact', route: '/contact' }],
          title: 'Contact',
          route: '/contact',
        },
        {
          action: 'mdi-ticket',
          items: [{ title: 'About', route: '/about' }],
          title: 'About',
          route: '/about',
        },
      ],
      menuItemsAsVenue: [
        {
          action: 'mdi-ticket',
          items: [
            { title: 'Your Events', route: '/events' },
            { title: 'All Events', route: '/events' },
            { title: 'Popular Events', route: '/events' },
          ],
          title: 'Events',
        },
        {
          action: 'mdi-ticket',
          items: [
            { title: 'Your Place', route: '/places/' },
            { title: 'All Places', route: '/places' },
            { title: 'Pic of the week', route: '/places' },
          ],
          title: 'Places',
        },
        {
          action: 'mdi-ticket',
          items: [{ title: 'Contact', route: '/contact' }],
          title: 'Contact',
          route: '/contact',
        },
        {
          action: 'mdi-ticket',
          items: [{ title: 'About', route: '/about' }],
          title: 'About',
          route: '/about',
        },
      ],
      menuItemsAsMember: [
        {
          action: 'mdi-ticket',
          items: [
            { title: 'Your Calendar', route: '/events' },
            { title: 'All Events', route: '/events' },
            { title: 'Popular Events', route: '/events' },
          ],
          title: 'Events',
        },
        {
          action: 'mdi-ticket',
          items: [
            { title: 'All Places', route: '/places' },
            { title: 'Pic of the week', route: '/places' },
          ],
          title: 'Places',
        },
        {
          action: 'mdi-ticket',
          items: [{ title: 'Contact', route: '/contact' }],
          title: 'Contact',
          route: '/contact',
        },
        {
          action: 'mdi-ticket',
          items: [{ title: 'About', route: '/about' }],
          title: 'About',
          route: '/about',
        },
      ],
      subEvents: [
        { title: 'All Events', route: '/events' },
        { title: 'Popular Events', route: '/events' },
      ],
      subEventsLoggedInPlace: [
        { title: 'Your Events', route: '/events' },
        { title: 'All Events', route: '/events' },
        { title: 'Popular Events', route: '/events' },
      ],
      subEventsLoggedInMember: [
        { title: 'Your Calendar', route: '/events' },
        { title: 'All Events', route: '/events' },
        { title: 'Popular Events', route: '/events' },
      ],
      subPlaces: [
        { title: 'All Places', route: '/places' },
        { title: 'Pic of the week', route: '/places' },
      ],
      subPlacesLoggedInPlace: [
        { title: 'Your Place', route: '/places/' },
        { title: 'All Places', route: '/places' },
        { title: 'Pic of the week', route: '/places' },
      ],
      subPlacesLoggedInMember: [
        { title: 'All Places', route: '/places' },
        { title: 'Pic of the week', route: '/places' },
      ],
      subContact: [{ title: 'Contact', route: '/contact' }],
      subAbout: [{ title: 'About', route: '/about' }],
      subProfile: [
        { title: 'Login', route: '/login' },
        { title: 'Signup', route: '/signup' },
      ],
      subProfileLoggedIn: [
        { title: 'Show Profile', route: '/myProfile' },
        { title: 'My Events', route: '/events' },
        { title: 'Create Event', route: '/CreateEvents' },
        { title: 'Logout', action: this.logout },
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
.myNavDrawer {
}
</style>
