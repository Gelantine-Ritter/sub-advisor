<template>
  <v-toolbar elevation="0" class="border-bottom border-dark">
    <!-- MOBILE: NAVIGATION DRAWER START-->
    <v-app-bar-nav-icon
      @click.stop="sideNav = !sideNav"
      class="d-flex d-sm-none ml-5"
    ></v-app-bar-nav-icon>
    <v-navigation-drawer
      v-model="sideNav"
      :ripple="false"
      absolute
      side
      temporary
      app
      class="myNavDrawer noGreyHover noGrey"
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
      <!--  DRAWER FOR UNLOGGED AND VENUES -->
      <template>
        <v-list>
          <v-list-group
            color="white"
            v-for="item in menuItems"
            :key="item.title"
            v-model="item.active"
            :to="item.route"
            :ripple="false"
            class="noGreyHover noGrey"
          >
            <template v-slot:activator>
              <v-list-item-content>
                <v-list-item-title
                  :ripple="false"
                  class="black--text noGreyHover noGrey"
                  v-text="item.title"
                  :key="item.title"
                  :to="item.route"
                ></v-list-item-title>
              </v-list-item-content>
            </template>

            <v-list-item
              :ripple="false"
              class="black--text v-list-item--active text-decoration-none noGreyHover noGrey"
              v-for="child in item.items"
              :key="child.title"
              :to="child.route"
            >
              <v-list-item-content>
                <v-list-item-title
                  :ripple="false"
                  v-text="child.title"
                  :key="child.title"
                  :to="child.route"
                  class="text-decoration-none noGreyHover noGrey"
                ></v-list-item-title>
              </v-list-item-content>
            </v-list-item>
          </v-list-group>
          <v-list-item
            :ripple="false"
            class="text-decoration-none noGreyHover noGrey"
            v-for="item in subPlaces"
            :key="item.title"
            :to="item.route"
          >
            <v-list-item-content>
              <v-list-item-title
                >PLACES
              </v-list-item-title></v-list-item-content
            >
          </v-list-item>
          <template>
            <v-list-item
              :ripple="false"
              class="text-decoration-none noGreyHover noGrey"
              v-for="item in subContact"
              :key="item.title"
              :to="item.route"
            >
              <v-list-item-content>
                <v-list-item-title
                  >CONTACT
                </v-list-item-title></v-list-item-content
              >
            </v-list-item>
          </template>
          <v-list-item
            :ripple="false"
            class="text-decoration-none noGreyHover noGrey"
            v-for="item in subAbout"
            :key="item.title"
            :to="item.route"
          >
            <v-list-item-content>
              <v-list-item-title>ABOUT </v-list-item-title></v-list-item-content
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
            :ripple="false"
            text
            v-bind="attrs"
            v-on="on"
          >
            Event
          </v-btn>
        </template>
        <v-list>
          <v-list-item
            :ripple="false"
            v-for="item in subEvents"
            :key="item.title"
            :to="item.route"
            class="noGreyHover noGrey text-decoration-none"
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
            :ripple="false"
            text
            v-bind="attrs"
            v-on="on"
          >
            Event
          </v-btn>
        </template>
        <v-list>
          <v-list-item
            :ripple="false"
            v-for="item in subEvents"
            :key="item.title"
            :to="item.route"
            class="noGreyHover noGrey text-decoration-none"
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
            :ripple="false"
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
    <!-- <v-toolbar-title class="d-none d-sm-flex white--text myLogoNav"> -->
    <v-toolbar-title class="d-none d-sm-flex white--text myLogoNav">
      <v-img class="logo-img" contain src="../../assets/logo.png" height="200px" alt="subadvisor logo" @click="redirectToHome()"/> 
            <v-img class="logo-img-small" contain src="../../assets/logo_small.png" height="200px" alt="subadvisor logo" @click="redirectToHome()"/> 
    </v-toolbar-title>
    <v-spacer></v-spacer>
    <!-- DESKTOP: RIGHT ALIGNED ITEMS START-->
    <!--CONTACT-->
    <v-menu offset-y>
      <template v-slot:activator="{ on, attrs }">
        <v-btn
          class="noGreyHover noGrey d-none text-decoration-none d-sm-flex ml-5 rounded-pill border border-dark"
          :ripple="false"
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
          :ripple="false"
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
          :ripple="false"
          icon
          class="mr-5 ml-5 noGreyHover noGrey"
          v-bind="attrs"
          v-on="on"
        >
          <v-icon large class="d-sm-flex noGreyHover noGrey">
            account_circle
          </v-icon>
        </v-btn>
        <v-btn
          v-else
          :ripple="false"
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
              :ripple="false"
              v-for="item in subProfileLoggedInVenue"
              :key="item.title"
              :to="item.route"
              @click="logoutSubmit(item.action)"
              class="noGreyHover noGrey text-decoration-none"
            >
              <v-list-item-title>{{ item.title }}</v-list-item-title>
            </v-list-item>
          </v-list>
        </template>
        <template v-else-if="role == 'MEMBER'">
          <v-list>
            <v-list-item
              :ripple="false"
              v-for="item in subProfileLoggedInMember"
              :key="item.title"
              :to="item.route"
              @click="logoutSubmit(item.action)"
              class="noGreyHover noGrey text-decoration-none"
            >
              <v-list-item-title>{{ item.title }}</v-list-item-title>
            </v-list-item>
          </v-list>
        </template>
      </template>
      <template v-else>
        <v-list>
          <v-list-item
            :ripple="false"
            v-for="item in subProfile"
            :key="item.title"
            :to="item.route"
            class="noGreyHover noGrey text-decoration-none"
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
            { title: 'MOST POPULAR EVENT', route: '/popularEvent' },
            { title: 'PIC OF THE DAY', route: '/picOfTheDay' },
          ],
          title: 'EVENTS',
        },
      ],
      menuItemsAsMember: [
        {
          action: 'mdi-ticket',
          items: [
            { title: 'YOUR CALENDAR', route: '/calendar' },
            { title: 'ALL EVENTS', route: '/events' },
            { title: 'MOST POPULAR EVENT', route: '/popularEvent' },
          ],
          title: 'EVENTS',
        },
        {
          action: 'mdi-ticket',
          items: [
            { title: 'ALL PLACES', route: '/places' },
            { title: 'PIC OF THE DAY', route: '/picOfTheDay' },
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
        { title: 'MOST POPULAR EVENT', route: '/popularEvent' },
        { title: 'PIC OF THE DAY', route: '/picOfTheDay' },
      ],
      subPlacesLoggedInMember: [
        { title: 'ALL PLACES', route: '/places' },
        { title: 'PIC OF THE DAY', route: '/picOfTheDay' },
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
        { title: 'YOUR CALENDAR', route: '/calendar' },
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
    redirectToHome() {
      this.$router.replace({
        name: 'home',
      })
    },
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

.theme--light.v-list-item--active:hover::before,
.theme--light.v-list-item--active::before {
  opacity: 0 !important;
}
.theme--light.v-btn--active:hover::before,
.theme--light.v-btn--active::before {
  opacity: 0 !important;
}
.theme--light.v-btn:hover::before {
  opacity: 0 !important;
}
.v-btn {
  transition-property: none;
  transition: none;
}
.v-btn:before {
  transition-property: none;
  transition: none;
}
a:hover {
  text-decoration: none !important;
}
::v-deep .v-overlay__scrim {
  opacity: 0 !important;
}

::v-deep .theme--light.v-list-item:focus::before {
  opacity: 0 !important;
}
::v-deep .theme--light.v-list-item:hover::before {
  opacity: 0 !important;
}
::v-deep .theme--light.v-list-item--active:hover::before {
  opacity: 0 !important;
}
::before :not(v-app-bar-nav-icon) {
  opacity: 0 !important;
}
.myNoButton {
  opacity: 0 !important;
}
.myLogoNav {
  padding-left: 107px !important;
}
@media screen and (min-width: 1000px) {      
  .logo-img-small{
   display: none; 
  }
} 
@media screen and (max-width: 1000px) {    
   
  .myLogoNav {
    padding-left: 98px !important;
  }
  
} 
@media screen and (max-width: 800px) {
  .logo-img-small{
       display:contents; 
  }
  .logo-img{
       display: none; 
  }
}
/*
@media screen and (max-width: 700px) { 
  ::v-deep .v-image__image--contain {
    margin-left: -5vw !important;
  }
}
*/
</style>
