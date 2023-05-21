<template>
  <li>
    <p4>작성자:{{ postComment.comment_writer_id}}</p4>
    <p4>작성일자:{{ postComment.comment_created_at}}</p4>
    <p4>내용:{{ postComment.comment_content}}</p4>

    <div v-if="loginUserId === postComment.comment_writer_id">
      
      <div v-if="showUpdateForm">
      <form>
      <label for="comment_content">내용</label>
      <textarea
        id="comment_content"
        v-model="comment_content"
        class="view"
      ></textarea>
      <button @click="updateComment">수정완료</button>
    </form>
    </div>

    <button class="btn" @click="showForm" >수정</button>
      <button class="btn" @click="deleteComment">삭제</button>
    </div>
    
  </li>
</template>

<script>
import { mapState } from "vuex";
export default {
  name: 'PostCommentItem',
  data() {
    return{
      showUpdateForm : false,
      comment_content : this.postComment.comment_content,
    };
  },
  props: {
    postComment: {
      type: Object,
      required: true,
    },
  },
  methods: {
    showForm() {
      this.showUpdateForm = true;
    },
    updateComment() {
      this.postComment.comment_content = this.comment_content;
      this.$store.dispatch("updateComment",this.postComment);
      this.showUpdateForm = false;
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
