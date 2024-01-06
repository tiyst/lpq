export type ConnectionMessage = {
	type: string;
	playerSessionId: string;
	playerName: string;
	allPlayers: Player[];
};

export type Player = {
	playerName: string;
	playerSessionId: string;
};
