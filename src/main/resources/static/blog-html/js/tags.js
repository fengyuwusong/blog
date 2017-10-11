/**
 * 全部标签页
 * Created by xiaojia on 2017/10/11.
 */
var tags={
    data:{
        tags:null
    },
    URL: {
        getTags: function () {
            return "/tag" ;
        }
    },
    getTags: function () {
        $.get(tags.URL.getTags(), {}, function (result) {
            console.log(result);
            if(result.code===-2){

            }
            tags.data.tags = result.data;

        })
    },
    init: function () {-
        tags.getTags();
        // console.log(tags);
        var vue = new Vue({
            el: "#vue-data",
            data: tags.data,
            methods: {
                //    跳转页码
                // changePage: function (pageNum) {
                //     if (list.data.cid === null && list.data.tid === null) {
                //         list.getArticles(pageNum);
                //     }
                //     else if (list.data.cid !== null && list.data.tid === null) {
                //         list.getArticlesByCategory(pageNum, list.data.cid);
                //     } else if (list.data.cid === null && list.data.tid !== null) {
                //         list.getArticlesByTag(pageNum, list.data.tid);
                //     }
                // },
            }
        })
    }
}