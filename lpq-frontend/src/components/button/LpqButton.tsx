import "./lpq-button.scss";

export type LpqButtonProps = {
	description: string;
	callback?: () => void;
};
const LpqButton = (props: LpqButtonProps) => {
	const description = props.description;
	const onClick = props?.callback;

	return (
		<button className="lpq-button" onClick={onClick}>
			{description}
		</button>
	);
};

export default LpqButton;
