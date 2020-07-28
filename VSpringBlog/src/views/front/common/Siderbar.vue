<template>
  <el-col :md="7" :lg="6" :xl="1" class="hidden-sm-and-down">
    <div id="siderBar-main">
      <div class="side-about">
        <div class="about-me">
          <p>
            <i style="color: #ff0000;" class="fa fa-bookmark" aria-hidden="true"></i>&nbsp;&nbsp;关于博主
          </p>
        </div>
        <div>
          <div class="me-avatar">
            <div class="ava2wx">
              <a id="logoUrl" style="opacity: 1;" href>
                <img src="https://q.qlogo.cn/g?b=qq&amp;nk=892903912&amp;s=100" />
              </a>
              <a id="wechatqrcode" style="margin-top: -80px; opacity: 0;" href>
                <img src />
              </a>
            </div>
          </div>
          <div class="desc">
            <p></p>
          </div>
        </div>
        <div class="social">
          <div class="weixin" id="weixin" style="border-right: 1px solid rgba(0,0,0,.04)">
            <i class="fa fa-weixin" aria-hidden="true"></i>
          </div>
          <div class="tweibo" style="border-right: 1px solid rgba(0,0,0,.04)">
            <a class="tweibo-a" href target="_blank">
              <i class="fa fa-github" aria-hidden="true"></i>
            </a>
          </div>
          <div class="qq" style="border-right: 1px solid rgba(0,0,0,.04)">
            <a class="qq-a" href target="_blank">
              <i class="fa fa-qq" aria-hidden="true"></i>
            </a>
          </div>
          <div class="weibo">
            <a class="weibo-a" href target="_blank">
              <i class="fa fa-weibo" aria-hidden="true"></i>
            </a>
          </div>
        </div>
      </div>

      <!-- 推荐阅读 -->
      <transition name="el-fade-in-linear">
        <div id="other-sider">
          <div class="side-about">
            <div class="about-me">
              <p>
                <i style="color: #ff0000;" class="fa fa-bookmark" aria-hidden="true"></i>&nbsp;&nbsp;推荐阅读
              </p>
            </div>
            <div class="recommend">
              <div class="recommend-top">
                <div class="arc-title">
                  <a href>MQ(消息队列)常见的应用场景解析</a>
                </div>
              </div>
              <div class="recommend-bottom">
                <div class="arc-tags color-pink">docker</div>
                <div class="arc-tags color-blue">大撒大撒</div>
                <div class="arc-tags color-pink">a撒大苏打></div>
              </div>
              <div class="other">
                <div class="arc-time" style="float:left;width:200px;">2020-07-17 16:56:00</div>
                <i class="fa fa-eye"></i>
                <i class="fa fa-pencil-square-o"></i>
                <i class="fa fa-heart-o"></i>
              </div>
            </div>

            <div class="recommend">
              <div class="recommend-top">
                <div class="arc-title">
                  <a href>MQ(消息队列)常见的应用场景解析</a>
                </div>
              </div>
              <div class="recommend-bottom">
                <div class="arc-tags color-pink">docker</div>
                <div class="arc-tags color-pink">大撒大撒</div>
                <div class="arc-tags color-blue">a撒大苏打></div>
              </div>
              <div class="other">
                <div class="arc-time" style="float:left;width:200px;">2020-07-17 16:56:00</div>
                <i class="fa fa-eye"></i>
                <i class="fa fa-pencil-square-o"></i>
                <i class="fa fa-heart-o"></i>
              </div>
            </div>

            <div class="recommend">
              <div class="recommend-top">
                <div class="arc-title">
                  <a href>MQ(消息队列)常见的应用场景解析</a>
                </div>
              </div>
              <div class="recommend-bottom">
                <div class="arc-tags color-pink">docker</div>
                <div class="arc-tags color-pink">大撒大撒</div>
                <div class="arc-tags color-blue">a撒大苏打></div>
              </div>
              <div class="other">
                <div class="arc-time" style="float:left;width:200px;">2020-07-17 16:56:00</div>
                <i class="fa fa-eye"></i>
                <i class="fa fa-pencil-square-o"></i>
                <i class="fa fa-heart-o"></i>
              </div>
            </div>

            <div class="recommend">
              <div class="recommend-top">
                <div class="arc-title">
                  <a href>MQ(消息队列)常见的应用场景解析</a>
                </div>
              </div>
              <div class="recommend-bottom">
                <div class="arc-tags color-pink">docker</div>
                <div class="arc-tags color-pink">大撒大撒</div>
                <div class="arc-tags color-blue">a撒大苏打></div>
              </div>
              <div class="other">
                <div class="arc-time" style="float:left;width:200px;">2020-07-17 16:56:00</div>
                <i class="fa fa-eye"></i>
                <i class="fa fa-pencil-square-o"></i>
                <i class="fa fa-heart-o"></i>
              </div>
            </div>
          </div>
        </div>
      </transition>
    </div>
  </el-col>
</template>

<script>
export default {
  name: "siderBar",
};
</script>
<style>
</style>
<style scoped>
.isFixed {
  position: fixed;
  background-color: #fff;
  top: 44px;
  z-index: 999;
}
.side-about {
  background: #fff;
  border-radius: 5px;
  box-shadow: 0 0px 10px rgba(0, 0, 0, 0.05);
  margin-bottom: -1px;
}
.about-me,
.links,
.newcomments_title,
.newposts_title {
  padding: 10px 15px;
  border-bottom: 1px solid #f4f4f4;
}
.about-me {
  display: flex;
  justify-content: space-between;
}
.about-me p,
.links p,
.newcomments_title p,
.newposts_title p {
  font-size: 12px;
  color: #666;
  text-shadow: 0 1px 3px rgba(88, 88, 88, 0.1);
}
p {
  margin: 0;
  padding: 0;
  line-height: 20px;
  text-shadow: 0 1px 3px rgba(88, 88, 88, 0.1);
}
.me-avatar {
  overflow: hidden;
  padding: 25px;
  text-align: center;
}
.ava2wx {
  max-height: 90px;
  padding: 5px 0;
  overflow: hidden;
}
.ava2wx a {
  width: 80px;
  margin: 0 auto;
}
#logoUrl,
#wechatqrcode {
  transition: opacity 0.4s ease-out, transform 0.4s ease-out;
  -webkit-transition: opacity 0.4s ease-out, transform 0.4s ease-out;
  -moz-transition: opacity 0.4s ease-out, transform 0.4s ease-out;
  -ms-transition: opacity 0.4s ease-out, transform 0.4s ease-out;
  -o-transition: opacity 0.4s ease-out, transform 0.4s ease-out;
}
#logoUrl img {
  width: 80px;
  height: 80px;
  border-radius: 50%;
  box-shadow: 0 0px 10px rgba(0, 0, 0, 0.45);
}
.desc p {
  padding: 0px 20px 30px 20px;
  font-size: 12px;
  text-align: justify;
}
.social {
  background: #fff;
  border-radius: 0 0 5px 5px;
  box-shadow: 0 0px 10px rgba(0, 0, 0, 0.05);
  margin-bottom: 20px;
  display: flex;
  text-align: center;
}
.social a {
  color: black;
}
@media screen and (min-width: 980px) {
  .weixin,
  .tweibo,
  .qq,
  .weibo,
  .email {
    padding: 10px 5px;
  }
}
.weixin {
  border-radius: 0px 0px 0px 5px;
}
.weixin,
.tweibo,
.qq,
.weibo,
.email {
  flex: 0 0 25.7%;
  max-width: 25%;
  padding: 20px;
  cursor: pointer;
  transition: all 0.3s ease-in-out;
  -webkit-transition: all 0.3s ease-in-out;
  -moz-transition: all 0.3s ease-in-out;
  -ms-transition: all 0.3s ease-in-out;
  -o-transition: all 0.3s ease-in-out;
}
.tweibo:hover,
.weibo:hover,
.qq:hover {
  background: #000;
}

.qq:hover .qq-a,
.tweibo:hover .tweibo-a,
.weibo:hover .weibo-a {
  color: #fff;
}
.weixin:hover {
  background: #000;
  color: #fff;
}
/* 推荐阅读 */
.recommend {
  padding-bottom: 4px;
  border-bottom: 1px solid rgba(0, 0, 0, 0.1);
}
.recommend .arc-title {
  text-align: justify;
  font-size: 12px;
  line-height: 23px;
  margin-bottom: 5px;
  padding-top: 10px;
}
.recommend .arc-title a {
  color: #696969;
  text-decoration: none;
}
.recommend .arc-tags {
  display: inline-block;
  height: 18px;
  line-height: 16px;
  margin: 2px 4px 2px 0;
  padding: 0 4px;
  border: 1px solid #e9eaec;
  border-radius: 3px;
  background: #f7f7f7;
  font-size: 10px;
  vertical-align: middle;
  opacity: 1;
  overflow: hidden;
  cursor: pointer;
}
.recommend .color-pink {
  background: rgb(213, 43, 179);
  border-color: rgb(213, 43, 179);
  color: rgb(255, 255, 255);
}
.recommend .color-blue {
  background: rgb(81, 227, 247);
  border-color: rgb(81, 227, 247);
  color: rgb(255, 255, 255);
}
.recommend {
  padding-left: 18px;
}
.recommend .recommend-bottom {
  margin-bottom: 5px;
}
.recommend .arc-time {
  font-size: 9px;
  line-height: 18px;
  font-weight: 200;
  color: #878d99;
}
.recommend .other i {
  font-size: 9px;
  padding-right: 6px;
  cursor: pointer;
}
#siderBar-main {
  padding-left: 13px;
  position: absolute;
}
</style>
