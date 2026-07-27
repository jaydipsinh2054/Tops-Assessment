import { useEffect, useState } from "react";
import { ToastContainer, toast } from "react-toastify";

import DashboardLayout from "../../layouts/DashboardLayout";

import { getMyRides } from "../../services/rideService";

function RideHistory() {

    const [rides, setRides] = useState([]);
    const [loading, setLoading] = useState(true);

    const loadRideHistory = async () => {

        try {

            setLoading(true);

            const data = await getMyRides();

            setRides(data);

        } catch (error) {

            console.error(error);

            toast.error("Failed to load ride history.");

        } finally {

            setLoading(false);

        }

    };

    useEffect(() => {

        loadRideHistory();

    }, []);

    const getBadgeClass = (status) => {

        switch (status) {

            case "COMPLETED":
                return "bg-success";

            case "STARTED":
                return "bg-primary";

            case "CANCELLED":
                return "bg-danger";

            default:
                return "bg-secondary";

        }

    };

    return (

        <DashboardLayout role="rider">

            <div className="d-flex justify-content-between align-items-center mb-4">

                <div>

                    <h2 className="dashboard-title mb-1">

                        Ride History

                    </h2>

                    <p className="text-muted">

                        View all your previous rides.

                    </p>

                </div>

            </div>

            {

                loading ? (

                    <div className="text-center mt-5">

                        <div
                            className="spinner-border text-primary"
                            role="status"
                        ></div>

                    </div>

                ) : rides.length === 0 ? (

                    <div className="alert alert-info text-center">

                        No rides found.

                    </div>

                ) : (

                    <div className="row g-4">

                        {

                            rides.map((ride) => (

                                <div
                                    className="col-lg-6"
                                    key={ride.rideId}
                                >

                                    <div className="card shadow-sm border-0 h-100">

                                        <div className="card-body">

                                            <div className="d-flex justify-content-between mb-3">

                                                <h5 className="fw-bold">

                                                    🚲 {ride.bikeNumber}

                                                </h5>

                                                <span
                                                    className={`badge ${getBadgeClass(ride.status)}`}
                                                >

                                                    {ride.status}

                                                </span>

                                            </div>

                                            <p>

                                                <strong>Station:</strong>{" "}

                                                {ride.stationName}

                                            </p>

                                            <p>

                                                <strong>Started At:</strong>{" "}

                                                {new Date(
                                                    ride.startTime
                                                ).toLocaleString()}

                                            </p>

                                            <p>

                                                <strong>Ended At:</strong>{" "}

                                                {

                                                    ride.endTime
                                                        ? new Date(
                                                            ride.endTime
                                                        ).toLocaleString()
                                                        : "-"

                                                }

                                            </p>

                                            <p>

                                                <strong>Fare:</strong>{" "}

                                                <span className="badge bg-dark">

                                                    ₹{

                                                        ride.fare
                                                            ? ride.fare
                                                            : 0

                                                    }

                                                </span>

                                            </p>

                                        </div>

                                    </div>

                                </div>

                            ))

                        }

                    </div>

                )

            }

            <ToastContainer position="top-right" />

        </DashboardLayout>

    );

}

export default RideHistory;