function zswbjy() {
    var zswb = $("#f_zswb").val();
    if (zswb.length > 1 && zswb != '') {
        var lodt = zswb.lastIndexOf(".");
        var type = zswb.substring(lodt + 1);
        if (type != "apk") {
            $("#zswbdiv").empty();
            $("#zswbdiv").append("<font color='red'>上传的文件必须为apk！</font>");
            return false;
        } else {
            $("#zswbdiv").empty();
            return true;
        }
    } else {
        $("#zswbdiv").empty();
        $("#zswbdiv").append("<font color='red'>文件不能为空且文件必须为apk</font>");
        return false;
    }
}





function checktxt() {
    var a = document.getElementById("name").value;
    if (a == null || a == "") {
        document.getElementById("nameDiv").style.display = "block";
    } else {
        document.getElementById("nameDiv").style.display = "none";
    }
}

function checktxtc() {
    var a = document.getElementById("namev").value;
    if (a == null || a == "") {
        document.getElementById("nameDivv").style.display = "block";
    } else {
        document.getElementById("nameDivv").style.display = "none";
    }
}



function subjectPersonnalPost() {
    var formData = new FormData($("#subject-personnel-post-form")[0]);
    $.ajax({
        url: "/app/upload",
        type: "post",

        data: formData,
        async: false,

        contentType: false,
        processData: false,
        data: formData,
        cache: false,
        xhrFields: {
            withCredentials: true
        },
        beforeSend: function () {},
        complete: function () {},
        success: function (result) {
            alert(result.msg);
            if (result.code == 200) {
                window.location.href = "App.html";
            }
        },
        error: function (result) {
            alert("网络连接出错！");
        }
    });
}