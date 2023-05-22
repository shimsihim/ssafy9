import Vue from "vue";
import VueRouter from "vue-router";
import Home from "../views/Home.vue";
import LoginForm from "@/components/LoginForm.vue";
import User from "../views/User.vue";
import UserList from "@/components/user/UserList.vue";
import UserRegist from "@/components/user/UserRegist.vue";
import UserDetail from "@/components/user/UserDetail.vue";
import Board from "../views/Board.vue";
import PostList from "@/components/board/PostList.vue";
import PostRegist from "@/components/board/PostRegist.vue";
import PostDetail from "@/components/board/PostDetail.vue";
import Youtube from "../views/Youtube.vue";
import YoutubeVideo from "@/components/youtube/YoutubeVideo.vue";
import YoutubeDetail from "@/components/youtube/YoutubeDetail.vue";
import YoutubeCommentRegist from "@/components/youtube/YoutubeCommentRegist.vue"
import CalendarItem from "@/components/calendar/CalendarItem.vue"
import Record from "@/views/Record.vue"

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "Home",
    component: Home,
  },
  {
    path: "/video",
    name: "Youtube",
    component: Youtube,
    children:[
      {
        path: "main",
        name: "YoutubeVideo",
        component: YoutubeVideo,
      },
      {
        path: "detail/:video_id",
        name: "YoutubeDetail",
        component: YoutubeDetail,
      },
      {
        path: "comment/regist",
        name: "YoutubeCommentRegist",
        component: YoutubeCommentRegist,
      },
    ]
  },
  {
    path: "/record",
    component: Record,
    children: [
      {
        path: "",
        name: "CalendarItem",
        component: CalendarItem,
      },
    ]
  },
  {
    path: "/login",
    name: "Login",
    component: LoginForm,
  },

  {
    path: "/board",
    name: "Board",
    component: Board,
    children:[
      {
        path: "list/:board_id",
        name: "PostList",
        component: PostList,
      },
      {
        path: "regist",
        name: "PostRegist",
        component: PostRegist,
      },
      {
        path: "detail/:post_num",
        name: "PostDetail",
        component: PostDetail,
      },
    ]
  },

  {
    path: "/user",
    component: User,
    children: [
      {
        path: "/regist",
        name: "Regist",
        component: UserRegist,
      },
      {
        path: "",
        name: "List",
        component: UserList,
      },
      {
        path: "/:id",
        name: "Detail",
        component: UserDetail,
      },
      {
        path: "/mypage",
        name: "UserMyPage",
        component: UserDetail,
      },
    ],
  },
  
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
