<template>
<v-container my-5>
    <h1
      class="h1Style text-center display-3 font-weight-medium"
      :class="[$vuetify.breakpoint.mdAndUp ? 'display-4' : 'display-2']"
    >
     PIC OF THE WEEK
    </h1>
  <div class="rounded-xl mycontainer" fluid>
      <v-container>
        <v-row center>
          <!-- venueName, adress, ... -->
          <v-col cols="12" xs="12" sm="12" md="8" lg="8" xl="8">
              <h2>HIER VENUE NAME</h2>
              <div>
                <h5>
                 HIER ADRESSE
                </h5>
              </div>
          </v-col>
          <v-col cols="12" xs="12" sm="12" md="4" lg="4" xl="4">
            <h1>HIER MAP</h1>
          </v-col>
        </v-row>
        <v-row center>
              <v-col cols="12" xs="12" sm="8" md="8" lg="8" xl="8">
              <div>
                  ÖFFNUNGSZEITEN              </div>
              <div>
              ÖFFNUNGSZEITE              </div>
              </v-col>
              <v-col cols="12" xs="12" sm="4" md="4" lg="4" xl="4" class="text-center">
PREIS              </v-col>
      </v-row>
        <v-row center>  
                        <v-col cols="12" xs="12" sm="12" md="12" lg="8" xl="8">
            <div>
            <!-- <p>{{ eventObj.info }}</p> -->
            Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Sed euismod nisi porta lorem mollis. Ipsum nunc aliquet bibendum enim facilisis gravida neque. Sed augue lacus viverra vitae congue eu consequat. Proin sed libero enim sed faucibus turpis in eu. Diam maecenas ultricies mi eget mauris pharetra et ultrices neque. Scelerisque fermentum dui faucibus in. Luctus venenatis lectus magna fringilla urna porttitor rhoncus dolor purus. Elementum facilisis leo vel fringilla est ullamcorper. Donec ac odio tempor orci dapibus ultrices in iaculis. Sit amet risus nullam eget felis eget nunc. Ac turpis egestas integer eget aliquet nibh. Et tortor at risus viverra adipiscing at in tellus. Et tortor consequat id porta nibh venenatis. Sem nulla pharetra diam sit amet. Quis vel eros donec ac odio tempor orci dapibus. Consequat ac felis donec et odio pellentesque.
            <br /><br />
            Libero id faucibus nisl tincidunt eget nullam non. Est pellentesque elit ullamcorper dignissim cras tincidunt. Consectetur purus ut faucibus pulvinar elementum. Est velit egestas dui id ornare arcu. Egestas purus viverra accumsan in nisl nisi scelerisque. Nec ultrices dui sapien eget mi proin. Risus viverra adipiscing at in. Consectetur adipiscing elit duis tristique sollicitudin nibh sit amet commodo. Nunc sed velit dignissim sodales ut eu. Volutpat commodo sed egestas egestas fringilla phasellus. Elementum sagittis vitae et leo duis ut diam quam. Odio facilisis mauris sit amet massa vitae tortor.
          </div>
          </v-col>
          <!-- tags, price, ... -->
          <v-col cols="12" xs="12" sm="12" md="12" lg="4" xl="4" class="text-center">
            <v-card
            class="pa-2 rounded-xl myLogoCard"
            flat
            tile
          >
            <v-responsive class="myResponsivePictureContainer">
             <h1>image und tags hier</h1>
            </v-responsive>
          </v-card>
          </v-col>
        </v-row> 
      </v-container>
    </div>
</v-container>
</template>

<script>
export default {}
</script>

<style></style>
