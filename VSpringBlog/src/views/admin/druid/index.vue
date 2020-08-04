<template>
  <div class="accept-container">
    <iframe
      id="show-iframe"
      frameborder="0"
      scrolling="auto"
      src="http://localhost:8081/druid/index.html"
    ></iframe>
  </div>
</template>

<script>
export default {
  data() {
    return {
     
    };
  },
  mounted() {
    const oIframe = document.getElementById("show-iframe");
    const deviceWidth = document.documentElement.clientWidth;
    const deviceHeight = document.documentElement.clientHeight;
    oIframe.style.width = deviceWidth + "px";
    oIframe.style.height = deviceHeight + "px";
  },
  methods: {

  },
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
</style>