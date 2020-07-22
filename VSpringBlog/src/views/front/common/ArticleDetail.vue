<template>
  <div class="content">
    <div id="art-main">
      <div class="art-title">nginx反向代理解决跨域</div>
      <div class="user-des">
        <img class="user-limg" src="https://iazuresky.com/usr/uploads/2019/10/3486836713.jpg" alt />
        <div class="user-rdes">
          <div class="username">whyzaa</div>
          <div class="otherdesc">
            <span class="art-time">2019.05.22 19:32:56</span>
            <span class="art-commentscount">
              <i class="el-icon-chat-line-round"></i>123
            </span>
          </div>
        </div>
      </div>
      <el-divider></el-divider>

      <div class="articlede-body">
        <div class="markdown-body">
          <h3><a id="_0"></a>什么是跨域</h3>
<blockquote>
<p>段落引用跨域指浏览器不允许当前页面的所在的源去请求另一个源的数据。源指协议，端口，域名。只要这个3个中有一个不同就是跨域</p>
</blockquote>
<pre><code>1. 协议跨域
http://a.baidu.com 访问 https://a.baidu.com；
2. 端口跨域
http://a.baidu.com:8080 访问  http://a.baidu.com:8081；
3. 域名跨域
http://a.baidu.com 访问 http://b.baidu.com；
</code></pre>
<h3><a id="vue_10"></a>解决方案（vue）</h3>
<h4><a id="proxyTable_11"></a>proxyTable：</h4>
<p>  找到项目config目录下面的index.js文件；<br />
  以豆瓣电影api为例，在proxyTable中添加如下代码。</p>
<pre><div class="hljs"><code class="lang-javascript"><span class="hljs-string">'/mapi'</span>: {
        <span class="hljs-attr">target</span>: <span class="hljs-string">'https://api.douban.com/v2/movie/'</span>, <span class="hljs-comment">// api接口地址</span>
        changeOrigin: <span class="hljs-literal">true</span>,
        <span class="hljs-attr">pathRewrite</span>: {  <span class="hljs-comment">// 路径重写，</span>
          <span class="hljs-string">'^/mapi'</span>: <span class="hljs-string">''</span>  <span class="hljs-comment">// 替换target中的请求地址，也就是说以后你在请求http://api.douban.com/v2/movie/的时候直接写成/mapi即可。</span>
        },
        <span class="hljs-attr">onProxyReq</span>: <span class="hljs-function"><span class="hljs-keyword">function</span> (<span class="hljs-params">proxyReq, req, res</span>) </span>{
          <span class="hljs-built_in">console</span>.log(<span class="hljs-string">"原路径："</span> + req.originalUrl, <span class="hljs-string">"代理路径："</span> + req.path)
   	 }
  }
</code></div></pre>
<h3><a id="_26"></a>如何使用？</h3>
<h4><a id="moviejs_27"></a>movie.js</h4>
<pre><div class="hljs"><code class="lang-JavaScript"><span class="hljs-keyword">import</span> request <span class="hljs-keyword">from</span> <span class="hljs-string">'@/utils/request'</span>
<span class="hljs-keyword">export</span> <span class="hljs-function"><span class="hljs-keyword">function</span> <span class="hljs-title">getMovieList</span>(<span class="hljs-params">url,params</span>) </span>{
  <span class="hljs-keyword">return</span> request({
      <span class="hljs-attr">url</span>: url,
      <span class="hljs-attr">method</span>: <span class="hljs-string">'get'</span>,
      params
    })
  }
</code></div></pre>
<hr />
<h4><a id="doubanvue_39"></a>douban.vue</h4>
<pre><div class="hljs"><code class="lang-javascript"><span class="hljs-keyword">import</span> { getMovieList } <span class="hljs-keyword">from</span> <span class="hljs-string">"@/api/movie"</span>;
<span class="hljs-keyword">export</span> <span class="hljs-keyword">default</span> {
  <span class="hljs-attr">name</span>: <span class="hljs-string">"douban"</span>,
  data() {
    <span class="hljs-keyword">return</span> {
      <span class="hljs-attr">moviedata</span>: <span class="hljs-literal">null</span>,
    };
  },
  <span class="hljs-attr">methods</span>: {
    <span class="hljs-attr">getMoive</span>: <span class="hljs-function"><span class="hljs-keyword">function</span>(<span class="hljs-params">start, count</span>) </span>{
	  <span class="hljs-comment">// /mapi/top250 --&gt; http://api.douban.com/v2/movie/top250</span>
      getMovieList(<span class="hljs-string">"/mapi/top250"</span>, {
        <span class="hljs-attr">start</span>: start,
        <span class="hljs-attr">count</span>: count,
        <span class="hljs-attr">apikey</span>: <span class="hljs-string">"0df993c66c0c636e29ecbb5344252a4a"</span>
      }).then(<span class="hljs-function"><span class="hljs-params">res</span> =&gt;</span> {
        <span class="hljs-keyword">this</span>.moviedata = res.subjects;
		<span class="hljs-built_in">console</span>.log(res.subjects)
      });
    }
  }
};
</code></div></pre>
<hr />
<h4><a id="_68"></a>最后效果：</h4>
<p><img src="https://iazuresky.com/usr/uploads/2019/10/3471224184.png" alt="图片20191015100914.png" /><br />
<img src="https://iazuresky.com/usr/uploads/2019/10/1957057929.png" alt="图片1957057929.png" /></p>
<h3><a id="_71"></a>补充一点：</h3>
<pre><code>配置proxyTable的方式**仅限于本地开发环境**，nginx服务器配置跨域可以参考 ：[nginx反向代理解决跨域](https://iazuresky.com/web/51.html &quot;nginx反向代理解决跨域&quot;)</code></pre>

        </div>
        <div class="arttags"></div>
      </div>
    </div>
    <div class="metas">
      <div class="item">
        <div class="title">
          <i class="el-icon-s-flag"></i>
          <span>相关标签:</span>
        </div>
      </div>
      <div class="item">
        <div class="title">
          <i class="el-icon-position"></i>

          <span>永久地址:</span>
          <span>http://localhost:8080/#/ArticleDetail/1</span>
        </div>
      </div>
      <div class="item">
        <div class="title">
          <i class="el-icon-share"></i>
          <span>版权声明:</span>
          <span>自由转载-署名-非商业性使用 | Creative Commons BY-NC 3.0 CN 著作权归作者所有。</span>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
export default {
  name: "ArticleDetail"
};
</script>
<style>
#art-main {
  border: 1px solid #dcdee2;
  border-color: #e8eaec;
  background-color: #fff;
  overflow: hidden;
}
#art-main .art-title {
  padding-top: 30px;
  padding-bottom: 10px;
  text-align: center;
  /* border-bottom: 1px solid #f4f4f4; */
  color: #555;
  font-size: 21px;
  text-shadow: 0 1px 3px rgba(88, 88, 88, 0.2);
  line-height: 30px;
}

.user-des {
  /* padding: 10px 0; */
  padding-left: 30px;
  /* border-bottom: 1px solid #f4f4f4; */
}
.user-des .user-limg {
  display: block;
  border-radius: 50%;
  border: 1px solid #eee;
  min-width: 48px;
  min-height: 48px;
  width: 48px;
  height: 48px;
  float: left;
  margin-left: 10px;
}
.user-des .user-rdes {
  margin-left: 72px;
}
.user-rdes .username {
  font-size: 16px;
  font-weight: 400;
}
.user-rdes .art-time,
.user-rdes .art-commentscount {
  color: #969696;
  font-size: 13px;
  margin-right: 10px;
}
.user-rdes .username {
  margin-bottom: 10px;
}
.articlede-body {
  padding: 0 30px;
  word-wrap: break-word;
  /* line-height: 26px; */
}
.content {
  margin-bottom: 10px;
}
.content .metas {
  padding: 1rem;
  background-color: #fff;
}
.content .metas > .item {
  margin-bottom: 8px;
  padding-left: 1rem;
  word-break: break-all;
}
.content .metas .item .title {
  color: #555;
  font-size: 13px;
}
.content .metas .item .title span {
  padding-right: 10px;
}
.content .metas .item .title i {
  color: rgb(255, 0, 0);
  font-size: 14px;
  padding-right: 10px;
}
</style>