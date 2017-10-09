/**
 * 全部文章页
 * Created by fengyuwusong on 2017/10/9.
 */
var list = {
    data: {
        articles: {1: "123"},
        cid: null,
        tid: null
    },
    URL: {
        getArticles: function (pageNum) {
            return "/admin/article/" + pageNum;
        }
    },
    getArticles: function (pageNum) {
        $.get(list.URL.getArticles(pageNum), {}, function (res) {
            console.log(res.data);
            list.data.articles = res.data
        })
    },
    init: function () {
        list.getArticles(1);
        var vue = new Vue({
            el: "#vue-data",
            data: list.data,
            methods: {
                //    跳转页码
                changePage: function (pageNum) {
                    if (list.data.cid === null && list.data.tid === null) {
                        list.getArticles(pageNum);
                    }
                    // else if (list.data.cid !== null && list.data.tid === null) {
                    //     list.getArticlesByCategory(pageNum, list.data.cid);
                    // } else if (list.data.cid === null && list.data.tid !== null) {
                    //     list.getArticlesByTag(pageNum, list.data.tid);
                    // }
                },
            }
        })
    }
}
