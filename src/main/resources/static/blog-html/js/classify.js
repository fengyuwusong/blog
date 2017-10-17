/**
 * 分类js
 * Created by fengyuwusong on 2017/10/17.
 */
var classify = {
    data: {
        category: null,
    },
    id: null,
    URL: {
        category: function () {
            return "/category";
        },
        deleteCategory: function () {
            return "/category/" + classify.id;
        },
    },
    getCategory: function () {
        $.get(classify.URL.category(), {}, function (result) {
            console.log(result);
            if (result.code !== 200) {
                alert(result.message);
            }
            classify.data.category = result.data;

        })
    },
    deleteCategory: function () {
        $.ajax({
            url: classify.URL.deleteCategory(),
            type: 'DELETE',
            success: function (result) {
                if (result.code !== 200) {
                    alert(result.message);
                }
                //    关闭modal 重新加载页面
                $("#deleteModal").modal('hide');
                classify.getCategory();
            }
        });
    },
    categoryToJson: function () {
        const category = {
            id: classify.id,
            categoryName: $("#categoryName").val(),
            description: $("#description").val(),
        };
        return JSON.stringify(category);
    },
    updateCategory: function () {
        $.ajax({
            url: classify.URL.category(),
            contentType: "application/json; charset=utf-8",
            type: 'PUT',
            data: classify.categoryToJson(),
            success: function (result) {
                if (result.code !== 200) {
                    alert(result.message);
                }
                //    关闭modal 重新加载页面
                $("#myModal").modal('hide');
                classify.getCategory();
            }
        });
    },
    init: function () {
        classify.getCategory();
        const vue = new Vue({
            el: "#vue-data",
            data: classify.data,
            methods: {
                //    获取删除标签id并赋值到data中
                getId: function (id) {
                    classify.id = id;
                },
                //    删除标签
                deleteCategory: function () {
                    classify.deleteCategory();
                },
                //更新标签
                editCategory: function () {
                    classify.updateCategory();
                }
            }
        })
    }
};