var stompClient = null;
function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#conversation").show();
    } else {
        $("#conversation").hide();
    }
    $("#notice").html("");
}

function connect() {
    //连接上端点(基站)
    //用stom进行包装，规范协议
    stompClient = Stomp.over(new SockJS('/endpoint-websocket'));
    stompClient.connect({}, function (frame) {	
        setConnected(true);
        stompClient.subscribe('/chat/demo_chat', function (result) {
        	showContent(JSON.parse(result.body));
        });
    });
}

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    setConnected(false);
}

function sendName() {
    stompClient.send("/app/sky/chat", {}, JSON.stringify({'content': $("#content").val()}));
}

function showContent(body) {
    $("#notice").append("<tr><td>" + body.content + "</td> <td>"+new Date(body.time).toLocaleString()+"</td></tr>");
}

$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $( "#connect" ).click(function() { connect(); });
    $( "#disconnect" ).click(function() { disconnect(); });
    $( "#send" ).click(function() { sendName(); });
});

