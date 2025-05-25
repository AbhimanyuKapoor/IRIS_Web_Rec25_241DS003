let stompClient = null;

function connect() {
  const socket = new SockJS("http://localhost:8080/ws?token=" + jwtToken);
  stompClient = Stomp.over(socket);

  stompClient.connect({}, function (frame) {
    // console.log("Connected: " + frame);

    stompClient.subscribe("/user/queue/notifications", function (notification) {
      alert("New Notification:\n\n" + notification.body);
    });
  });
}

// function sendMessage() {
//   const message = document.getElementById("message").value;
//   if (stompClient && stompClient.connected) {
//     stompClient.send("/app/sendMessage", {}, message);
//     console.log("Message sent:", message);
//   } else {
//     console.error("WebSocket connection is not established.");
//   }
// }

if (userRole != "ADMIN") connect();
