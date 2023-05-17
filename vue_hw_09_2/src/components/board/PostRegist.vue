<template>
  <div class="container">
    <h2>글등록</h2>
    <fieldset class="text-center">
      <select name="post_board_id" v-model="post.post_board_id">
        <option :value="1">공지</option>
        <option :value="2">자유</option>
        <option :value="3">정보</option>
        <option :value="4" selected>식단</option>
      </select>
      <label for="post_title">제목</label>
      <input type="text" id="post_title" v-model="post.post_title" class="view" /><br />
      <label for="post_content">내용</label>
      <textarea id="post_content" v-model="post.post_content" class="view" /><br />
      <button class="btn" @click="regist">등록</button>
    </fieldset>

  </div>
</template>
<script>
import { mapState } from "vuex";
export default {
  name: "PostRegist",

  methods: {
    regist() {
      if (
        this.post.post_title === "" ||
        this.post.post_content === ""
      ) {
        alert("모든 내용을 입력해주세요");
        return;
      }

      this.post.post_writer_id = this.loginUser.user_id

      this.$store.dispatch("createPost", this.post);
    },
  },
  computed: {
    ...mapState(["loginUser"]),
    ...mapState(["post"]),
  },
};
</script>