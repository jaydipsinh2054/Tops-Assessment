import { NavLink } from "react-router-dom";

function RiderSidebar() {

    return (

        <div className="sidebar d-flex flex-column p-3">

            <div className="text-center mb-5">

                <h2 className="fw-bold text-white">
                    🚲 Glide
                </h2>

                <small className="text-secondary">
                    Rider Panel
                </small>

            </div>

            <ul className="nav flex-column">

                <li className="nav-item mb-2">
                    <NavLink to="/rider/dashboard" className="nav-link">
                        <i className="bi bi-speedometer2 me-2"></i>
                        Dashboard
                    </NavLink>
                </li>

                <li className="nav-item mb-2">
                    <NavLink to="/rider/wallet" className="nav-link">
                        <i className="bi bi-wallet2 me-2"></i>
                        Wallet
                    </NavLink>
                </li>

                <li className="nav-item mb-2">
                    <NavLink to="/rider/stations" className="nav-link">
                        <i className="bi bi-geo-alt me-2"></i>
                        Stations
                    </NavLink>
                </li>

                <li className="nav-item mb-2">
                    <NavLink to="/rider/bikes" className="nav-link">
                        <i className="bi bi-bicycle me-2"></i>
                        Available Bikes
                    </NavLink>
                </li>

                <li className="nav-item mb-2">
                    <NavLink to="/rider/reservations" className="nav-link">
                        <i className="bi bi-calendar-check me-2"></i>
                        Reservations
                    </NavLink>
                </li>

                <li className="nav-item mb-2">
                    <NavLink to="/rider/current-ride" className="nav-link">
                        <i className="bi bi-bicycle me-2"></i>
                        Current Ride
                    </NavLink>
                </li>

                <li className="nav-item mb-2">
                    <NavLink to="/rider/history" className="nav-link">
                        <i className="bi bi-clock-history me-2"></i>
                        Ride History
                    </NavLink>
                </li>

            </ul>

            <div className="mt-auto pt-4">

                <hr className="border-secondary"/>

                <small className="text-secondary">
                    Glide Rider v1.0
                </small>

            </div>

        </div>

    );

}

export default RiderSidebar;