import { useEffect, useState } from "react";
import { toast, ToastContainer } from "react-toastify";

import DashboardLayout from "../../layouts/DashboardLayout";
import StatCard from "../../components/dashboard/StatCard";

import { getDashboard } from "../../services/riderDashboardService";

function Dashboard() {

    const [dashboard, setDashboard] = useState(null);

    const [loading, setLoading] = useState(true);

    const loadDashboard = async () => {

        try {

            setLoading(true);

            const data = await getDashboard();

            setDashboard(data);

        }

        catch (error) {

            console.error(error);

            toast.error("Failed to load dashboard.");

        }

        finally {

            setLoading(false);

        }

    };

    useEffect(() => {

        loadDashboard();

    }, []);

    if (loading) {

        return (

            <DashboardLayout role="rider">

                <div className="text-center mt-5">

                    <div
                        className="spinner-border text-primary"
                        role="status"
                    ></div>

                </div>

            </DashboardLayout>

        );

    }

    return (

        <DashboardLayout role="rider">

            <h2 className="dashboard-title">

                Welcome Back 👋

            </h2>

            <p className="text-muted mb-4">

                Here's a quick overview of your account.

            </p>

            <div className="row g-4">

                <div className="col-lg-4 col-md-6">

                    <StatCard
                        title="Wallet Balance"
                        value={`₹${dashboard.walletBalance}`}
                        icon="bi-wallet2"
                        color="#10b981"
                    />

                </div>

                <div className="col-lg-4 col-md-6">

                    <StatCard
                        title="Active Ride"
                        value={dashboard.activeRide ? "Yes" : "No"}
                        icon="bi-bicycle"
                        color="#2563eb"
                    />

                </div>

                <div className="col-lg-4 col-md-6">

                    <StatCard
                        title="Reservation"
                        value={
                            dashboard.activeReservation
                                ? "Active"
                                : "None"
                        }
                        icon="bi-calendar-check-fill"
                        color="#f59e0b"
                    />

                </div>

                <div className="col-lg-4 col-md-6">

                    <StatCard
                        title="Completed Rides"
                        value={dashboard.completedRides}
                        icon="bi-clock-history"
                        color="#7c3aed"
                    />

                </div>

                <div className="col-lg-4 col-md-6">

                    <StatCard
                        title="Total Spent"
                        value={`₹${dashboard.totalSpent}`}
                        icon="bi-cash-stack"
                        color="#ef4444"
                    />

                </div>

            </div>

            <ToastContainer position="top-right" />

        </DashboardLayout>

    );

}

export default Dashboard;