<template>
  <div class="container">
    <h2>회원 가입</h2>
    <fieldset class="text-center">
      <label for="user_id">아이디</label>
      <input type="text" id="user_id" v-model="user_id" class="view" /><button @click="idCheck">중복확인</button><br />
      <label for="user_pw">비밀번호</label>
      <input
        type="password"
        id="user_pw"
        v-model="user_pw"
        class="view"
      /><br />
      <label for="user_pw_chk">비밀번호 확인</label>
      <input
        type="password"
        id="user_pw_chk"
        v-model="user_pw_chk"
        class="view"
      /><br /><span v-if="chk">일치</span><span v-else>비밀번호를 확인해라</span><br>


      <label for="user_name">이름</label>
      <input type="text" id="user_name" v-model="user_name" class="view" /><br />

      <label for="user_email">이메일</label>
      <input type="email" id="user_email" v-model="user_email" class="view" /><br />
      
      <label for="user_nickname">닉네임</label>
      <input type="text" id="user_nickname" v-model="user_nickname" class="view" /><br />
      <!-- <label for="age">나이</label>
      <input type="number" id="age" v-model="age" class="view" /><br /> -->
      <button class="btn" @click="regist">등록</button>
      <button class="btn" @click="getInfoFromAPI">랜덤</button>
    </fieldset>
  </div>
</template>
<script>
import { mapState } from "vuex";
export default {
  name: "UserList",
  data() {
    return {
      user_id: "",
      user_pw: "",
      user_name: "",
      user_email: "",
  
      user_nickname: "",
      user_pw_chk : "",
      //age: 0,
      //img: "",
      chk : false,
    
    };
  },
  methods: {
    async getInfoFromAPI() {
      await this.$store.dispatch("setRandomUser");
      this.id = this.randomUser.id;
      this.password = this.randomUser.password;
      this.name = this.randomUser.name;
      this.email = this.randomUser.email;
      //this.age = this.randomUser.age;
    },

    idCheck(){
      this.$store.dispatch("selectId", this.user_id);
      window.setTimeout(1000)
      console.log(this.idChk) // 현재 비동기 처리로 인해서 지금의 콘솔이 먼저 뜸
      
    },


    regist() {
      if (
        this.id === "" ||
        this.password === "" ||
        this.name === "" ||
        this.email === ""
      ) {
        alert("모든 내용을 입력해주세요");
        return;
      }

      let user = {
        user_id: this.user_id,
        user_pw: this.user_pw,
        user_name: this.user_name,
        user_email: this.user_email,
        user_phone: this.user_phone,
        user_nickname: this.user_nickname,
        //age: this.age,
        //img: "#",
      };

      this.$store.dispatch("createUser", user);
    },
  },
  computed: {
    ...mapState(["randomUser"]),
    ...mapState(["idChk"]),

  },
  watch : {
    user_pw_chk(){
      if(this.user_pw_chk===this.user_pw){
        this.chk = true;
      }
      else{
        this.chk = false;
      }
    },
  },
};
</script>
