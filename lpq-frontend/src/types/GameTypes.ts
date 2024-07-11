export type ConnectionMessage = {
	type: string;
	player: Player;
};

export type Player = {
	answeredCorrectly: boolean;
	name: string;
	score: number;
	sessionId: string;
};
