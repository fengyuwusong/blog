/**
 * 文章展示
 * Created by fengyuwusong on 2017/10/17.
 */
const message = {
    id: null,
    data: {
        article: null,
        config: null
    },
    URL: {
        getArticle: function () {
            return "/article/read/" + message.id;
        },
        //获取配置
        getConfig: function () {
            return "/config"
        }
    },

    //获取所有配置
    getConfig: function () {
        $.get(message.URL.getConfig(), {}, function (result) {
            message.data.config = result.data;
        })
    },

    //获取id参数
    GetQueryString: function (name) {
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
        var r = window.location.search.substr(1).match(reg);
        if (r != null)return unescape(r[2]);
        return null;
    },
    //获取文章
    getArticle: function () {
        $.get(message.URL.getArticle(), {}, function (res) {
            message.data.article = res.data;
            if (res.data.content.length < 60) {
                $(".footer").css("position", "fixed");
            }
            message.editor(res.data.content);
        })
    },

    //editor编辑器
    editor: function (content) {
        let testEditormdView;
        testEditormdView = editormd.markdownToHTML("test-editormd-view", {
            markdown: content,//+ "\r\n" + $("#append-test").text(),
            //htmlDecode      : true,       // 开启 HTML 标签解析，为了安全性，默认不开启
            htmlDecode: "style,script,iframe",  // you can filter tags decode
            //toc             : false,
            tocm: true,    // Using [TOCM]
            tocContainer: "#custom-toc-container", // 自定义 ToC 容器层
            //gfm             : false,
            //tocDropdown     : true,
            // markdownSourceCode : true, // 是否保留 Markdown 源码，即是否删除保存源码的 Textarea 标签
            emoji: true,
            taskList: true,
            tex: true,  // 默认不解析
            flowChart: true,  // 默认不解析
            sequenceDiagram: true,  // 默认不解析
        });

    },
    //初始化
    init: function () {
        message.id = message.GetQueryString("id");
        message.getConfig();
        message.getArticle();

        if (message.id !== null) {
            message.getArticle();
            let vue = new Vue({
                el: '#vue-data',
                data: message.data,
                methods: {
                    topTop: function () {
                        //回到顶部
                        $("html,body").animate({scrollTop: 0}, 300);
                        return false;
                    }
                }
            });
            //    vue 格式化时间
            Vue.filter('time', function (value) {
                function add0(m) {
                    return m < 10 ? '0' + m : m
                }

                var time = new Date(parseInt(value));
                var y = time.getFullYear();
                var m = time.getMonth() + 1;
                var d = time.getDate();
                return y + '.' + add0(m) + '.' + add0(d);
            });
        } else {
            alert("id 不能为空！");
        }

    }

};