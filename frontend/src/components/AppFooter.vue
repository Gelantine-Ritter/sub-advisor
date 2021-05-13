<template>
  <v-container>
    <v-footer class="padless">
      <v-col class="text-center" cols="12">
        {{ new Date().getFullYear() }} — <strong>subadvisor</strong>
      </v-col>
    </v-footer>
  </v-container>
</template>

<script>
export default {
  name: 'Footer',
  data() {
    return {}
  },
}
</script>

<style></style>
