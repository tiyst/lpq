import React from "react";
import { BrowserRouter, Route, Routes } from "react-router-dom";
import MainPage from "./pages/mainPage/MainPage";
import Layout from "./Outlet";
import GamePage from "./pages/gamePage/GamePage";

export default function App() {
	return (
		<BrowserRouter>
			<Routes>
				<Route path="/" element={<Layout />}>
					<Route index element={<MainPage />} />
					<Route path="game" element={<GamePage />} />
				</Route>
			</Routes>
		</BrowserRouter>
	);
}
