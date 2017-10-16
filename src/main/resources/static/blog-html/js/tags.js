/**
 * 全部标签页
 * Created by xiaojia on 2017/10/11.
 */
var tags={
    data:{
        tags: null,
        deleteId: null
    },
    URL: {
        getTags: function () {
            return "/tag" ;
        },
        deleteTag: function () {
            return "/tag/" + tags.data.deleteId;
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
    deleteTag: function () {
        $.ajax({
            url: tags.URL.deleteTag(),
            type: 'DELETE',
            success: function (result) {
                if (result.code !== 200) {
                    alert(result.message);
                }
                //    关闭modal 重新加载页面
                $("#deleteModal").modal('hide');
                tags.getTags();
            }
        });
    },
    init: function () {
        tags.getTags();
        // console.log(tags);
        var vue = new Vue({
            el: "#vue-data",
            data: tags.data,
            methods: {
                //    获取删除标签id并赋值到data中
                getDeleteId: function (id) {
                    tags.data.deleteId = id;
                },
                //    删除文章
                deleteTag: function () {
                    tags.deleteTag();
                },
            }
        })
    }
}