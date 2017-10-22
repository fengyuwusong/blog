/**
 * 全部标签页
 * Created by xiaojia on 2017/10/11.
 */
var tags = {
    data: {
        tags: null,
    },
    id: null,
    URL: {
        tag: function () {
            return "/tag";
        },
        deleteTag: function () {
            return "/tag/" + tags.id;
        },
    },
    getTags: function () {
        $.get(tags.URL.tag(), {}, function (result) {
            console.log(result);
            if (result.code !== 200) {
                alert(result.message);
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
    tagToJson: function () {
        const tag = {
            id: tags.id,
            tagName: $("#tagName").val(),
        };
        return JSON.stringify(tag);
    },
    updateTag: function () {
        $.ajax({
            url: tags.URL.tag(),
            contentType: "application/json; charset=utf-8",
            type: 'PUT',
            data: tags.tagToJson(),
            success: function (result) {
                if (result.code !== 200) {
                    alert(result.message);
                }
                //    关闭modal 重新加载页面
                $("#myModal").modal('hide');
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
                getId: function (id) {
                    tags.id = id;
                },
                //    删除标签
                deleteTag: function () {
                    tags.deleteTag();
                },
                //更新标签
                editTag: function () {
                    tags.updateTag();
                }
            }
        })
    }
}