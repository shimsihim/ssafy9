
<template>
  <div>
    <header class="site-header d-flex flex-column justify-content-center align-items-center">
            <div class="container">
                <div class="row">

                    <div class="col-lg-12 col-12 text-center">

                        <h2 class="mb-0">게시판페이지</h2>
                    </div>

                </div>
            </div>
      </header>
    <!-- <board-nav></board-nav> -->
    <router-view></router-view>
    <hr />
  </div>
</template>

<script>
//import BoardNav from "@/components/board/BoardNav.vue"

export default {
  name: "Board",
  components : {
    //BoardNav,
  }
};
</script>



