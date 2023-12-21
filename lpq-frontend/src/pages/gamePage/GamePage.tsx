import { useState } from "react";
import LpqHeader from "../../components/header/LpqHeader";
import './GamePage.scss'
import '../common/LpqComponent.scss'

const GamePage = () => {
	const [players, setPlayers] = useState<string[]>();

	// TODO create and manage websocket connection with backend (configurable with yaml file for dev environment mocks)
	return (
		<>
			<LpqHeader/>
			<div className="game-screen-wrapper">
				<div className="left-part lpq-component">
					<h1>Left part</h1>
				</div>
				<div className="right-bar lpq-component">
					<h1>Right part</h1>
				</div>
			</div>
		</>
	);
};

export default GamePage;