import { useEffect, useState } from "react";
import { ToastContainer, toast } from "react-toastify";

import DashboardLayout from "../../layouts/DashboardLayout";

import { getStationReports } from "../../services/reportService";

function Reports() {

    const [reports, setReports] = useState([]);

    const [loading, setLoading] = useState(true);

    const [searchTerm, setSearchTerm] = useState("");

    const loadReports = async () => {

        try {

            setLoading(true);

            const data = await getStationReports();

            setReports(data);

        } catch (error) {

            console.error(error);

            toast.error("Failed to load reports.");

        } finally {

            setLoading(false);

        }

    };

    useEffect(() => {

        loadReports();

    }, []);

    const filteredReports = reports.filter(report =>
        report.stationName
            .toLowerCase()
            .includes(searchTerm.toLowerCase())
    );

    return (

        <DashboardLayout role="operator">

            <div className="d-flex justify-content-between align-items-center mb-4">

                <div>

                    <h2 className="dashboard-title">

                        Station Reports

                    </h2>

                    <p className="text-muted">

                        Rides and revenue report for each station.

                    </p>

                </div>

            </div>

            <div className="card shadow-sm border-0 mb-4">

                <div className="card-body">

                    <input
                        type="text"
                        className="form-control"
                        placeholder="Search station..."
                        value={searchTerm}
                        onChange={(e) =>
                            setSearchTerm(e.target.value)
                        }
                    />

                </div>

            </div>

            {

                loading ?

                    (

                        <div className="text-center mt-5">

                            <div
                                className="spinner-border text-primary"
                                role="status"
                            ></div>

                        </div>

                    )

                    :

                    (

                        <div className="card shadow-sm border-0">

                            <div className="card-body">

                                <div className="table-responsive">

                                    <table className="table table-hover align-middle">

                                        <thead>

                                            <tr>

                                                <th>Station</th>

                                                <th>Total Bikes</th>

                                                <th>Available</th>

                                                <th>Completed Rides</th>

                                                <th>Total Revenue</th>

                                            </tr>

                                        </thead>

                                        <tbody>

                                            {

                                                filteredReports.map(report => (

                                                    <tr key={report.stationId}>

                                                        <td>

                                                            <strong>

                                                                {report.stationName}

                                                            </strong>

                                                        </td>

                                                        <td>

                                                            {report.totalBikes}

                                                        </td>

                                                        <td>

                                                            <span className="badge bg-success">

                                                                {report.availableBikes}

                                                            </span>

                                                        </td>

                                                        <td>

                                                            {report.completedRides}

                                                        </td>

                                                        <td className="fw-bold text-success">

                                                            ₹{report.totalRevenue}

                                                        </td>

                                                    </tr>

                                                ))

                                            }

                                        </tbody>

                                    </table>

                                </div>

                            </div>

                        </div>

                    )

            }

            <ToastContainer position="top-right" />

        </DashboardLayout>

    );

}

export default Reports;