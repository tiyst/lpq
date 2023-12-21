import React from "react";
import { BrowserRouter, Route, Routes } from "react-router-dom";
import MainPage from "./pages/mainPage/MainPage";
import Layout from "./Outlet";
import GamePageWrapper from "./pages/gamePage/GamePageWrapper";

export default function App() {
	return (
		// <StompProvider config={{ brokerURL: "ws://localhost:8080/lpqws" }}>
		// <StompSessionProvider url={"ws://localhost:8080/lpqws"}>
		<BrowserRouter>
			<Routes>
				<Route path="/" element={<Layout />}>
					<Route index element={<MainPage />} />
					<Route path="game">
						<Route path=":gameCode" element={<GamePageWrapper />} />
					</Route>
				</Route>
			</Routes>
		</BrowserRouter>
		// </StompSessionProvider>
		// </StompProvider>
	);
}
