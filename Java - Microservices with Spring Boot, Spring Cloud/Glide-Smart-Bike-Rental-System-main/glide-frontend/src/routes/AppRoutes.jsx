import { BrowserRouter, Routes, Route } from "react-router-dom";

import Login from "../pages/auth/Login";
import Register from "../pages/auth/Register";

import RiderDashboard from "../pages/rider/Dashboard";
import Wallet from "../pages/rider/Wallet";
import Stations from "../pages/rider/Stations";
import Reservations from "../pages/rider/Reservations";
import RideHistory from "../pages/rider/RideHistory";
import AvailableBikes from "../pages/rider/AvailableBikes";
import CurrentRide from "../pages/rider/CurrentRide";

import OperatorDashboard from "../pages/operator/Dashboard";
import OperatorStations from "../pages/operator/Stations";
import OperatorBikes from "../pages/operator/Bikes";
import Reports from "../pages/operator/Reports";

function AppRoutes() {
    return (
        <BrowserRouter>
            <Routes>

                {/* Authentication */}
                <Route path="/" element={<Login />} />
                <Route path="/login" element={<Login />} />
                <Route path="/register" element={<Register />} />

                {/* Rider */}
                <Route
                    path="/rider/dashboard"
                    element={<RiderDashboard />}
                />

                <Route
                    path="/rider/wallet"
                    element={<Wallet />}
                />

                <Route
                    path="/rider/stations"
                    element={<Stations />}
                />

                <Route
                    path="/rider/bikes"
                    element={<AvailableBikes />}
                />

                <Route
                    path="/rider/stations/:stationId/bikes"
                    element={<AvailableBikes />}
                />

                <Route
                    path="/rider/reservations"
                    element={<Reservations />}
                />`x    `

                <Route
                    path="/rider/current-ride"
                    element={<CurrentRide />}
                />

                <Route
                    path="/rider/history"
                    element={<RideHistory />}
                />

                {/* Operator */}
                <Route
                    path="/operator/dashboard"
                    element={<OperatorDashboard />}
                />

                <Route
                    path="/operator/stations"
                    element={<OperatorStations />}
                />

                <Route
                    path="/operator/bikes"
                    element={<OperatorBikes />}
                />

                <Route
                    path="/operator/reports"
                    element={<Reports />}
                />

            </Routes>
        </BrowserRouter>
    );
}

export default AppRoutes;