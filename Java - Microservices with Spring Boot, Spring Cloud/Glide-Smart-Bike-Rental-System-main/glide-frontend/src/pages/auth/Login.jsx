import { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import { ToastContainer, toast } from "react-toastify";

import AuthLayout from "../../layouts/AuthLayout";
import { loginUser } from "../../services/authService";
import { STORAGE_KEYS, USER_ROLES } from "../../constants/AppConstants";

function Login() {

    const navigate = useNavigate();

    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [loading, setLoading] = useState(false);

    const handleSubmit = async (e) => {

        e.preventDefault();

        setLoading(true);

        try {

            const response = await loginUser({
                email,
                password,
            });

            localStorage.setItem(STORAGE_KEYS.TOKEN, response.token);
            localStorage.setItem(STORAGE_KEYS.FULL_NAME, response.fullName);
            localStorage.setItem(STORAGE_KEYS.EMAIL, response.email);
            localStorage.setItem(STORAGE_KEYS.ROLE, response.role);

            toast.success("Login Successful!");

            setTimeout(() => {

                if (response.role === USER_ROLES.OPERATOR) {
                    navigate("/operator/dashboard");
                } else {
                    navigate("/rider/dashboard");
                }

            }, 1000);

        } catch (error) {

            console.error(error);

            toast.error("Invalid Email or Password");

        } finally {

            setLoading(false);

        }

    };

    return (

        <AuthLayout title="Login">

            <form onSubmit={handleSubmit}>

                <div className="mb-3">

                    <label className="form-label">
                        Email
                    </label>

                    <input
                        type="email"
                        className="form-control"
                        placeholder="Enter email"
                        value={email}
                        onChange={(e) => setEmail(e.target.value)}
                        required
                    />

                </div>

                <div className="mb-3">

                    <label className="form-label">
                        Password
                    </label>

                    <input
                        type="password"
                        className="form-control"
                        placeholder="Enter password"
                        value={password}
                        onChange={(e) => setPassword(e.target.value)}
                        required
                    />

                </div>

                <button
                    type="submit"
                    className="btn btn-primary w-100"
                    disabled={loading}
                >
                    {loading ? "Logging in..." : "Login"}
                </button>

                <p className="text-center mt-3">

                    Don't have an account?

                    <Link
                        to="/register"
                        className="ms-2"
                    >
                        Register
                    </Link>

                </p>

            </form>

            <ToastContainer position="top-right" />

        </AuthLayout>

    );

}

export default Login;