$(function(){
    getDat(1);
});
function getDat(page_num){
    var _data = "page_num=" + page_num + "&pagesize=10";
    $.ajax({
        url:"/api/product/list",
        type:"get",
        dataType:"json",
        cache:false,
        timeout: 1000,
        xhrFields:{
            withCredentials:true
        },
        data:_data,
        success:function(result){
            if (result.code == 200) {
                initDat(result.data);
            }
        }
    });
}
function initDat(data){
    var list = data.list;
    var tr_str = "";
    // for (var i = 0; i < list.length; i++) {
    // 	liStr += '<li class="lef1"  ><a href="#" onclick="showActivityUpdateBox(this)">' + list[i].name + '</a><ul>';
    // 	liStr += '</ul></li>';
    // }
    for (var i = 0; i < 8; i++) {
        tr_str += "<div id='container1'  data-category_id='" + list[i].id + "'>" +
            "<ul>"+
            "<li class='ming'><a href='#' onclick='showActivityBo(this)'>"+ "书名：" + list[i].name + "</a></li>"+
            // "<li ><a href='#' onclick='showActivityBo(this)'>" + list[i].subtitle + "</a></li>" +
            "<li class='yuan'><a href='#' onclick='showActivityBo(this)'>" + "原价格：" + list[i].original_price + "</a></li>" +
            "<li class='jia'><a href='#' onclick='showActivityBo(this)' >"+ "优惠价：" +"￥"+" " + list[i].promote_price + "</a></li>" +
            // "<li ><a href='#' onclick='showActivityBo(this)'>" + list[i].stock + "</a></li>" +
            // "<li ><a href='#' onclick='showActivityBo(this)'>" + list[i].create_date + "</a></li>" +
            "</ul>"+
            "</div>";
        var product_image = list[i].product_image_list;
        for (var x = 0; x <1; x++) {
            tr_str +="<div data-id='" + list[i].id + "' id='container2'>" +
                // "<li >" + product_image_list[y].id + "</li>" +
                // "<li ><a href='#' onclick='showActivityBox(this)'>" + product_image[x].picture + "</a></li>" +
                "<li ><img src='"+ product_image[x].picture + "' class='im' onclick='showActivityBo(this)'> </li>" +
                "</div>";


        }
    }
    $("#fenlie1").html(tr_str);
}
function showActivityBo(item) {
    window.location.href = "/bookstoreHome/" + $(item).closest("div").data("id") + "/Look_at";
    return;
}