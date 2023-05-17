<template>
  <header>
    <b-navbar toggleable="lg" type="dark">

      <b-navbar-brand><router-link to="/" class="logo">SSAFIT</router-link></b-navbar-brand>

      <b-collapse id="nav-collapse" is-nav>

        <b-navbar-nav>

     
          <b-nav-item-dropdown right>
            <template #button-content>
              <span style="color: white;">게시판</span>
            </template>
            <b-dropdown-item class="bg-info"><router-link
                :to="{ name: 'PostList', params: { board_id: 1 } }">공지사항</router-link></b-dropdown-item>
            <b-dropdown-item class="bg-info"><router-link
                :to="{ name: 'PostList', params: { board_id: 2 } }">자유게시판</router-link></b-dropdown-item>
            <b-dropdown-item class="bg-info"><router-link
                :to="{ name: 'PostList', params: { board_id: 3 } }">운동관련정보</router-link></b-dropdown-item>
            <b-dropdown-item class="bg-info"><router-link
                :to="{ name: 'PostList', params: { board_id: 4 } }">식단관련정보</router-link></b-dropdown-item>
          </b-nav-item-dropdown>

          <b-nav-item-dropdown right>
            <template #button-content>
              <router-link :to="{ name: 'VideoList', params: { video_part: 'all' } }">비디오</router-link>
            </template>
            <b-dropdown-item class="bg-info"><router-link to="/video/전신">전신</router-link></b-dropdown-item>
            <b-dropdown-item class="bg-info"><router-link to="/video/상체">상체</router-link></b-dropdown-item>
            <b-dropdown-item class="bg-info"><router-link to="/video/하체">하체</router-link></b-dropdown-item>
            <b-dropdown-item class="bg-info"><router-link to="/video/복부">복부</router-link></b-dropdown-item>
          </b-nav-item-dropdown>

        </b-navbar-nav>

        <b-navbar-nav v-if="getUser" class="ml-auto">
          <b-nav-item><a href="#" @click="logout">로그아웃</a></b-nav-item>
          <b-nav-item><router-link to="/mypage">마이페이지</router-link></b-nav-item>
        </b-navbar-nav>

        <b-navbar-nav v-else class="ml-auto">
          <b-nav-item><router-link to="/login">로그인</router-link></b-nav-item>
          <b-nav-item><router-link :to="{ name: 'Regist' }">회원가입</router-link></b-nav-item>
        </b-navbar-nav>

      </b-collapse>
      <!-- 관리자용 사용자목록 -->
      <!-- <router-link to="/user">사용자목록</router-link> -->
    </b-navbar>
  </header>
</template>
<script>
import { mapState } from "vuex";
export default {
  name: "HeaderNav",
  methods: {
    logout() {
      this.$store.commit("LOGOUT");
      alert("로그아웃 되었습니다.");
    },
  },
  computed: {
    ...mapState(["loginUser"]),
    getUser() {
      if (this.loginUser) {
        return true;
      } else {
        return false;
      }
    },
  },
};
</script>
<style>
header {
  line-height: 70px;
}

header a {
  margin: 10px;
  text-decoration-line: none;
  color: white;
}

.header-nav {
  display: flex;
  justify-content: space-between;
}

.logo {
  display: inline-block;
  font-size: 2rem;
  font-weight: bold;
  color: white;
  margin: 0;
}
</style>
