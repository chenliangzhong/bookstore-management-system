// function getQueryVariable(variable) {
//     var query = window.location.search.substring(1);
//     var vars = query.split("&");
//     for (var i = 0; i < vars.length; i++) {
//         var pair = vars[i].split("=");
//         if (pair[0] == variable) return pair[1];
//     }
// }
// $(function () {
//     showActivityBox(1);
// });
//
// var productXhr;
// function showActivityBox(page_num) {
//     if (productXhr) productXhr.abort();
//     var _data = "page_num=" + page_num + "&page_size=10";
//     productXhr=$.ajax({
//         url: "/api/product/selectByCategoryId/$!category_id",
//         type: "get",
//         dataType: "json",
//         cache: false,
//         xhrFields: {
//             withCredentials: true
//         },
//         beforeSend: function () {},
//         complete: function () {},
//         data: _data,
//         success: function (result) {
//
//             initData(result.data);
//
//
//         }
//     });
// }
//
// function initData(data) {
//     var list = data.list;
//     var tr_str = "<tr>" +
//         "<th >名字</th>" +
//         "<th   >小标题</th>" +
//         "<th    >原价格</th>" +
//         "<th   >优惠价格</th>" +
//         "<th   >库存</th>" +
//         "<th   >时间</th>" +
//         "<th   >外键，指向用户表</th>" +
//         "<th  >操作</th>" +
//         "</tr>";
//     for (var i = 0; i < list.length; i++) {
//         tr_str += "<tr data-id='" + list[i].id + "'>" +
//             '<td class="cc">' + list[i].name + '</td>' +
//             '<td class="cc">' + list[i].subtitle + '</td>' +
//             '<td class="cc">' + list[i].original_price + '</td>' +
//             '<td class="cc">' + list[i].promote_price + '</td>' +
//             '<td class="cc">' + list[i].stock + '</td>' +
//             '<td class="cc">' + list[i].create_date + '</td>' +
//             '<td class="cc">' + list[i].category_id + '</td>' +
//             // "<td  class='cc'>&nbsp&nbsp<a  href='#' onclick='showActivityBoxx(this)'>修改</a>&nbsp&nbsp<a  href='#'  onclick='deleteItem(this)'>删除</a></td>" +
//             '</td>'
//         "</tr>";
//     }
//
//
//     $("#table").html(tr_str);

//     $(".M-box").pagination({
//         pageCount: data.pages,
//         current: data.page_num,
//         callback: 'pageselectCallback',
//         prevContent: "上一页",
//         nextContent: "下一页",
//         jump: true,
//         coping: true,
//         callback: function (api) {
//             getData(api.getCurrent())
//         }
//     });
// }

function deleteItem(item) {
    if (confirm("确定要删除吗？")) {
        var id = $(item).closest("tr").data("id");
        var data = "";
        data += "id=" + id;
        $.ajax({
            url: "/api/product/delete",
            type: "post",
            dataType: "json",
            cache: false,
            xhrFields: {
                withCredentials: true
            },
            beforeSend: function () {},
            complete: function () {},
            data: data,
            success: function (result) {
                if (result.code == 200) {
                    alert(result.data);
                    var tr = $(item).closest("tr");
                    tr.remove();
                }

            }

        });
    }
}

function openPage(item) {
    window.location.href = $(item).data("url");
}

function showActivityBoxx(item) {
    window.location.href = "tianjiaRevise.html?id=" + $(item).closest("tr").data("id");
    return;
}