<template>
  <li @click="clickVideo">
    <router-link :to="`/video/detail/${video.id.videoId}`">
    <img :src="video.snippet.thumbnails.high.url" />
    {{ video.snippet.title }}</router-link>
  </li>
</template>

<script>
export default {
  name: 'YoutubeVideoItem',
  props: {
    video: {
      type: Object,
      required: true,
    },
  },
  methods: {
    clickVideo() {
      this.$store.dispatch('setVideo', this.video);
    },
  },
};
</script>

<style></style>
