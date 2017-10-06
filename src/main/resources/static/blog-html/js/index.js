/**
 * 获取数据
 * Created by fengyuwusong on 2017/10/5.
 */

var index = {
    data: {
        articles: null,
        category: null,
        tags: null,
        config: null
    },
    URL: {
        //获取文章列表
        getArticles: function (pageNum) {
            return "/article/" + pageNum;
        },
        //通过类别id获取文章列表
        getArticlesByCategory: function (pageNum, cid) {
            return "/article/category/" + pageNum + "/" + cid;
        },
        //通过tagId获取文章吧列表
        getArticlesByTag: function (pageNum, tid) {
            return "/article/tag/" + pageNum + "/" + tid;
        },
        //模糊搜索
        search: function (pageNum, keyWord) {
            return "/article/search/" + pageNum + "/" + keyWord;
        },
        //获取所有分类
        getCategory: function () {
            return "/category";
        },
        //所有tag
        getTags: function () {
            return "/tag";
        },
        //获取配置
        getConfig: function () {
            return "/config"
        }
    },
    //获取文章列表函数
    getArticleList: function (pageNum) {
        $.get(index.URL.getArticles(pageNum), {}, function (result) {
            index.data.articles = result.data;
        })
    },
//    获取所有分类函数
    getCategory: function () {
        $.get(index.URL.getCategory(), {}, function (result) {
            console.table(result.data);
            index.data.category = result.data;
        })
    },
    //获取所有tag函数
    getTags: function () {
        $.get(index.URL.getTags(), {}, function (result) {
            console.table(result.data);
            index.data.tags = result.data;
        })
    },
    //获取所有配置
    getConfig: function () {
        $.get(index.URL.getConfig(), {}, function (result) {
            console.log(result);
            index.data.config = result.data;
        })
    },
//    通过类别id获取文章列表
    getArticlesByCategory(pageNum, cid){
        $.get(index.URL.getArticlesByCategory(pageNum, cid), {}, function (result) {
            console.log(result);
            index.data.articles = result.data;
        })
    },
//    初始化
    init: function () {
        //刚加载获取第一页数据
        index.getArticleList(1);
        //加载所有分类
        index.getCategory();
        //加载所有tag
        index.getTags();
        //加载所有config
        index.getConfig();


        var vue = new Vue({
            el: '#vue-data',
            data: index.data,
            methods: {
                //    跳转页码
                changePage: function (pageNum) {
                    index.getArticleList(pageNum);
                },
                //通过分类加载
                getArticlesByCategory: function (pageNum, cid) {
                    //重新赋空值
                    index.data.article = null;
                    index.getArticlesByCategory(pageNum, cid);
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
        })
    }
}
