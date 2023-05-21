<template>
  <li>
    <p4>작성자:{{ videoComment.videocomment_writer_id}}</p4>
    <p4>작성일자:{{ videoComment.videocomment_created_at}}</p4>
    <p4>내용:{{ videoComment.videocomment_content}}</p4>
  
    <div v-if="loginUserId === videoComment.videocomment_writer_id">
      <button class="btn" @click="updateComment">수정</button>
      <button class="btn" @click="deleteComment">삭제</button>
    </div>

  </li>
</template>

<script>
import { mapState } from "vuex";
export default {
  name: 'YoutubeVideoCommentItem',
  props: {
    videoComment: {
      type: Object,
      required: true,
    },
  },
  methods: {
    update() {

    },
    deleteComment() {
      this.$store.dispatch("deleteVideoComment",this.videoComment.videocomment_num,this.videoComment.videocomment_video_id);
    },

  },
  computed: {
    ...mapState([ "loginUserId"]),
  },
};
</script>

<style></style>
