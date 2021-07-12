<template>
    <v-icon class="skipIcon">mdi-skip-forward</v-icon>
</template>

<script>
export default {
  props: {
    show: Boolean
  }
}
</script>


<style scoped>
.skipIcon{
    color: black;
}
@media screen and (max-width: 600px) {
  .skipIcon{
      color: #cafb03;
  }
}
</style>