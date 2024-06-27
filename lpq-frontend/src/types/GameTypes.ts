export type ConnectionMessage = {
	type: string;
	playerSessionId: string;
	playerName: string;
	allPlayers: Player[];
};

export type Player = {
	answeredCorrectly: boolean;
	name: string;
	score: number;
	sessionId: string;
};
