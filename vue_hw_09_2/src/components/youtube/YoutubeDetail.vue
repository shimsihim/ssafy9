<template>
  <div class="container">

  <h2>영상 상세</h2>
    <div>제목: {{ video.snippet.title }}</div>
    <div>채널명: {{ video.snippet.channelTitle }}</div>
    <b-card-body>
      <b-card-title>{{ video.snippet.title }}</b-card-title>
      <div>
        <b-embed type="iframe" aspect="16by9" :src="`https://youtube.com/embed/${video.id.videoId}`" allowfullscreen></b-embed>
      </div>
    </b-card-body>
    <!-- <div>링크: {{ video.video_url }}</div> 
    <div>조회수 : {{ video.video_viewCnt }}</div>
    <div>좋아요 수: {{ video.video_zzimCnt }}</div> -->

    <youtube-comment-regist :video="video"></youtube-comment-regist>

    <h2>댓글 목록</h2>
    <ul class="youtube-comment-list">
      <youtube-video-comment-item
        v-for="videoComment in this.videoComments"
        :key="videoComment.videocomment_num"
        :videoComment="videoComment"
      ></youtube-video-comment-item>
    </ul> 

  </div>
</template>

<script>
import { mapState } from "vuex";
import YoutubeCommentRegist from "./YoutubeCommentRegist.vue";
import YoutubeVideoCommentItem from "./YoutubeVideoCommentItem.vue";

export default {
    name: "YoutubeDetail",
    
    components: {
      YoutubeCommentRegist,
      YoutubeVideoCommentItem,
    },
    computed: {
        ...mapState(["video","videoComments"]),
    },
    created() {
        const pathName = new URL(document.location).pathname.split("/");
        const video_id = pathName[pathName.length - 1];
        this.$store.dispatch("setVideo", video_id);
        this.$store.dispatch("setVideoComments", video_id);
    },
};
</script>