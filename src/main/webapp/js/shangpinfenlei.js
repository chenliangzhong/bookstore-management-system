function subjectPersonnalPost() {
    $.ajax({
        url: "/api/product/add",
        type: "post",
        data: $("#subject-personnel-post-form").serialize(),
        dataType: "json",
        cache: false,
        xhrFields: {
            withCredentials: true
        },
        beforeSend: function () {},
        complete: function () {},
        success: function (result) {
            if (result.code == 200) {
                window.location.href = "tianjialiebiao";
            }
            alert(result.msg);
        },
        error: function (result) {
            alert("网络连接出错！");
        }
    });
}