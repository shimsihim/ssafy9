<template>
  <li>
    <p4>작성자:{{ postComment.comment_writer_id}}</p4>
    <p4>작성일자:{{ postComment.comment_created_at}}</p4>
    <p4>내용:{{ postComment.comment_content}}</p4>

    <div v-if="loginUserId === postComment.comment_writer_id">
      <button class="btn" @click="updateComment">수정</button>
      <button class="btn" @click="deleteComment">삭제</button>
    </div>
    
  </li>
</template>

<script>
import { mapState } from "vuex";
export default {
  name: 'PostCommentItem',
  props: {
    postComment: {
      type: Object,
      required: true,
    },
  },
  methods: {
    updateComment() {
      this.$store.dispatch("updateComment",this.postComment)
    },
    deleteComment() {
      this.$store.dispatch("deleteComment",this.postComment.comment_num)
    },

  },
  computed: {
    ...mapState([ "loginUserId"]),
  },
};
</script>

<style>
</style>
