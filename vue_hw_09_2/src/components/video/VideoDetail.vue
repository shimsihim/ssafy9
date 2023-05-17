<template>
  <div class="container">
    <h2>영상 상세</h2>
    <div>제목: {{ video.video_title }}</div>
    <div>채널명: {{ video.video_channelName }}</div>
    <b-card-body>
      <b-card-title>{{ video.video_title }}</b-card-title>
      <div>
        <b-embed type="iframe" aspect="16by9" :src="`https://youtube.com/embed/${this.video.video_id}`" allowfullscreen></b-embed>
      </div>
    </b-card-body>
    <!-- <div>링크: {{ video.video_url }}</div> -->
    <div>조회수 : {{ video.video_viewCnt }}</div>
    <div>좋아요 수: {{ video.video_zzimCnt }}</div>

    <button class="btn" @click="registComment">등록</button>


  </div>
</template>

<script>
import { mapState } from "vuex";
export default {
  name: "VideoDetail",
 
  methods: {
    registComment() {
      this.$store.state.post = null;
      this.$router.push("/video/comment/regist");
    }
  },
  computed: {
    ...mapState(["video"]),
  },
  created() {
        const pathName = new URL(document.location).pathname.split("/");
    const video_id = pathName[pathName.length - 1];
    this.$store.dispatch("setVideo", video_id);
  },
};
</script>