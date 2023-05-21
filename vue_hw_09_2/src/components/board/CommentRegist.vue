<template>
  <div class="container">
    <h2>글등록</h2>
    <fieldset class="text-center">
      <label for="comment_content">내용</label>
      <textarea v-if="loginToken"
        id="comment_content"
        v-model="comment_content"
        class="view"
      />
      <textarea v-else
        id="comment_content"
        v-model="comment_none"
        class="view"
        readonly
      /><br />      
      <button class="btn" @click="regist">등록</button>
    </fieldset>
    
  </div>
</template>
<script>
import { mapState } from "vuex";
export default {
  name: "CommentRegist",
  data() {
    return {
      comment_content : "",
      comment_none : "로그인 해주세요",
    };
  },
 
  methods: {
    regist() {
      console.log("메소드 들어감")
      if (
        this.comment_content === "" 
      ) {
        alert("내용을 입력해주세요");
        return;
      }

      let comment = {
        post_num : this.post.post_num,
        comment_content: this.comment_content,
      };

      this.$store.dispatch("registPostComment", comment);
      this.comment_content = ""
    },
  },
  computed: {
    ...mapState(["loginUser"]),
    ...mapState(["post","loginToken"]),
    // compVal: {
    //   get () {
    //     if (this.selected) {
    //       return this.videocomment_content
    //     } else {
    //       return this.comment_none
    //     }
    //   },
    //   set (val) {
    //     if (this.selected) {
    //       this.videocomment_content = val
    //     } else {
    //       this.comment_none = val
    //     }
    //   }
    // },


  },
};
</script>