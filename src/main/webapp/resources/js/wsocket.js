/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
var websocketSession = sessionStorage.cachedSession;

function open() {
    console.log("Socket is about to open");
    if (!websocketSession) {
        websocketSession = new WebSocket('ws://172.20.52.105:8080/ssb/job-status');
        websocketSession.onmessage = actOnMessage;
        console.log("Socket is open");
        sessionStorage.cachedSession = websocketSession;
    }
}

function actOnMessage(event) {
    console.log("Message from the Socket is received");
    var socketMsg = document.getElementById("sockMsg");
    socketMsg.innerHTML = socketMsg.innerHTML + "<br/><label id='data'>" + event.data + "</label>";
}

function close() { // This method isn't invoked, for the sake of long-polling
    console.log("Socket is about to close");
    if (websocketSession) {
        websocketSession.close();
        sessionStorage.cachedSession = null;
    }
}

window.onload = open;
window.onunload = close;

