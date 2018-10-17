
$(function () {
    getData(1);
});

function getData(page_num) {
    var _data = "page_num=" + page_num + "&page_size=5";
    $.ajax({
        url: "/api/category/list",
        type: "get",
        dataType: "json",
        cache: false,
        xhrFields: {
            withCredentials: true
        },
        beforeSend: function () {},
        complete: function () {},
        data: _data,
        success: function (result) {
            if (result.code == 200) {
                initData(result.data);

            }



        }
    });
}

function initData(data) {
    var list = data.list;
    var tr_str = "<tr>" +
        "<th>主键</th>" +
        "<th>分类的名称</th>" +
        "<th >属性管理</th>" +
        "<th >产品管理</th>" +
        "<th >修改</th>" +
        "<th >删除</th>" +
        "</tr>";
    for (var i = 0; i < list.length; i++) {
        tr_str += "<tr data-id='" + list[i].id + "'>" +
            '<td class="cc">' + list[i].id + '</td>' +
            '<td class="cc">' + list[i].name + '</td>' +
            '<td  class="cc">&nbsp&nbsp<a href="/admin/' + list[i].id + '/query">详情</a></td>' +
            '<td  class="cc">&nbsp&nbsp<a href="/admin/' + list[i].id + '/fenleiliebiao">详情</a></td>' +
            "<td  class='cc'>&nbsp&nbsp<a  href='#' onclick='showActivityUpdateBox(this)'>修改</a></td>" +
            "<td  class='cc'>&nbsp&nbsp<a  href='#'  onclick='deleteItem(this)'>删除</a></td>" +
            '</td>'
        "</tr>";
    }


    $("#table").html(tr_str);

    $(".M-box").pagination({
        pageCount: data.pages,
        current: data.page_num,
        callback: 'pageselectCallback',
        prevContent: "上一页",
        nextContent: "下一页",
        jump: true,
        coping: true,
        callback: function (api) {
            getData(api.getCurrent())
        }
    });
}

//修改

var activityXhr;
var updateTr;
function showActivityUpdateBox(item) {
    $(".activity-update-box").show();
    var tr=$(item).closest("tr");
    update_id = tr.data("id");
    if (activityXhr) activityXhr.abort();
    activityXhr = $.ajax({
        url:"/api/category/selectById/" +update_id,
        type: "get",
        dataType: "json",
        cache: false,
        xhrFields: {
            withCredentials: true
        },
        success: function (result) {
            if (result.code == 200) {
                var data = result.data.list;
                for(x in data){
                    // console.log("id: " + data[x].id);
                    console.log("name: " + data[x].name);
                }
                for(x in data){
                    $("#container").append('<p>'+"商品名称："+"<input type='text' name='id' value='"+data[x].id +"'>" + '</p>');
                    $("#container").append('<p>'+"商品名称："+"<input type='text' name='name' value='" + data[x].name +"'>" + '</p>');
                }
                // alert("zhenshuai");
            }
        },
    });
}
function activityUpdate() {
    if (activityXhr) activityXhr.abort();
    activityXhr = $.ajax({
        url: "/api/category/update",
        type: "post",
        data: new FormData($("#activity-update-form")[0]),
        dataType: "json",
        processData: false, // 用于对data参数进行序列化处理 这里必须false
        contentType: false, // 必须
        async : true,
        cache: false,//清除缓存
        xhrFields: {//跨域
            withCredentials: true
        },
        success: function (result) {
            if (result.code == 200) {
                alert(result.msg);
                var dialog = $("#activity-update-dialog");
                updateTr.children("#id").text(dialog.find("input[name=id]").val());
                updateTr.children("#name").text(dialog.find("input[name=name]").val());

                // updateTr.children("#category_id").text(dialog.find("input[name=category_id]").val());
            } else {

            }
        },error: function (result) {

        }
    });
}


// 删除
function deleteItem(item) {
    if (confirm("确定要删除吗？")) {
        var id = $(item).closest("tr").data("id");
        var data = "";
        data += "id=" + id;
        $.ajax({
            url: "/api/category/delete",
            type: "post",
            data: data,
            dataType: "json",
            cache: false,
            xhrFields: {
                withCredentials: true
            },
            beforeSend: function () {},
            complete: function () {},
            success: function (result) {
                if (result.code == 200) {
                    alert(result.msg);
                    var tr = $(item).closest("tr");
                    tr.remove();
                }

            }

        });
    }
}

function showActivityBox(item) {
    window.location.href = "fenleiliebiao.html?id=" + $(item).closest("tr").data("id");
    return;
}
//
// function openPage(item) {
//     openUrl($(item).data("url"));
// }
//
// function openUrl(url) {
//     window.location.href = url ;
// }