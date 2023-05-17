<template>
    <div class="container">
      <h2>글 상세</h2>
      <div>제목: {{post.post_title }}</div>
      <div>내용: {{post.post_content }}</div>
      <div>닉네임: {{post.post_writer_nickname }}</div>
      <div>작성일: {{post.post_created_at }}</div>
      <div>조회수: {{post.post_view_cnt }}</div>
  
        <router-link to="/board/regist" class="btn" @click="updatePost">수정</router-link>
        <button class="btn" @click="deletePost">삭제</button>
      
    </div>
</template>

  <script>
    import { mapState } from "vuex";
    export default {
    name: "PostDetail",
    methods: {
      updatePost() {
        this.$store.dispatch("updateUser", this.loginUser);
      },
      deletePost() {
        this.$store.dispatch("deletePost");
      },
    },
    computed: {
      ...mapState(["post"]),
      },
    created() {
      const pathName = new URL(document.location).pathname.split("/");
      const post_num = pathName[pathName.length - 1];
      this.$store.dispatch("setPost", post_num);
    },
  };
  </script>