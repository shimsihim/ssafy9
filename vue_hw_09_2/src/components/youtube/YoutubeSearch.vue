<template>
  <div>
    <h3>검색 컴포넌트</h3>
    <input type="text" v-model="keyword" />
    <button @click="search">검색</button>
  </div>
</template>

<script>
export default {
  name: 'YoutubeSearch',
  data() {
    return {
      keyword: '',
    };
  },
  methods: {
    search() {
      this.$store.dispatch('searchYoutube', this.keyword);
    },
  },
};
</script>

<style></style>
