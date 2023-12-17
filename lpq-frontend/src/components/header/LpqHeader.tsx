import LpqButton from "../button/LpqButton";
import '../../pages/common/LpqComponent.scss'
import './LpqHeader.scss'

const LpqHeader = () => {
	return (
		<div className="header lpq-component">
			<h1>LPQ</h1>
			<div className="loginWrapper">
				<LpqButton description="Login" />
			</div>
		</div>
	);
};

export default LpqHeader;