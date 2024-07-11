import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import LpqHeader from "../../components/header/LpqHeader";
import "./GamePage.scss";
import "../common/LpqComponent.scss";
import { useStompClient, useSubscription } from "react-stomp-hooks";
import { ConnectionMessage, Player } from "../../types/GameTypes";
import {Simulate} from "react-dom/test-utils";
import play = Simulate.play;

const GamePage = () => {
	const GAME_GET_URL = (gameCode: string | undefined) => `http://localhost:8080/game/${gameCode}/players`;

	const [players, setPlayers] = useState<Player[]>([]);
	const [messages, setMessages] = useState<string[]>([]);
	const [joined, setJoined] = useState<boolean>(false);

	const { gameCode } = useParams();
	const client = useStompClient();

	useEffect(() => {
		client?.publish({
			destination: `/app/lpq/connect/${gameCode}`,
			body: "reactJsUser",
		});

		setJoined(true);
	}, [client?.active]);

	useEffect(() => {
		const endpoint = GAME_GET_URL(gameCode);

		console.log(endpoint)
		fetch(endpoint)
			.then(response => console.log(response.json()))

		fetch(endpoint)
			.then(response => response.json())
			.then(players => setPlayers(players));
	}, [joined]);


	useSubscription(`/lpq/game/${gameCode}`, message => {
		console.log(message.body);
		setMessages(messages.concat(message.body));
	});

	useSubscription(`/lpq/game/${gameCode}/players`, message => {
		const connectedMessage: ConnectionMessage = JSON.parse(message.body);
		console.log(connectedMessage);

		if (connectedMessage.type == "DISCONNECTED") {
			setPlayers(players.filter(p => p.sessionId !== connectedMessage.player.sessionId));
		} else if (connectedMessage.type == "CONNECTED") {
			setPlayers(players.concat(connectedMessage.player))
		}
		setMessages(messages.concat(`${connectedMessage.player.name} has ${connectedMessage.type}`));
	});

	// TODO create and manage websocket connection with backend
	// (configurable with yaml file for dev environment mocks)

	return (
		<>
			<LpqHeader leftText={gameCode}/>
			<div className="game-screen-wrapper">
				<div className="left-part lpq-component">
					<h1>{gameCode}</h1>
					<ul>
						{messages.map(message => (
							<li key={message}>{message}</li>
						))}
					</ul>
				</div>
				<div className="right-bar lpq-component">
					<h1>Right part</h1>
					<ul>
						{players.map(player => (
							<li key={player.sessionId}>{player.name}</li>
						))}
					</ul>
				</div>
			</div>
		</>
	);
};

export default GamePage;
