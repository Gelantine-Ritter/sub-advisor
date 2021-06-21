<template>
  <v-responsive v-if="this.picData == null || this.picData == ''">
    
    <div class="noPicture"><h1>NO PICTURE</h1></div>
  </v-responsive>
  <v-responsive v-else>
    <v-img fill class="myPicture" :src="picDataUrl()" alt="" />
  </v-responsive>
</template>
<script>
export default {
  props: {
    picData: String,
  },
  methods: {
    picDataUrl() {
      return 'data:image/png;base64, ' + this.picData
    },
  },
}
</script>

<style>
.myPicture {
  height: 100%;
}
.myPictureCard {
  padding: 2%;
  margin-bottom: 8%;
}
.noPicture {
  height: 100%;
  background: black;
  line-height: 100%;
}
.noPicture h1 {
  font-size: 70%;
  text-align: center;
  line-height: 100%;
  color: white;
}
</style>