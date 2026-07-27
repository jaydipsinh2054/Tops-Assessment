import { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import { ToastContainer, toast } from "react-toastify";

import AuthLayout from "../../layouts/AuthLayout";
import { registerUser } from "../../services/authService";

function Register() {

    const navigate = useNavigate();

    const [formData, setFormData] = useState({
        fullName: "",
        email: "",
        phone: "",
        password: "",
        confirmPassword: ""
    });

    const [loading, setLoading] = useState(false);

    const handleChange = (e) => {

        setFormData({
            ...formData,
            [e.target.name]: e.target.value
        });

    };

    const handleSubmit = async (e) => {

        e.preventDefault();

        if (formData.password !== formData.confirmPassword) {

            toast.error("Passwords do not match");

            return;

        }

        setLoading(true);

        try {

            const response = await registerUser({
                fullName: formData.fullName,
                email: formData.email,
                phone: formData.phone,
                password: formData.password
            });

            toast.success(response);

            setTimeout(() => {

                navigate("/login");

            }, 1500);

        } catch (error) {

            console.error(error);

            if (error.response?.data) {
                toast.error(error.response.data);
            } else {
                toast.error("Registration Failed");
            }

        } finally {

            setLoading(false);

        }

    };

    return (

        <AuthLayout title="Create Account">

            <form onSubmit={handleSubmit}>

                <div className="mb-3">

                    <label className="form-label">
                        Full Name
                    </label>

                    <input
                        type="text"
                        name="fullName"
                        className="form-control"
                        placeholder="Enter full name"
                        value={formData.fullName}
                        onChange={handleChange}
                        required
                    />

                </div>

                <div className="mb-3">

                    <label className="form-label">
                        Email
                    </label>

                    <input
                        type="email"
                        name="email"
                        className="form-control"
                        placeholder="Enter email"
                        value={formData.email}
                        onChange={handleChange}
                        required
                    />

                </div>

                <div className="mb-3">

                    <label className="form-label">
                        Phone Number
                    </label>

                    <input
                        type="text"
                        name="phone"
                        className="form-control"
                        placeholder="Enter phone number"
                        value={formData.phone}
                        onChange={handleChange}
                        required
                    />

                </div>

                <div className="mb-3">

                    <label className="form-label">
                        Password
                    </label>

                    <input
                        type="password"
                        name="password"
                        className="form-control"
                        placeholder="Enter password"
                        value={formData.password}
                        onChange={handleChange}
                        required
                    />

                </div>

                <div className="mb-3">

                    <label className="form-label">
                        Confirm Password
                    </label>

                    <input
                        type="password"
                        name="confirmPassword"
                        className="form-control"
                        placeholder="Confirm password"
                        value={formData.confirmPassword}
                        onChange={handleChange}
                        required
                    />

                </div>

                <button
                    type="submit"
                    className="btn btn-primary w-100"
                    disabled={loading}
                >
                    {loading ? "Creating Account..." : "Create Account"}
                </button>

                <p className="text-center mt-3">

                    Already have an account?

                    <Link
                        to="/login"
                        className="ms-2"
                    >
                        Login
                    </Link>

                </p>

            </form>

            <ToastContainer position="top-right" />

        </AuthLayout>

    );

}

export default Register;