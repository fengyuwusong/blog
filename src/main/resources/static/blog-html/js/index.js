/**
 * 获取数据
 * Created by fengyuwusong on 2017/10/5.
 */

var index = {
    currentPage: 1,
    data: {
        articles: null,
        test: null
    },
    URL: {
        //获取文章列表
        getArticles: function () {
            return "/article/" + index.currentPage;
        },
        //通过类别id获取文章列表
        getArticlesByCategory: function (cid) {
            return "/article/category/" + index.currentPage + "/" + cid;
        },
        //通过tagId获取文章吧列表
        getArticlesByTag: function (tid) {
            return "/article/tag/" + index.currentPage + "/" + tid;
        },
        //模糊搜索
        search: function (keyWord) {
            return "/article/search/" + index.currentPage + "/" + keyWord;
        }
    },
    //获取文章列表函数
    getArticleList: function () {
        $.get(index.URL.getArticles(), {}, function (result) {
            console.table(result.data['list']);
            index.data.articles = result.data
        })
    },

//    初始化
    init: function () {
        index.getArticleList();
        var vue = new Vue({
            el: '#vue-data',
            data: index.data
        });
    }
}
