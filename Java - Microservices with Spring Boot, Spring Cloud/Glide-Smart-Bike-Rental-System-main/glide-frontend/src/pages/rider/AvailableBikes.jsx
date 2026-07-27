import { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import { ToastContainer, toast } from "react-toastify";

import DashboardLayout from "../../layouts/DashboardLayout";

import {
    getAllBikes,
    getBikesByStation
} from "../../services/bikeService";

import { reserveBike } from "../../services/reservationService";

function AvailableBikes() {

    const { stationId } = useParams();

    const navigate = useNavigate();

    const [bikes, setBikes] = useState([]);
    const [loading, setLoading] = useState(true);

    const loadBikes = async () => {

        try {

            setLoading(true);

            let data;

            if (stationId) {

                data = await getBikesByStation(stationId);

            } else {

                data = await getAllBikes();

            }

            setBikes(
                data.filter(
                    bike => bike.status === "AVAILABLE"
                )
            );

        } catch (error) {

            console.error(error);

            toast.error("Failed to load bikes.");

        } finally {

            setLoading(false);

        }

    };

    useEffect(() => {

        loadBikes();

    }, [stationId]);

    const reserveSelectedBike = async (bikeId) => {

        try {

            await reserveBike(bikeId);

            toast.success("Bike reserved successfully!");

            loadBikes();

            navigate("/rider/reservations");

        } catch (error) {

            console.error(error);

            toast.error(
                error.response?.data?.message ||
                error.response?.data ||
                "Reservation failed."
            );

        }

    };

    return (

        <DashboardLayout role="rider">

            <div className="d-flex justify-content-between align-items-center mb-4">

                <div>

                    <h2 className="dashboard-title mb-1">

                        {stationId
                            ? "Available Bikes"
                            : "All Available Bikes"}

                    </h2>

                    <p className="text-muted">

                        {stationId
                            ? "Choose a bike from this station."
                            : "Browse all available bikes across every station."}

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

                ) : (

                    <div className="row g-4">

                        {

                            bikes.length === 0 ? (

                                <div className="col-12">

                                    <div className="alert alert-warning text-center">

                                        No available bikes found.

                                    </div>

                                </div>

                            ) : (

                                bikes.map((bike) => (

                                    <div
                                        className="col-lg-4 col-md-6"
                                        key={bike.id}
                                    >

                                        <div className="card shadow-sm border-0 h-100">

                                            <div className="card-body">

                                                <h4 className="fw-bold mb-3">

                                                    🚲 {bike.bikeNumber}

                                                </h4>

                                                <p>

                                                    <strong>Model:</strong>{" "}

                                                    {bike.model}

                                                </p>

                                                <p>

                                                    <strong>Station:</strong>{" "}

                                                    <span className="text-primary">

                                                        {bike.stationName}

                                                    </span>

                                                </p>

                                                <p>

                                                    <strong>Status:</strong>{" "}

                                                    <span className="badge bg-success">

                                                        Available

                                                    </span>

                                                </p>

                                                <button
                                                    className="btn btn-success w-100 mt-3"
                                                    onClick={() =>
                                                        reserveSelectedBike(bike.id)
                                                    }
                                                >

                                                    Reserve Bike

                                                </button>

                                            </div>

                                        </div>

                                    </div>

                                ))

                            )

                        }

                    </div>

                )

            }

            <ToastContainer position="top-right" />

        </DashboardLayout>

    );

}

export default AvailableBikes;