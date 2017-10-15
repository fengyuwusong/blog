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
        },
        getTags: function () {
            return "/tag";
        },
        addArticle: function () {
            return "/admin/article";
        },
        getCategory: function () {
            return "/category";
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
//    分类使用预加载
    category: function () {
        var provinces = ["广东省", "福建省", "山西省", "山东省", "湖北省", "湖南省", "陕西省", "上海市", "北京市", "广西省"];
        var substringMatcher = function (strs) {
            return function findMatches(q, cb) {
                var matches, substrRegex;
                matches = [];
                substrRegex = new RegExp(q, 'i');
                $.each(strs, function (i, str) {
                    if (substrRegex.test(str)) {
                        matches.push({value: str});
                    }
                });
                cb(matches);
            };
        };

        $('#category').typeahead({
                hint: true,
                highlight: true,
                minLength: 1
            },
            {
                name: 'provinces',
                displayKey: 'value',
                source: substringMatcher(provinces)
            });


        // $("#category").typeahead({
        //     source: function (query, process) {
        //         $.get(edit.URL.getCategory(), {}, function (res) {
        //             console.log(res);
        //             return res.data;
        //         });
        //     }
        // });
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

        edit.category();


        $("#submit").on("click", function (e) {
            //弹出菜单 确定分类
            alert(editor.getHTML());
        });


        //todo  表单预加载
        //远程获取数据
        // var tags = new Bloodhound({
        //     datumTokenizer: Bloodhound.tokenizers.obj.whitespace('tagName'),
        //     queryTokenizer: Bloodhound.tokenizers.whitespace,
        //     prefetch: {
        //         url: edit.URL.getTags(),
        //         filter: function (res) {
        //             console.log(res);
        //
        //             var r= $.map(res.data, function(tag) {
        //                 return { tagName: tag.tagName }; });
        //
        //             console.log(r);
        //             return r;
        //         }
        //     }
        // });
        // tags.initialize();
        //
        // var elt = $('#tags');
        //
        // elt.tagsinput({
        //     typeaheadjs: {
        //         name: 'tags',
        //         displayKey: 'tagName',
        //         valueKey: 'tagName',
        //         source: tags.ttAdapter()
        //     },
        //     freeInput: true,
        //     trimValue: true,
        //     maxChars: 8,
        // });

    }
};
