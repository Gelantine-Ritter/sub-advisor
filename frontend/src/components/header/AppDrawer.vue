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
  items: [
    { icon: 'account_circle' },
    { title: 'test1' },
    { title: 'tets1' },
    { title: 'test3' },
    { title: 'test4' },
  ],
  sideNav: false,
}
</script>

<style></style>
