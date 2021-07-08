<template>
  <div>
    <v-icon small class="ml-2" >fas fa-users</v-icon>
                          {{number}}
  </div>
</template>

<script>
export default {
  name: 'ParticipantsIcon',
  props: { 
    number: Number,
  }, 
}
</script>

<style>

</style>