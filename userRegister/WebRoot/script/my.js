$(document).ready(function(){
    $("#username").blur(function(){
        if($(this).val() == "")
            $("#chk_username").html("username cannot be empty.");
    }).keyup(function(){
        $("#chk_username").load("register?username="+$(this).val()+"&timestamp="+Math.random());/*解决缓存问题*/
    });
    $("#register").click(function(){
        if($("#username").val()!="" && $("#password").val()!="")
            $("#form").attr("action", "register").submit();
    });
    $("#login").click(function(){
        if($("#username").val()!="" && $("#password").val()!="")
            $("#form").attr("action", "login").submit();
    });
});