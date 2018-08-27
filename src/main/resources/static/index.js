$(document).ready(function(){

    function request(target, hash, key){
        console.log(target, hash, key);
        var url = "/rcode";
        if (target=="sql"){
            url = "/scode";
        }
        $.ajax({
            url : url,
            type : "POST",
            data :{
                "type" : target,
                "hash" : hash,
                "key"  : key
            },
            success : function(data){
                console.log(data);
                if(data.status){
                    if(data.code==""){
                        $("#res").text(data.msg);
                    }else {
                        $("#res").text(data.code);
                    }

                    $("#res_div").show();
                }else {
                    $("#res").text(data.msg);
                    $("#res_div").show();
                }
            },
            error: function(data) {
                $("#res").text("查询验证码异常");
                $("#res_div").show();
            }

        });
    }

    $("#search_type").change(function(){
        var target = $("#search_type").find("option:selected").val();
        if ( target == "hash"){
            $("#s").hide();
            $("#q").hide();
            $("#h").show();
        }else if (target == "string") {
            $("#h").hide();
            $("#q").hide();
            $("#s").show();
        }else if (target == "sql"){
            $("#h").hide();
            $("#s").hide();
            $("#q").show();
        }else {
            $("#res").text("请选择正确的查询方式");
            $("#res_div").show();
        }
    });

    $("#search").click(function () {
        var target = $("#search_type").find("option:selected").val();
        if (target == "hash"){
            var hash = $("#hash").val().trim();
            var key = $("#key").val().trim();
            if(hash != "" && key != ""){
                request(target, hash, key);
            }else {
                $("#res").text("请输入正确的哈希查询参数");
                $("#res_div").show();
            }
        }else if (target == "string"){
            var string = $("#string").val().trim();
            if(string != ""){
                request(target, "", string);
            }else {
                $("#res").text("请输入正确的字符串查询参数");
                $("#res_div").show();
            }
        }else if (target == "sql"){
            var telno = $("#telno").val().trim();
            if(telno != ""){
                request(target, "", telno);
            }else {
                $("#res").text("请输入正确的数据库查询参数");
                $("#res_div").show();
            }
        }else {
            $("#res").text("请输入正确的查询参数");
            $("#res_div").show();
        }
    });

    $(document).keyup(function(event){
        if(event.keyCode ==13){
            $("#search").trigger("click");
        }
    });

});