/**
 * Created by fengyuwusong on 2017/10/9.
 */

var edit = {
    //编辑器实例
    editor: null,
    data: null,
    //文章id
    id: null,
    tagElt: $('#tags'),
    URL: {
        getArticle: function () {
            return "/admin/article/read/" + edit.id;
        },
        getTags: function () {
            return "/tag";
        },
        addOrUpdateArticle: function () {
            return "/admin/article";
        },
        getCategory: function () {
            return "/category";
        },
    },


//    获取id参数
    GetQueryString: function (name) {
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
        var r = window.location.search.substr(1).match(reg);
        if (r != null)return unescape(r[2]);
        return null;
    },


//    获取文章
    getArticle: function () {
        $.get(edit.URL.getArticle(), {}, function (res) {
            edit.data = res.data;
            //内容赋值
            edit.edit(res.data.content);
            //标题赋值
            $("#title").val(res.data.title);
            //作者赋值
            $("#author").val(res.data.author);
            //基本描述赋值
            $("#description").val(res.data.description);
            //分类赋值
            $("#category").val(res.data.category.categoryName);
            //标签赋值
            $.map(res.data.articleTags, function (tag) {
                edit.tagElt.tagsinput("add", tag.tagName);
            });
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


        const category = new Bloodhound({
            datumTokenizer: Bloodhound.tokenizers.obj.whitespace('categoryName'), //服务端返回的json中， categoryName对应的字段叫categoryName
            queryTokenizer: Bloodhound.tokenizers.whitespace,
            prefetch: {
                url: edit.URL.getCategory(),  //'%QUERY' 将被用户输入的值代替
                filter: function (resp) { //服务端未必直接以json array方式返回搜索结果。如果不是的话，指定一下搜索结果在json中的路径。
                    return $.map(resp.data, function (c) {
                        return {categoryName: c.categoryName, cid: c.id};
                    });
                }
            }
        });
        category.initialize();


        $('#category').typeahead({
                hint: true,
                highlight: true,
                minLength: 1
            },
            {
                name: 'category',
                displayKey: 'categoryName',
                source: category.ttAdapter(),
            })

            .on('typeahead:selected', function (evt, datum) {
                $('#cid').val(datum.cid);
            });

        // const c=category.index.datums;
        // let flag=false;
        // if(edit.id!==null){
        //     flag = true;
        // }

        //todo 不能选选项之外的
        // .blur(function(evt, datum){
        //     $.map(c,function (cc) {
        //         if(cc.categoryName===$("#category").val()){
        //             flag=true;
        //             return;
        //         }
        //     });
        //     if(flag!==true){
        //         alert("不能填写没添加的分类!");
        //         $("#category").val("");
        //         flag=false;
        //     }
        // });
    },


//    tag使用预加载
    tag: function () {

        const tags = new Bloodhound({
            datumTokenizer: Bloodhound.tokenizers.obj.whitespace('tagName'), //服务端返回的json中， categoryName对应的字段叫categoryName
            queryTokenizer: Bloodhound.tokenizers.whitespace,
            prefetch: {
                url: edit.URL.getTags(),  //'%QUERY' 将被用户输入的值代替
                filter: function (resp) { //服务端未必直接以json array方式返回搜索结果。如果不是的话，指定一下搜索结果在json中的路径。
                    return $.map(resp.data, function (tag) {
                        return {tagName: tag.tagName};
                    });
                }
            }
        });


        //todo 可以使用监听事件改进
        edit.tagElt.tagsinput({
            typeaheadjs: {
                name: 'tag',
                displayKey: 'tagName',
                valueKey: 'tagName',
                source: tags.ttAdapter()
            },
            freeInput: true,
            trimValue: true,
            maxChars: 8,
        });

    },


    //获取所有input框集合转为json
    getInputArticle: function () {

        const tags = $.map(edit.tagElt.tagsinput('items'), function (tag) {
            return {tagName: tag};
        });
        // console.log(tags);

        const article = {
            id: edit.id,
            articleTags: tags,
            author: $("#author").val(),
            categoryId: $('#cid').val(),
            content: edit.editor.getMarkdown(),
            description: $("#description").val(),
            title: $("#title").val()
        };
        return JSON.stringify(article);
    },


    //添加文章方法
    addArticle: function () {
        $.ajax({
            type: "POST",
            url: edit.URL.addOrUpdateArticle(),
            contentType: "application/json; charset=utf-8",
            data: edit.getInputArticle(),
            dataType: "json",
            success: function (res) {
                if (res.code === 200) {
                    alert("添加完成");
                    location.href = "./list.html";
                } else {
                    alert(res.message);
                }
            },
            error: function () {

            }
        });
    },
    updateArticle: function () {
        $.ajax({
            type: "PUT",
            url: edit.URL.addOrUpdateArticle(),
            contentType: "application/json; charset=utf-8",
            data: edit.getInputArticle(),
            dataType: "json",
            success: function (res) {
                if (res.code === 200) {
                    alert("编辑完成");
                    location.href = "./list.html";
                } else {
                    alert(res.message);
                }
            },
            error: function () {

            }
        });
    },
//    初始化方法
    init: function () {
        //判断是否含有参数
        edit.id = edit.GetQueryString("id");
        //ajax加载
        if (edit.id !== null) {
            edit.getArticle();
        } else {
            edit.edit("");
        }


        //预加载文章分类
        edit.category();
        //预加载文章tag
        edit.tag();

        //监听提交按钮
        $("#submit").on("click", function (e) {
            if (edit.id === null) {
                //添加文章
                edit.addArticle();
            } else {
                // todo更新文章
                edit.updateArticle();
            }
        });
    }
};
