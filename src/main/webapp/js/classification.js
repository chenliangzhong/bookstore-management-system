$(function(){
	getData(1);
});
function getData(page_num){
	var _data = "page_num=" + page_num + "&page_size=10";
	$.ajax({
		url:"/api/category/list",
		type:"get",
		dataType:"json",
		cache:false,
		xhrFields:{
			withCredentials:true
		},
		data:_data,
		success:function(result){
			if (result.code == 200) {
				initData(result.data);
			}
		}
	});
}
function initData(data){
	var list = data.list;
	 var tr_str = "";
	// for (var i = 0; i < list.length; i++) {
	// 	liStr += '<li class="lef1"  ><a href="#" onclick="showActivityUpdateBox(this)">' + list[i].name + '</a><ul>';
	// 	liStr += '</ul></li>';
	// }
	 for (var i = 0; i < list.length; i++) {
        tr_str += "<div id='le' data-category_id='" + list[i].id + "'>" +
           "<ul>"+
            '<li ><a href="/bookstoreHome/' + list[i].id + '/categoryById">' + list[i].name + '</a></li>' +
			"</ul>"+
        "</div>";
    }
	$("#left1").html(tr_str);
}

// function showActivityBox(item) {
//     window.location.href = "categoryById.html?category_id=" + $(item).closest("div").data("category_id");
//     return;
// }

// 退出系统
function logout(){
	        
	        $.ajax({
	            url : "/api/user/logout",
	            dataType : "json",
	            cache: false,
                xhrFields: {
                    withCredentials: true
                },
                beforeSend: function () {},
                complete: function () {},
	           success : function(result) {
	               if(result.code == 200){
	               	// if (result.code == 200) {
	               		  alert(result.msg);
	                   window.location.href ="login.html";
	               	// }
	                 

	               }else{
	                   alert(result.msg);
	               }
	               // if (result.code == 200) window.location.href = "Login.html";
	           },
	             error: function (result) {
            		alert(result.msg);
                }
	        });
	 
	    }
