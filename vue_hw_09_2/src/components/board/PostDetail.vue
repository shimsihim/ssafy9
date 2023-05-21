<template>
  <div class="container">
    <h2>글 상세</h2>
    <div>제목: {{ post.post_title }}</div>
    <div>내용: {{ post.post_content }}</div>
    <div>닉네임: {{ post.post_writer_nickname }}</div>
    <div>작성일: {{ post.post_created_at }}</div>
    <div>조회수: {{ post.post_view_cnt }}</div>


    <!-- 좋아요 표시 아이콘 위치 -->
    <span class="h2 mb-6" @click="changeLike">
      <b-button v-if="likeChk" size="sm" style="color: black; background-color: transparent; " variant="outline-info" >
        <b-icon icon="heart-fill" aria-hidden="true" style="color: red;"></b-icon><br> 좋아요
      </b-button>
      <b-button v-else size="sm" style="color: black; background-color: transparent; " variant="outline-info" >
        <b-icon icon="heart" aria-hidden="true"></b-icon><br> 좋아요
      </b-button>
    </span>

    <!-- 작성자면 수정 삭제버튼 보이게 해줌 -->
    <div v-if="loginUserId === post.post_writer_id">
      <router-link to="/board/regist" class="btn">수정</router-link>
      <!-- 현재 store의 post는 해당 게시글로 저장되어있음 , regist페이지로 넘어가서 그대로 바인딩 시켜 현재의 글 내용 보여줌 -->
      <button class="btn" @click="deletePost">삭제</button>
    </div>

    <comment-regist :post="post"></comment-regist>
    <h2>댓글 상세</h2>

    <ul class="post-comment-list">
      <post-comment-item v-for="postComment in this.postComments" :key="postComment.comment_num"
        :postComment="postComment"></post-comment-item>
    </ul>


  </div>
</template>

<script>
import { mapState } from "vuex";
import PostCommentItem from "./PostCommentItem.vue";
import CommentRegist from "./CommentRegist.vue";

export default {
  name: "PostDetail",
  data(){
    return{
      post_num:0,
    }
  },
  methods: {
    changeLike(){
      this.$store.dispatch("likeChkReverse",this.post_num);
    },
    deletePost() {
      this.$store.dispatch("deletePost");
    },
  },
  components: {
    PostCommentItem,
    CommentRegist
  },
  computed: {
    ...mapState(["post", "postComments", "loginUserId","likeChk"]),
  },
  created() {
    const pathName = new URL(document.location).pathname.split("/");
    const post_num = pathName[pathName.length - 1];
    this.post_num = post_num
    this.$store.dispatch("setPost", post_num);
    this.$store.dispatch("setPostComments", post_num);
    this.$store.dispatch("setPostLike",post_num);
  },
};
</script>