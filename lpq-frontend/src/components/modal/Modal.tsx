import "./Modal.scss";
import LpqButton from "../button/LpqButton";

export type ModalProps = {
	title: string;
	subtitle?: string;
	handleClose: () => void;
};
const Modal = (props: ModalProps) => {
	const { title, subtitle, handleClose } = { ...props };

	return (
		<div className="modal-backdrop">
			<div className="modal">
				<div className="modal-body">
					<div className="modal-title">{title}</div>
					<div className="modal-subtitle">{subtitle}</div>
					<input type="text" />
					<LpqButton description="Close" callback={() => handleClose()} />
					<LpqButton description="Join" />
				</div>
			</div>
		</div>
	);
};

export default Modal;
