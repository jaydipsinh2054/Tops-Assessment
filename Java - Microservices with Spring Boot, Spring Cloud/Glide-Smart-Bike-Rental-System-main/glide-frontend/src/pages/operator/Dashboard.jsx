import { useEffect, useState } from "react";

import DashboardLayout from "../../layouts/DashboardLayout";
import StatCard from "../../components/dashboard/StatCard";

import { getDashboard } from "../../services/dashboardService";

function Dashboard() {

    const [dashboard, setDashboard] = useState(null);

    async function loadDashboard() {

        try {

            const data = await getDashboard();

            setDashboard(data);

        } catch (error) {

            console.error("Error loading dashboard:", error);

        }

    }

    useEffect(() => {

        loadDashboard();

    }, []);

    if (!dashboard) {

        return (

            <DashboardLayout>

                <h3>Loading Dashboard...</h3>

            </DashboardLayout>

        );

    }

    return (

        <DashboardLayout>

            <h2 className="dashboard-title">
                Operator Dashboard
            </h2>

            <div className="row g-4">

                <div className="col-lg-4 col-md-6">
                    <StatCard
                        title="Total Users"
                        value={dashboard.totalUsers}
                        icon="bi-people-fill"
                        color="#2563eb"
                    />
                </div>

                <div className="col-lg-4 col-md-6">
                    <StatCard
                        title="Total Stations"
                        value={dashboard.totalStations}
                        icon="bi-geo-alt-fill"
                        color="#10b981"
                    />
                </div>

                <div className="col-lg-4 col-md-6">
                    <StatCard
                        title="Total Bikes"
                        value={dashboard.totalBikes}
                        icon="bi-bicycle"
                        color="#f59e0b"
                    />
                </div>

                <div className="col-lg-4 col-md-6">
                    <StatCard
                        title="Available Bikes"
                        value={dashboard.availableBikes}
                        icon="bi-check-circle-fill"
                        color="#22c55e"
                    />
                </div>

                <div className="col-lg-4 col-md-6">
                    <StatCard
                        title="Active Rides"
                        value={dashboard.activeRides}
                        icon="bi-lightning-charge-fill"
                        color="#ef4444"
                    />
                </div>

                <div className="col-lg-4 col-md-6">
                    <StatCard
                        title="Total Revenue"
                        value={`₹${dashboard.totalRevenue}`}
                        icon="bi-currency-rupee"
                        color="#7c3aed"
                    />
                </div>

            </div>

        </DashboardLayout>

    );

}

export default Dashboard;