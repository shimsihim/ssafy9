<template>
  <div class="container">
    <h2>비디오 목록</h2>
    <div v-if="videos">
     
          <tr v-for="(video, index) in videos" :key="index">
            <video-card v-bind:video ="video"></video-card>
          </tr>
        </div>
    <div v-else>등록된 게시물이 없습니다.</div>
    <button >등록하기</button>
  </div>
</template>

<script>
import { mapState, mapGetters } from "vuex";
import VideoCard from "@/components/video/VideoCard"

export default {
  name: "PostList",
  components : {
    VideoCard,
  },
  methods: {
    change(video_part){
      this.$store.dispatch("setVideos",video_part);
    },
    // registPost(){
    //   this.$store.state.post = null;
    //   this.$router.push("/board/regist");
    // }
  },
  computed: {
    ...mapState(["videos"]),
    ...mapGetters(["videoCnt"]),
  },
  
  watch: {
      $route(to, form) {
      if (to.path !== form.path) this.change(this.$route.params.video_part);
    },
  },
  created() {
    const pathName = new URL(document.location).pathname.split("/");
    const video_part = pathName[pathName.length-1];
    this.$store.dispatch("setVideos",video_part);
  },
};
</script>

