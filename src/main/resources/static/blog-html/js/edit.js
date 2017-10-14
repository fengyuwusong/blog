/**
 * Created by fengyuwusong on 2017/10/9.
 */

var edit = {
    //编辑器实例
    editor: null,
    data: null,
    URL: {
        getArticle: function (id) {
            return "/admin/article/read/" + id;
        }
    },
//    获取id参数
    GetQueryString: function (name) {
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
        var r = window.location.search.substr(1).match(reg);
        if (r != null)return unescape(r[2]);
        return null;
    },
//    获取文章
    getArticle: function (id) {
        $.get(edit.URL.getArticle(id), {}, function (res) {
            console.log(res);
            edit.data = res.data;
            edit.edit(res.data.content);
        })
    },
//    调用编辑器
    edit: function (content) {
        //实例化editor编辑器
        edit.editor = editormd("editormd", {
            width: "1000px",
            height: 640,
            syncScrolling: "single",
            path: "../lib/",
            markdown: content
        });
    },
//    初始化方法
    init: function () {
        //判断是否含有参数
        var id = edit.GetQueryString("id");
        //ajax加载
        if (id !== null) {
            edit.getArticle(id);
        } else {
            edit.edit("");
        }

        $("#submit").on("click", function (e) {
            //弹出菜单 确定分类
            alert(editor.getHTML());
        })
    }
};
