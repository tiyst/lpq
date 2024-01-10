import "./MainPage.scss";
import LpqButton from "../../components/button/LpqButton";
import { useNavigate } from "react-router-dom";
import LpqHeader from "../../components/header/LpqHeader";
import Modal from "../../components/modal/Modal";
import { useState } from "react";

const MainPage = () => {
	const [showJoinGamePrompt, setShowJoinGamePrompt] = useState<boolean>(false);

	const navigate = useNavigate();

	return (
		<div className="App">
			<LpqHeader leftText="LPQ" />
			<div className="button-group">
				<LpqButton description="Create a game" />
				<LpqButton description="Join a game" callback={() => setShowJoinGamePrompt(true)} />
				<LpqButton description="Find a game" />
				<LpqButton description="Play alone" callback={() => navigate("/game")} />
			</div>
			{showJoinGamePrompt && (
				<Modal
					title="Join a game"
					subtitle="Insert game code"
					handleClose={() => setShowJoinGamePrompt(false)}
				/>
			)}
		</div>
	);
};

export default MainPage;
