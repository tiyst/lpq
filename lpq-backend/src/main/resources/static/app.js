//Very basic tester web app that leaves a lot to be desired

let gameCode = null;
let client;

function setConnected(connected) {
	$("#connect").prop("disabled", connected);
	$("#disconnect").prop("disabled", !connected);
	if (connected) {
		$("#conversation").show();
	} else {
		$("#conversation").hide();
	}
	$("#greetings").html("");
}

function initWebsocketClient() {
	client = new StompJs.Client({
		brokerURL: 'ws://localhost:8080/lpqws'
	});

	let destination = '/lpq/game/' + gameCode;
	client.onConnect = () => {
		setConnected(true);
		client.subscribe(destination, (message) => {
			showGreeting(JSON.stringify(message.body))
			message.ack();
		});

		let connect = "/app/lpq/connect/" + gameCode;
		console.log("Connected to server with game code: " + gameCode)
		client.publish({
			destination: connect,
			body: "devTestPlayer"
		});
	}

	client.onWebSocketError = (error) => {
		console.error('Error with websocket', error);
	};
}

function connect() {
	if (gameCode == null || gameCode === "") {
		return;
	}
	initWebsocketClient();
	client.activate();
}

function disconnect() {
	if (client !== null) {
		client.deactivate();
	}
	setConnected(false);
	console.log("Disconnected");
}

function sendMessage() {
	let destination = "/app/lpq/" + gameCode;
	client.publish({
		destination: destination,
		body: JSON.stringify({
			"playerName": "player",
			"move": "it move it"
		})
	})
}

function showGreeting(message) {
	$("#greetings").append("<tr><td>" + message + "</td></tr>");
}

async function createGame() {
	const response = await fetch('game/create/GUESS_CHAMPION/SPLASH', {
		method: 'POST',
		body: JSON.stringify({"GameType": "GUESS_CHAMPION", "GuessType": "SPLASH"}),
		headers: {
			'Accept': 'application/json',
			'Content-Type': 'application/json'
		}
	});
	const gameData = await response.json();
	console.log(gameData)
}

$(function () {
	$("form").on('submit', function (e) {
		e.preventDefault();
	});
	$("#connect").click(function () {
		connect();
	});
	$("#disconnect").click(function () {
		disconnect();
	});
	$("#create").click(function () {
		createGame().then();
	})
	$("#send").click(function () {
		sendMessage();
	});
	$("#gameCode").on("change", function () {
		gameCode = $("#gameCode").val();
	})
});