import GamePage from "./GamePage";
import { StompSessionProvider } from "react-stomp-hooks";
import { useParams } from "react-router-dom";


const GamePageWrapper = () => {
	const { gameCode } = useParams();

	return (
		<StompSessionProvider url={"ws://localhost:8080/lpqws"}>
			<GamePage />
		</StompSessionProvider>
	);
};

export default GamePageWrapper;
