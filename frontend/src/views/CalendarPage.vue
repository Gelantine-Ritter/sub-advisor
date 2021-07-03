<template>
        <div>
            <v-container my-5>
            <v-row class="justify-center">
                <v-col cols="10"> 
                <h1 class="h1Style text-right">YOUR
                <br />    
                CALENDAR</h1>
                </v-col>
            </v-row>
            <br />
            <v-col cols="12">
                <v-row class= "justify-space-between align-center">
                    <v-col cols="12" sm="12" md="4" lg= "4" xl="4" class= "text-center justify-center d-none d-md-flex">
                        <v-date-picker
                        class="myCalendar"
                        rounded-xl
                        color="black"
                        is-dark
                        is-range
                        no-title
                        ></v-date-picker>
                        
                    </v-col>
                    <v-col cols="12" sm="12" md="4" lg= "4" xl="4" class= "text-center align-center d-lg-none" >
                        <v-menu
                        :close-on-content-click="false"
                        :nudge-right="40"
                        transition="scale-transition"
                        offset-y
                        max-width="290px"
                        min-width="290px"
                        >
                        <template v-slot:activator="{ on }">
                            <v-text-field
                            large
                            block
                            outlined
                            class="rounded-pill centered-input myDateField"
                            label=" DATE"
                            color="black"
                            readonly
                            v-on="on"
                            ></v-text-field>
                        </template>
                        <v-date-picker
                            locale="en-in"
                            rounded-xl
                            color="black"
                            is-dark
                            is-range
                            no-title
                        ></v-date-picker>
                  </v-menu>
                    </v-col>

                    <v-col cols="1" sm="1" md="1" lg= "1" xl="1" class="text-right">
                        <v-icon class="skipIcon">mdi-skip-backward</v-icon>
                    </v-col>
                    <v-col cols="10" sm="10" md="6" lg= "6" xl="6">
                        <div class="rounded-xl mycontainer" fluid>
                            <v-row center>
                                <!-- venueName, adress, ... -->
                                <v-col cols="12" >
                                    <h2>VENUE.NAME</h2>
                                    <div>
                                        <h5> EVENT.NAME </h5>
                                    </div>
                                </v-col>
                            </v-row>
                            <v-row center>
                                <v-col cols="12" xs="12" sm="7" md="7" lg="7" xl="7">
                                    <div> Doors: EVENT.EVENTSTART </div>
                                    <div> Date: EVENT.DATE </div>
                                </v-col>
                                <v-col cols="12" xs="12" sm="5" md="5" lg="5" xl="5" class="text-center">
                                        Price: XX.XX €
                                </v-col>
                            </v-row>
                            <v-row center>  
                                <v-col cols="12" xs="12" sm="7" md="7" lg="7" xl="7">
                                    <div>
                                    <!-- <p>{{ eventObj.info }}</p> -->
                                    EVENT.INFO <br /> 
                                    Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua... 
                                    </div>
                                    <br />
                                    <v-btn 
                                    large
                                    block
                                    outlined
                                    elevation="1"
                                    class="rounded-pill text-decoration-none myEnterBtn" 
                                    >
                                    GO TO EVENT
                                    </v-btn>
                                </v-col>
                                <!-- tags, price, ... -->
                                <v-col cols="12" xs="12" sm="5" md="5" lg="5" xl="5" class="text-center">
                                    <v-card
                                    class="pa-2 rounded-xl myLogoCard"
                                    flat
                                    tile
                                    >
                                    <v-responsive class="myResponsivePictureContainer">
                                        <div class="noPicture"><h1>NO PICTURE</h1></div>
                                    </v-responsive>    
                                    </v-card>
                                </v-col>
                            </v-row>
                        </div>
                    </v-col>
                    <v-col cols="1" sm="1" md="1" lg= "1" xl="1" class="text-left">
                        <v-icon class="skipIcon">mdi-skip-forward</v-icon>
                    </v-col>
                </v-row>
            </v-col>
        </v-container>
        </div>
</template>

<script>
export default {
    data() {
        return {
            eventObj: {
                pic: '',
            }
    }
  },
}
</script>

<style scoped>
.mycontainer{
    width: 100%;
    margin: 0;
}
::v-deep .v-input__slot{
    background: white !important;
}

.myCalendar{
    border: solid 2px black;
    border-radius: 24px;
}

.skipIcon{
    color: black;
}
.myLogoCard {
  width: 100%;
  padding: 2%;
  margin-bottom: 8%;
}
.noPicture {
  height: 100%;
  width: 100%;
  background: black;
}
.noPicture h1 {
  font-size: 100%;
  text-align: center;
  line-height: 8.5em;
  color: white;
}
.myLogo {
  height: 100%;
}
@media (min-width: 960px){
    .container {
        max-width: 95vw !important;
    }
}
@media screen and (max-width: 600px) {
  .myLogoCard {
    width: 100%;
    height: auto;
  }
  .noPicture h1 {
    line-height: 150px;
  }
  .skipIcon{
      color: #cafb03;
  }
}
</style>