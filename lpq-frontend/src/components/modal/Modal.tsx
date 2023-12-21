import "./Modal.scss";
import LpqButton from "../button/LpqButton";
import { useNavigate } from "react-router-dom";
import { useState } from "react";

export type ModalProps = {
	title: string;
	subtitle?: string;
	handleClose: () => void;
};
const Modal = (props: ModalProps) => {
	const { title, subtitle, handleClose } = { ...props };
	const navigate = useNavigate();

	const [gameCode, setGameCode] = useState("game-code");
	const [gameName, setGameName] = useState("game-name");

	const joinGame = (gameCode: string) => {
		navigate(`/game/${gameCode}`);
	};

	return (
		<div className="modal-backdrop">
			<div className="modal">
				<div className="modal-body">
					<div className="modal-title">{title}</div>
					<div className="modal-subtitle">{subtitle}</div>
					<input type="text" value={gameCode} onChange={e => setGameCode(e.target.value)} />
					<input type="text" value={gameName} onChange={e => setGameName(e.target.value)} />
					<LpqButton description="Close" callback={() => handleClose()} />
					<LpqButton description="Join" callback={() => joinGame(gameCode)} />
				</div>
			</div>
		</div>
	);
};

export default Modal;
