import { NavLink } from "react-router-dom";

function OperatorSidebar() {
    return (
        <div className="sidebar d-flex flex-column p-3">

            {/* Logo */}

            <div className="text-center mb-5">

                <h2 className="fw-bold text-white">
                    🚲 Glide
                </h2>

                <small className="text-secondary">
                    Operator Panel
                </small>

            </div>

            {/* Navigation */}

            <ul className="nav flex-column">

                <li className="nav-item mb-2">
                    <NavLink
                        to="/operator/dashboard"
                        className="nav-link"
                    >
                        <i className="bi bi-speedometer2 me-2"></i>
                        Dashboard
                    </NavLink>
                </li>

                <li className="nav-item mb-2">
                    <NavLink
                        to="/operator/stations"
                        className="nav-link"
                    >
                        <i className="bi bi-geo-alt me-2"></i>
                        Stations
                    </NavLink>
                </li>

                <li className="nav-item mb-2">
                    <NavLink
                        to="/operator/bikes"
                        className="nav-link"
                    >
                        <i className="bi bi-bicycle me-2"></i>
                        Bikes
                    </NavLink>
                </li>

                <li className="nav-item mb-2">
                    <NavLink
                        to="/operator/reports"
                        className="nav-link"
                    >
                        <i className="bi bi-bar-chart me-2"></i>
                        Reports
                    </NavLink>
                </li>

            </ul>

            {/* Footer */}

            <div className="mt-auto pt-4">

                <hr className="border-secondary" />

                <small className="text-secondary">
                    Glide v1.0
                </small>

            </div>

        </div>
    );
}

export default OperatorSidebar;