<template>
  <div class="side-toc" ref="list">
    <slot name="content"></slot>
  </div>
</template>

<script>
export default {
  props: {
    isArticlePage: {
      type: Boolean,
      default: false,
    },
  },
  methods: {
    recalcActive() {
      // 先计算list相对于页面的顶部距离
      var listScrollTop =
        this.$refs.list.getBoundingClientRect().top +
        document.documentElement.scrollTop;
      // 再计算active相对于页面的顶部距离
      var activeAnode = this.$refs.list.querySelector(".active");
      if (activeAnode === null) {
        return;
      }
      var activeANodeScrollTop =
        activeAnode.getBoundingClientRect().top +
        document.documentElement.scrollTop;
      var activeIndicator = this.$refs.list.querySelector(".active-indicator");
      activeIndicator.style.top = activeANodeScrollTop - listScrollTop + "px";
      activeIndicator.style.height = activeAnode.clientHeight + "px";
    },
    beActive(event) {
      var activeANode = this.$refs.list.querySelector(".active");
      if (activeANode !== null) {
        activeANode.classList.remove("active");
      }
      event.target.classList.add("active");
      this.$nextTick(() => {
        this.recalcActive();
      });
    },
  },
  mounted: function () {
    this.recalcActive();
  },
  updated: function () {
    this.recalcActive();
  },
};
</script>
<style lang="scss" rel="stylesheet/stylus">
.side-toc {
  position: relative;
  background: #fff;

  h4 {
    font-size: 18px;
    padding: 13px 13px 0;
    line-height: 18px;
    text-align: left;
  }

  .list {
    position: relative;
    padding: 0 25px 15px;
    margin-top: 10px;

    .active-indicator {
      position: absolute;
      left: -1px;
      right: 0;
      background-color: #f9f9f9;
      border-left: 2px solid #409eff;
      z-index: 1;
      transition: all 0.2s ease-out;
    }

    ul {
      position: relative;
      padding-left: 1em;
      z-index: 2;

      > li {
        list-style-type: upper-roman;
        line-height: 2.2em;

        > a {
          cursor: pointer;
          display: block;
          height: 100%;
          width: 100%;
          margin: 0;
          padding: 0;
          font-size: 16px;
          color: #363636;
          &.active {
            font-weight: 800;
            color: #409eff;
          }
        }

        > ul > li > a {
          font-size: 16px;
          color: #828282;
        }
      }
    }
  }
}
</style>