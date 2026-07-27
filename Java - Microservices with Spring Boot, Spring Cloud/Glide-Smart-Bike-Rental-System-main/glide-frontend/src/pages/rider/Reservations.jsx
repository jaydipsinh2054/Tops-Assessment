import { useEffect, useState } from "react";
import { toast, ToastContainer } from "react-toastify";

import DashboardLayout from "../../layouts/DashboardLayout";
import {
  getMyReservations,
  cancelReservation,
} from "../../services/reservationService";
import { startRide } from "../../services/rideService";

function Reservations() {
  const [reservations, setReservations] = useState([]);
  const [loading, setLoading] = useState(true);

  const loadReservations = async () => {
    try {
      setLoading(true);

      const data = await getMyReservations();

      setReservations(data);
    } catch (error) {
      console.error(error);

      toast.error("Failed to load reservations.");
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    loadReservations();
  }, []);

  const handleCancel = async (reservationId) => {
    if (!window.confirm("Cancel this reservation?")) {
      return;
    }

    try {
      await cancelReservation(reservationId);

      toast.success("Reservation cancelled successfully.");

      loadReservations();
    } catch (error) {
      console.error(error);

      toast.error(
        error.response?.data?.message ||
          error.response?.data ||
          "Failed to cancel reservation.",
      );
    }
  };

  const handleStartRide = async (reservationId) => {
    try {
      await startRide(reservationId);

      toast.success("Ride started successfully!");
      
    } catch (error) {
      console.error(error);

      toast.error(
        error.response?.data?.message ||
          error.response?.data ||
          "Failed to start ride.",
      );
    }
  };

  return (
    <DashboardLayout role="rider">
      <h2 className="dashboard-title mb-1">My Reservations</h2>

      <p className="text-muted mb-4">View and manage your bike reservations.</p>

      {loading ? (
        <div className="text-center mt-5">
          <div className="spinner-border text-primary" role="status"></div>
        </div>
      ) : reservations.length === 0 ? (
        <div className="alert alert-info text-center">
          No reservations found.
        </div>
      ) : (
        <div className="row g-4">
          {reservations.map((reservation) => (
            <div className="col-lg-6" key={reservation.reservationId}>
              <div className="card shadow-sm border-0">
                <div className="card-body">
                  <h4 className="fw-bold">🚲 {reservation.bikeNumber}</h4>

                  <hr />

                  <p>
                    <strong>Station :</strong> {reservation.stationName}
                  </p>

                  <p>
                    <strong>Status :</strong>{" "}
                    <span
                      className={`badge ${
                        reservation.status === "PENDING"
                          ? "bg-warning text-dark"
                          : reservation.status === "CANCELLED"
                            ? "bg-danger"
                            : "bg-success"
                      }`}
                    >
                      {reservation.status}
                    </span>
                  </p>

                  <p>
                    <strong>Reserved At :</strong>

                    <br />

                    {new Date(reservation.startTime).toLocaleString()}
                  </p>

                  <div className="d-flex gap-2 mt-4">
                    {reservation.status === "PENDING" && (
                      <>
                        <button
                          className="btn btn-success flex-fill"
                          onClick={() =>
                            handleStartRide(reservation.reservationId)
                          }
                        >
                          Start Ride
                        </button>

                        <button
                          className="btn btn-danger"
                          onClick={() =>
                            handleCancel(reservation.reservationId)
                          }
                        >
                          Cancel
                        </button>
                      </>
                    )}
                  </div>
                </div>
              </div>
            </div>
          ))}
        </div>
      )}

      <ToastContainer position="top-right" />
    </DashboardLayout>
  );
}

export default Reservations;
