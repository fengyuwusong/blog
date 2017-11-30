/**
 * 分类js
 * Created by fengyuwusong on 2017/10/17.
 */
var classify = {
    data: {
        category: null,
    },
    nullFlag: 0,
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
    addCategoryToJson: function () {
        const category = {
            id: null,
            categoryName: $("#addCategoryName").val(),
            description: $("#addDescription").val(),
        };
        return JSON.stringify(category);
    },
    //进行判空操作
    checkNull: function (val, output) {
        if (val === "" || val === undefined || val === null) {
            alert(output);
            classify.nullFlag = 1;
        }
    },
    updateCheck: function () {
        classify.nullFlag = 0;
        classify.checkNull($("#categoryName").val(), "请填写新的分类~");
        classify.checkNull($('#description').val(), "请填写新的描述~");
    },
    addCheck: function () {
        classify.nullFlag = 0;
        classify.checkNull($("#addCategoryName").val(), "请填写分类~");
        classify.checkNull($('#addDescription').val(), "请填写描述~");
    },
    updateCategory: function () {
        classify.updateCheck();
        if (classify.nullFlag === 1) {
            return
        }
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
    addCategory: function () {
        classify.addCheck();
        if (classify.nullFlag === 1) {
            return
        }
        $.ajax({
            url: classify.URL.category(),
            contentType: "application/json; charset=utf-8",
            type: 'POST',
            data: classify.addCategoryToJson(),
            success: function (result) {
                if (result.code !== 200) {
                    alert(result.message);
                }
                //    关闭modal 重新加载页面
                $("#addModal").modal('hide');
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
                //    获取删除、编辑标签id并赋值到data中
                getId: function (id) {
                    classify.id = id;
                    $.map(classify.data.category, function (c) {
                        if (c.id === id) {
                            $("#categoryName").val(c.categoryName);
                            $("#description").val(c.description);
                            return
                        }
                    });
                },
                //    删除标签
                deleteCategory: function () {
                    classify.deleteCategory();
                },
                //更新标签
                editCategory: function () {
                    classify.updateCategory();
                },
                //添加
                addCategory: function () {
                    classify.addCategory();
                }
            }
        })
    }
};