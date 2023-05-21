
<template>
  <div>
    <YoutubeSearch></YoutubeSearch>
    <YoutubeSearchResult></YoutubeSearchResult>
    <hr />
  </div>
</template>

<script>
//import VideoPartButton from "@/components/video/VideoPartButton.vue"
import YoutubeSearch from './YoutubeSearch.vue';
import YoutubeSearchResult from './YoutubeSearchResult.vue';

export default {
  name: "YoutubeVideo",
  components : {
    //VideoPartButton,
    YoutubeSearch,
    YoutubeSearchResult,
  }
};
</script>



