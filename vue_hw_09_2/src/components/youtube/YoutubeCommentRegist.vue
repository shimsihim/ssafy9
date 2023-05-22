<template>
  <div class="container">
    <h2>글등록</h2>
    <fieldset class="text-center">
      <label for="videocomment_content">내용</label>
      <textarea v-if="loginToken"
        id="videocomment_content"
        v-model="videocomment_content"
        class="view"
        
      />
      <textarea v-else
        id="videocomment_content"
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
  name: "YoutubeCommentRegist",
  data() {
    return {
      videocomment_content : "",
      comment_none : "로그인 해주세요",
    };
  },
  props : {
    video: {
      type: Object,
      required: true,
    },
  },
  methods: {
    regist() {
      if (
        this.videocomment_content === "" 
      ) {
        alert("내용을 입력해주세요");
        return;
      }

      let videoComment = {
        videocomment_video_id : this.video.id.videoId,
        videocomment_content: this.videocomment_content,
      };

      this.$store.dispatch("registVideoComment", videoComment);
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