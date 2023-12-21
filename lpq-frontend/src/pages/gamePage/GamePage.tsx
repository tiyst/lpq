import {useState} from "react";
import {useParams} from "react-router-dom";
import LpqHeader from "../../components/header/LpqHeader";
import "./GamePage.scss";
import "../common/LpqComponent.scss";
import {useStompClient, useSubscription} from "react-stomp-hooks";

const GamePage = (props: {gameCode: string | undefined}) => {
	const [players, setPlayers] = useState<string[]>([]);

	const {gameCode} = useParams();

	const [messages, setMessages] = useState<string[]>([]);
	const [lastMessage, setLastMessage] = useState<string>("No message received yet");
	const client = useStompClient()

	useSubscription(`/lpq/game/${gameCode}`, message => {
		setLastMessage(message.body);
		console.log(message.body);
		const strings = messages;
		strings.push(message.body);
		client?.publish({
			destination: "/app/lpq/connect",
			body: JSON.stringify({
				"gameCode": gameCode,
				"userName": "reactJsUser"
			})
		});
		setMessages(strings);
		console.log(messages);
	});

	// TODO create and manage websocket connection with backend (configurable with yaml file for dev environment mocks)

	return (
		<>
			<LpqHeader/>
			<div className="game-screen-wrapper">
				<div className="left-part lpq-component">
					<h1>{gameCode}</h1>
					<ul>
						{messages.map((i, message) => (
							<li key={i}>{message}</li>
						))}
					</ul>
				</div>
				<div className="right-bar lpq-component">
					<h1>Right part</h1>
				</div>
			</div>
		</>
	);
};

export default GamePage;
