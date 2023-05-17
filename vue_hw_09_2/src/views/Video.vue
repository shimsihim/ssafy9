
<template>
  <div>
    <header class="site-header d-flex flex-column justify-content-center align-items-center">
            <div class="container">
                <div class="row">

                    <div class="col-lg-12 col-12 text-center">

                        <h2 class="mb-0">영상조회페이지</h2>
                    </div>

                </div>
            </div>
      </header>
    <!-- <video-part-button></video-part-button> -->
    <router-view></router-view>
    <hr />
  </div>
</template>

<script>
//import VideoPartButton from "@/components/video/VideoPartButton.vue"

export default {
  name: "Video",
  components : {
    //VideoPartButton,
  }
};
</script>



