/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
var websocketSession;

function open() {
    console.log("Socket is about to open");
    if (!websocketSession) {
        websocketSession = new WebSocket('ws://localhost:8080/ssb/job-status');
        websocketSession.onmessage = actOnMessage;
        console.log("Socket is open");        
    }
}

function actOnMessage(event) {
    console.log("Message from the Socket is received");
    var socketMsg = document.getElementById("sockMsg");
    socketMsg.innerHTML = socketMsg.innerHTML + "<br/><label id='data'>" + event.data + "</label>";
}

function close() {
    console.log("Socket is about to close");
    if (websocketSession) {
        websocketSession.close();        
    }
}

window.onload = open;
window.onunload = close;
