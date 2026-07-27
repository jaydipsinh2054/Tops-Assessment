import { useNavigate } from "react-router-dom";
import { STORAGE_KEYS } from "../../constants/AppConstants";

function Navbar() {

    const navigate = useNavigate();

    const fullName = localStorage.getItem(STORAGE_KEYS.FULL_NAME) || "User";
    const role = localStorage.getItem(STORAGE_KEYS.ROLE) || "";

    const initials = fullName
        .split(" ")
        .map(name => name.charAt(0))
        .join("")
        .toUpperCase();

    function handleLogout() {

        localStorage.clear();

        navigate("/login");

    }

    return (

        <nav className="navbar px-4">

            <div className="container-fluid">

                {/* Left */}

                <div>

                    <h3 className="fw-bold mb-0">
                        Dashboard
                    </h3>

                    <small className="text-secondary">
                        Welcome back 👋
                    </small>

                </div>

                {/* Right */}

                <div className="d-flex align-items-center">

                    {/* Notification */}

                    <button
                        className="btn btn-light rounded-circle me-3"
                        style={{
                            width: "45px",
                            height: "45px"
                        }}
                    >
                        <i className="bi bi-bell fs-5"></i>
                    </button>

                    {/* Avatar */}

                    <div
                        className="d-flex justify-content-center align-items-center text-white fw-bold me-3"
                        style={{
                            width: "45px",
                            height: "45px",
                            borderRadius: "50%",
                            background: "#2563eb"
                        }}
                    >
                        {initials}
                    </div>

                    {/* User */}

                    <div className="me-4">

                        <div className="fw-semibold">

                            {fullName}

                        </div>

                        <small className="text-secondary">

                            {role}

                        </small>

                    </div>

                    {/* Logout */}

                    <button
                        className="btn btn-outline-danger"
                        onClick={handleLogout}
                    >

                        <i className="bi bi-box-arrow-right me-2"></i>

                        Logout

                    </button>

                </div>

            </div>

        </nav>

    );

}

export default Navbar;