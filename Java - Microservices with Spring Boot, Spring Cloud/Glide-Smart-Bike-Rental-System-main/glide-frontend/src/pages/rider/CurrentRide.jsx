import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import { ToastContainer, toast } from "react-toastify";

import DashboardLayout from "../../layouts/DashboardLayout";

import { getMyRides, endRide } from "../../services/rideService";
import { getAllStations } from "../../services/stationService";

function CurrentRide() {

    const navigate = useNavigate();

    const [ride, setRide] = useState(null);
    const [stations, setStations] = useState([]);
    const [stationId, setStationId] = useState("");
    const [loading, setLoading] = useState(true);

    

    const loadData = async () => {

        try {

            setLoading(true);

            const rides = await getMyRides();

            const activeRide = rides.find(
                ride => ride.status === "STARTED"
            );

            setRide(activeRide || null);

            const stationList = await getAllStations();

            setStations(stationList);

        } catch (error) {

            console.error(error);

            toast.error("Failed to load current ride.");

        } finally {

            setLoading(false);

        }

    };

    useEffect(() => {

        loadData(); 

    }, []);

    const handleEndRide = async () => {

        if (!stationId) {

            toast.warning("Please select a return station.");

            return;

        }

        try {

            await endRide(ride.rideId, stationId);

            toast.success("Ride completed successfully!");

            setTimeout(() => {

                navigate("/rider/history");

            }, 1200);

        } catch (error) {

            console.error(error);

            toast.error(
                error.response?.data?.message ||
                error.response?.data ||
                "Failed to end ride."
            );

        }

    };

    return (

        <DashboardLayout role="rider">

            <h2 className="dashboard-title mb-4">
                Current Ride
            </h2>

            {

                loading ? (

                    <div className="text-center mt-5">

                        <div
                            className="spinner-border text-primary"
                            role="status"
                        ></div>

                    </div>

                ) : !ride ? (

                    <div className="alert alert-info">

                        No active ride found.

                    </div>

                ) : (

                    <div className="card shadow border-0">

                        <div className="card-body">

                            <h4 className="mb-4">

                                🚴 Active Ride

                            </h4>

                            <p>

                                <strong>Bike Number:</strong>{" "}

                                {ride.bikeNumber}

                            </p>

                            <p>

                                <strong>Current Station:</strong>{" "}

                                {ride.stationName}

                            </p>

                            <p>

                                <strong>Started At:</strong>{" "}

                                {new Date(
                                    ride.startTime
                                ).toLocaleString()}

                            </p>

                            <p>

                                <strong>Status:</strong>{" "}

                                <span className="badge bg-primary">

                                    {ride.status}

                                </span>

                            </p>

                            <div className="mb-3">

                                <label className="form-label">

                                    Return Station

                                </label>

                                <select
                                    className="form-select"
                                    value={stationId}
                                    onChange={(e) =>
                                        setStationId(e.target.value)
                                    }
                                >

                                    <option value="">

                                        Select Station

                                    </option>

                                    {

                                        stations.map(station => (

                                            <option
                                                key={station.id}
                                                value={station.id}
                                            >

                                                {station.stationName}

                                            </option>

                                        ))

                                    }

                                </select>

                            </div>

                            <button
                                className="btn btn-danger"
                                onClick={handleEndRide}
                            >

                                End Ride

                            </button>

                        </div>

                    </div>

                )

            }

            <ToastContainer position="top-right" />

        </DashboardLayout>

    );

}

export default CurrentRide;