<template>
  <header>
    <nav class="board-nav">
      <div>
        <router-link to="/video/전신" >전신</router-link>
        <router-link to="/video/상체" >상체</router-link>
        <router-link to="/video/하체" >하체</router-link>
        <router-link to="/video/복부" >복부</router-link>
      </div>
    </nav>
  </header>
</template>

<script>
export default {
  name: "VideoPartButton",
};
</script>
