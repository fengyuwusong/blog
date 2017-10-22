/**
 * 全部文章页
 * Created by fengyuwusong on 2017/10/9.
 */
var list = {
    data: {
        articles: null,
        cid: null,
        tid: null,
        deleteId: null,
    },
    URL: {
        getArticles: function (pageNum) {
            return "/admin/article/" + pageNum;
        },
        deleteArticle: function () {
            return "/admin/article/" + list.data.deleteId;
        }
    },
    getArticles: function (pageNum) {
        $.get(list.URL.getArticles(pageNum), {}, function (res) {
            list.data.articles = null;
            list.data.articles = res.data;
        })
    },
    deleteArticle: function (pageNum) {
        $.ajax({
            url: list.URL.deleteArticle(),
            type: 'DELETE',
            success: function (result) {
                if (result.code !== 200) {
                    alert(result.message);
                }
                //    关闭modal 重新加载页面
                $("#deleteModal").modal('hide');
                list.getArticles(pageNum);
            }
        });
    },


    init: function () {
        list.getArticles(1);
        //编辑
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
                //    获取删除文章id并赋值到data中
                getDeleteId: function (id) {
                    list.data.deleteId = id;
                },
                //    删除文章
                deleteArticle: function (pageNum) {
                    list.deleteArticle(pageNum);
                },

                // //    编辑文章
                //     editArticle:function (id) {
                //         location.href="edit.html";
                //     }
            }
        })
    }
}
