import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import { toast, ToastContainer } from "react-toastify";

import DashboardLayout from "../../layouts/DashboardLayout";
import { getAllStations } from "../../services/stationService";

function Stations() {

    const navigate = useNavigate();

    const [stations, setStations] = useState([]);
    const [loading, setLoading] = useState(true);

    const loadStations = async () => {

        try {

            setLoading(true);

            const data = await getAllStations();

            setStations(data);

        } catch (error) {

            console.error(error);

            toast.error("Failed to load stations.");

        } finally {

            setLoading(false);

        }

    };

    useEffect(() => {

        loadStations();

    }, []);

    const viewBikes = (stationId) => {

        navigate(`/rider/stations/${stationId}/bikes`);

    };

    return (

        <DashboardLayout role="rider">

            <div className="d-flex justify-content-between align-items-center mb-4">

                <div>

                    <h2 className="dashboard-title mb-1">
                        Bike Stations
                    </h2>

                    <p className="text-muted mb-0">
                        Select a station to view available bikes.
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

                            stations.length === 0 ? (

                                <div className="col-12">

                                    <div className="alert alert-info text-center">

                                        No stations available.

                                    </div>

                                </div>

                            ) : (

                                stations.map((station) => (

                                    <div
                                        className="col-lg-4 col-md-6"
                                        key={station.id}
                                    >

                                        <div className="card shadow-sm border-0 h-100">

                                            <div className="card-body">

                                                <h4 className="fw-bold mb-3">

                                                    <i className="bi bi-geo-alt-fill text-danger me-2"></i>

                                                    {station.stationName}

                                                </h4>

                                                <p className="mb-2">

                                                    <i className="bi bi-pin-map me-2 text-secondary"></i>

                                                    {station.address}

                                                </p>

                                                <p className="mb-2">

                                                    <i className="bi bi-bicycle me-2 text-success"></i>

                                                    <strong>
                                                        Available Bikes :
                                                    </strong>{" "}

                                                    {station.availableBikes}

                                                </p>

                                                <p className="mb-4">

                                                    <i className="bi bi-grid-3x3-gap-fill me-2 text-primary"></i>

                                                    <strong>
                                                        Total Slots :
                                                    </strong>{" "}

                                                    {station.totalSlots}

                                                </p>

                                                <button
                                                    className="btn btn-primary w-100"
                                                    onClick={() => viewBikes(station.id)}
                                                >

                                                    <i className="bi bi-bicycle me-2"></i>

                                                    View Bikes

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

export default Stations;