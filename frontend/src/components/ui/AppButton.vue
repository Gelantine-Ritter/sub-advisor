<template>
  <div class="text-center">
    <v-btn small elevation="1" class="rounded-pill">{{ text }}</v-btn>
  </div>
</template>

<script>
export default {
  name: 'AppButton',
  props: {
    text: String,
  },
}
</script>

<style scoped>
.theme--light.v-btn.v-btn--has-bg {
  background: black;
  color: white;
  width: 65%;
  font-size: 100%;
}
</style>
