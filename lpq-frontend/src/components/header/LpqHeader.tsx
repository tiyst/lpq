import LpqButton from "../button/LpqButton";
import '../../pages/common/LpqComponent.scss'
import './LpqHeader.scss'

export type HeaderProps = {
	leftText?: string;
}

const LpqHeader = (props: HeaderProps) => {
	return (
		<div className="header lpq-component">
			<h1>{props.leftText}</h1>
			<div className="loginWrapper">
				<LpqButton description="Login" />
			</div>
		</div>
	);
};

export default LpqHeader;