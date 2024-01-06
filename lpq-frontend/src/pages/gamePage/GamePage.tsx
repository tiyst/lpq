import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import LpqHeader from "../../components/header/LpqHeader";
import "./GamePage.scss";
import "../common/LpqComponent.scss";
import { useStompClient, useSubscription } from "react-stomp-hooks";
import { ConnectionMessage, Player } from "../../types/GameTypes";

const GamePage = () => {
	const [players, setPlayers] = useState<Player[]>([]);
	const [messages, setMessages] = useState<string[]>([]);

	const { gameCode } = useParams();
	const client = useStompClient();

	useEffect(() => {
		client?.publish({
			destination: `/app/lpq/connect/${gameCode}`,
			body: "reactJsUser",
		});
	}, [client?.active]);

	useSubscription(`/lpq/game/${gameCode}`, message => {
		console.log(message.body);
		setMessages(messages.concat(message.body));
		console.log(messages);
	});

	useSubscription(`/lpq/game/${gameCode}/players`, message => {
		const connectedMessage: ConnectionMessage = JSON.parse(message.body);
		console.log(connectedMessage);
		console.log(players);
		if (connectedMessage.type === "CONNECTED") {
			setPlayers(connectedMessage.allPlayers);
		} else {
			console.log(connectedMessage.allPlayers);
			setPlayers(players.filter(player => player.playerSessionId !== connectedMessage.playerSessionId));
		}
		console.log(players);
	});
	// TODO create and manage websocket connection with backend (configurable with yaml file for dev environment mocks)

	return (
		<>
			<LpqHeader />
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
							<span key={player.playerSessionId}> {player.playerName}</span>
						))}
					</ul>
				</div>
			</div>
		</>
	);
};

export default GamePage;
