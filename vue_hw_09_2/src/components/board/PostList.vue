<template>
  <div class="container">
    <h2>게시글 목록</h2>
    <h4>등록된 사용자의 수 : {{ postCnt }}</h4>
    <div v-if="postCnt">
      <table class="user-list">
        <colgroup>
          <col style="width: 20%" />
          <col style="width: 40%" />
          <col style="width: 20%" />
          <col style="width: 20%" />
        </colgroup>
        <thead>
          <tr>
            <th>작성자</th>
            <th>제목</th> 
            <th>조회수</th> 
            <th>등록일</th> 
          </tr>
        </thead>
        <tbody>
          <tr v-for="(post, index) in posts" :key="index">
            <td>{{ post.post_writer_nickname }}</td>
            <td>
              <router-link :to="`/board/detail/${post.post_num}`">{{
                post.post_title
              }}</router-link>
            </td>
            <td>{{ post.post_view_cnt }}</td>
            <td>{{ post.post_created_at }}</td>
          </tr>
        </tbody>
      </table>
    </div>
    <div v-else>등록된 게시물이 없습니다.</div>
    <button v-if="loginToken"  @click="registPost">등록하기</button>
  </div>
</template>

<script>
import { mapState, mapGetters } from "vuex";


export default {
  name: "PostList",
  methods: {
    change(board_id){
      this.$store.dispatch("setPosts",board_id);
    },
    registPost(){
      this.$store.state.post ={
        post_writer_id: "",
        post_board_id: "",
        post_title: "",
        post_content: "",
      };
      this.$router.push("/board/regist");
    }
  },
  computed: {
    ...mapState(["posts"]),
    ...mapState(["loginToken"]),
    ...mapGetters(["postCnt"]),

  },
  
  watch: {
      $route(to, form) {
      if (to.path !== form.path) this.change(this.$route.params.board_id);// 게시판 이동 시 마다 주소값을 확인하여 게시판 종류에 맞는 게시글 나타냄
    },
  },
  created() {
    const pathName = new URL(document.location).pathname.split("/");
    console.log(pathName);
    const post_board_id = pathName[pathName.length - 1];
    this.$store.dispatch("setPosts",post_board_id);
  },
};
</script>

