function getQueryVariable(variable) {
    var query = window.location.search.substring(1);
    var vars = query.split("&");
    for (var i = 0; i < vars.length; i++) {
        var pair = vars[i].split("=");
        if (pair[0] == variable) return pair[1];
    }
}
$(function () {
    showActivityBox(getQueryVariable("id"));
});

function showActivityBox(id) {

    $.ajax({
        url: "http://192.168.1.138:8080/api/product/show/" + id,
        type: "get",
        dataType: "JSON",
        cache: false,
        xhrFields: {
            withCredentials: true
        },
        beforeSend: function () {},
        complete: function () {},
        success: function (result) {
            if (result.code == 200) {
                var data = result.data;
                var div = $("#subject-update");
                div.find("input[name=id]").val(result.data.id);
                div.find("input[name=name]").val(data.name);
                div.find("input[name=subitle]").val(result.data.subitle);
                div.find("input[name=original_price]").val(data.original_price);
                div.find("input[name=promote_price]").val(result.data.promote_price);
                div.find("input[name=stock]").val(result.data.stock);
                div.find("input[name=create_date]").val(result.data.create_date);
                div.find("input[name=category_id]").val(result.data.category_id);
            }
        },
        error: function (result) {
            alert("网络连接错误");
        }




    });

}


function subjectUpdate() {
    $.ajax({
        url: "http://192.168.1.138:8080/api/product/update",
        type: "post",
        data: $("#subject-update-form").serialize(),
        dataType: "json",
        cache: false,
        xhrFields: {
            withCredentials: true
        },
        beforeSend: function () {},
        complete: function () {},
        success: function (result) {
            alert(result.msg);
            if (result.code == 200) {} else {}
        },
        error: function (result) {
            alert("网络连接出错！");
        }
    });
}