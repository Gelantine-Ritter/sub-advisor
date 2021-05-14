<template v-model="sideNav" absolute bottom temporary app>
  <v-list>
    <v-list-tile v-for="item in menuItems" :key="item.title">
      <v-list-tile-action>
        <v-icon>{{ item.icon }}</v-icon>
      </v-list-tile-action>
      <v-list-tile-content> {{ item.title }} </v-list-tile-content>
      <v-spacer></v-spacer>
    </v-list-tile>
  </v-list>
</template>

<script>
export default {
  name: 'Drawer',

  data() {
    return {
      sideNav: false,
      menuItems: [
        { icon: 'account_circle' },
        { icon: 'home', title: 'test1' },
        { icon: 'home', title: 'test2' },
        { icon: 'home', title: 'test3' },
        { icon: 'home', title: 'test4' },
      ],
    }
  },
}
</script>

<style></style>
