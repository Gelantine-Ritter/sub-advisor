<template>
  <div>
    <template v-if="user != null">
      <div class="rounded-xl mycontainer" fluid>
        <v-card
          center
          class="rounded-xl d-flex justify-space-between mb-1"
          flat
          tile
        >
          <v-card
            class="pa-2 rounded-xl myLogoCard"
            flat
            tile
            :style="styleObject"
          >
            <v-responsive class="myResponsivePictureContainer">
              <template v-if="user.pic == null || user.pic == ''">
                <div class="noPicture"><h1>NO PICTURE</h1></div>
              </template>
              <template v-else>
                <v-img fill class="myLogo" :src="picDataUrl()" alt="" />
              </template>
            </v-responsive>
          </v-card>
          <v-card class="pa-2" flat tile>
            <h4>
              <v-btn @click.stop="showDialogPicture = true" icon class="ml-5">
                <v-icon class="text-right" color="black">far fa-edit</v-icon>
                <ModalPicture v-model="showDialogPicture" />
              </v-btn>
            </h4>
          </v-card>
        </v-card>
        <div>
          <v-card class="d-flex justify-space-between mb-1" flat tile>
            <v-card class="pa-2" flat tile>
              <h4>YOUR ACCOUNT</h4>
              <p class="font-weight-thin">
                Here you can edit your private account data.
              </p>
            </v-card>
            <v-card class="pa-2" flat tile>
              <h4>
                <v-btn @click.stop="showDialogAccount = true" icon class="ml-5">
                  <v-icon
                    class="text-right myEditButtonSmallScreen"
                    color="black"
                    >far fa-edit</v-icon
                  >
                  <ModalAccount v-model="showDialogAccount" />
                </v-btn>
              </h4>
            </v-card>
          </v-card>
        </div>
        <v-list-item two-line>
          <v-list-item-content>
            <v-list-item-subtitle>USERNAME</v-list-item-subtitle>
            <v-list-item-title> {{ user.username }}</v-list-item-title>
          </v-list-item-content>
        </v-list-item>
        <v-list-item two-line>
          <v-list-item-content>
            <v-list-item-subtitle>MAIL</v-list-item-subtitle>
            <v-list-item-title> {{ user.email }} </v-list-item-title>
          </v-list-item-content>
        </v-list-item>
        
        <v-list-item two-line>
          <v-list-item-content>
            <v-list-item-subtitle> FIRSTNAME </v-list-item-subtitle>
            <v-list-item-title> {{ user.firstName }} </v-list-item-title>
          </v-list-item-content>
        </v-list-item>
        <v-list-item two-line>
          <v-list-item-content>
            <v-list-item-subtitle>LASTNAME</v-list-item-subtitle>
            <v-list-item-title> {{ user.lastName }} </v-list-item-title>
          </v-list-item-content>
        </v-list-item>
        <v-divider></v-divider>

        <v-row justify="center">
          <v-btn
            class="myDeleteButton"
            outlined
            rounded
            text
            @click.stop="deleteDialog = true"
          >
            DELETE YOUR ACCOUNT
          </v-btn>
          <v-dialog v-model="deleteDialog" max-width="500">
            <v-card>
              <v-card-title class="text-h4 justify-center">
                Are you really sure you want to delete your account?
              </v-card-title>
              <v-card-actions>
                <v-spacer></v-spacer>
                <v-btn
                  elevation="0"
                  color="light"
                  @click="deleteDialog = false"
                >
                  Let me think about it...
                </v-btn>

                <v-btn color="error" outlined @click="deleteProfileSubmit">
                  Yes
                </v-btn>
              </v-card-actions>
            </v-card>
          </v-dialog>
        </v-row>
      </div>
    </template>
    <template v-else>
      <div class="rounded-xl mycontainer" fluid>
        <h1>YOU ARE ACTUALY NOT ALLOWED TO SEE THIS :/</h1>
      </div>
    </template>
  </div>
</template>

<script>
import { mapGetters, mapActions } from 'vuex'
import ModalAccount from './DialogAccountMemberUpdate.vue'
import ModalPicture from './DialogPictureMemberUpdate.vue'

export default {
  data() {
    return {
      styleObject: { border: '2px solid #cafb03' },
      showDialogAccount: false,
      showDialogPicture: false,
      deleteDialog: false,
    }
  },
  computed: {
    ...mapGetters({
      user: 'auth/user',
    }),
  },
  components: {
    ModalAccount,
    ModalPicture,
  },
  methods: {
    ...mapActions({
      deleteMemberProfile: 'auth/deleteMemberProfile',
    }),
    picDataUrl() {
      return 'data:image/png;base64, ' + this.user.pic
    },
    deleteProfileSubmit() {
      this.deleteMemberProfile().then(() => {
        this.$router.replace({
          name: 'home',
        })
      })
    },
  },
}
</script>

<style scoped>
.myLogoCard {
  width: 250px;
  height: 250px;
  padding: 2%;
  margin-bottom: 8%;
}
.myResponsivePictureContainer {
  height: 100%;
  width: 100%;
}
.noPicture {
  height: 100%;
  width: 100%;
  background: black;
  line-height: 100%;
}
.noPicture h1 {
  font-size: 100%;
  text-align: center;
  line-height: 250px;
  color: white;
}
.myLogo {
  height: 100%;
}
.myRowInOpeningHours {
  margin-bottom: 5px;
}
.mySubOpeningHours {
  margin-bottom: 20px !important;
}
@media screen and (max-width: 600px) {
  .myDateColInOpeningHours {
    padding-bottom: 0;
  }
  .myHoursColInOpeningHours {
    padding-top: 0;
  }
  .myLogoCard {
    width: 150px;
    height: 150px;
  }
  .noPicture h1 {
    line-height: 150px;
  }
}
</style>
