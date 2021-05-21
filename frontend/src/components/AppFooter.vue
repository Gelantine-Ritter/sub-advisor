<template>
  <v-container height="50" absolute>
    <v-row justify="center" no-gutters>
      <v-btn
        v-for="link in links"
        :key="link"
        color="black"
        text
        rounded
        class="my-2"
      >
        {{ link }}
      </v-btn>
      <v-col
        class="caption border-top border-dark py-4 text-center black--text"
        cols="12"
      >
        {{ new Date().getFullYear() }} —
        <strong>subadvisor</strong>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
export default {
  data: () => ({
    links: ['Contact', 'Impressum'],
  }),
}
</script>

<style></style>
