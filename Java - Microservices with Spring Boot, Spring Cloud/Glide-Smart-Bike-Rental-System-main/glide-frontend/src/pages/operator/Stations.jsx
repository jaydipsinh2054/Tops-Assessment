import { useEffect, useState } from "react";
import { ToastContainer, toast } from "react-toastify";

import DashboardLayout from "../../layouts/DashboardLayout";
import {
  getAllStations,
  addStation,
  updateStation,
  deleteStation,
} from "../../services/stationService";

import StationFormModal from "../../components/operator/StationFormModal";

function Stations() {
  const [stations, setStations] = useState([]);
  const [loading, setLoading] = useState(true);

  const [showModal, setShowModal] = useState(false);
  const [selectedStation, setSelectedStation] = useState(null);

  const [searchTerm, setSearchTerm] = useState("");

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

  const openAddModal = () => {
    setSelectedStation(null);
    setShowModal(true);
  };

  const openEditModal = (station) => {
    setSelectedStation(station);
    setShowModal(true);
  };

  const closeModal = () => {
    setShowModal(false);
    setSelectedStation(null);
  };

  const saveStation = async (stationData) => {
    try {
      if (selectedStation) {
        await updateStation(selectedStation.id, stationData);
        toast.success("Station updated successfully.");
      } else {
        await addStation(stationData);
        toast.success("Station added successfully.");
      }

      closeModal();
      await loadStations();
    } catch (error) {
      console.error(error);
      toast.error("Operation failed.");
    }
  };

  const handleDelete = async (station) => {
    const confirmDelete = window.confirm(
      `Are you sure you want to delete "${station.stationName}"?`
    );

    if (!confirmDelete) return;

    try {
      await deleteStation(station.id);

      toast.success("Station deleted successfully.");

      await loadStations();
    } catch (error) {
      console.error(error);
      toast.error("Failed to delete station.");
    }
  };

  const filteredStations = stations.filter((station) => {
    const keyword = searchTerm.toLowerCase();

    return (
      station.stationName.toLowerCase().includes(keyword) ||
      station.address.toLowerCase().includes(keyword)
    );
  });

  return (
    <DashboardLayout>
      <div className="d-flex justify-content-between align-items-center mb-4">
        <h2 className="dashboard-title mb-0">
          Station Management
        </h2>

        <button
          className="btn btn-primary"
          onClick={openAddModal}
        >
          <i className="bi bi-plus-circle me-2"></i>
          Add Station
        </button>
      </div>

      <div className="row mb-3">
        <div className="col-md-4">
          <input
            type="text"
            className="form-control"
            placeholder="🔍 Search station..."
            value={searchTerm}
            onChange={(e) =>
              setSearchTerm(e.target.value)
            }
          />
        </div>
      </div>

      {loading ? (
        <div className="text-center mt-5">
          <div
            className="spinner-border text-primary"
            role="status"
          ></div>
        </div>
      ) : (
        <div className="card shadow-sm">
          <div className="card-body">
            <table className="table table-hover align-middle">

              <thead className="table-light">

                <tr>

                  <th>ID</th>
                  <th>Station Name</th>
                  <th>Address</th>
                  <th>Total Slots</th>
                  <th>Available Bikes</th>
                  <th width="140">Actions</th>

                </tr>

              </thead>

              <tbody>

                {filteredStations.length === 0 ? (

                  <tr>

                    <td
                      colSpan="6"
                      className="text-center text-muted py-4"
                    >

                      No stations found.

                    </td>

                  </tr>

                ) : (

                  filteredStations.map((station) => (

                    <tr key={station.id}>

                      <td>{station.id}</td>

                      <td>{station.stationName}</td>

                      <td>{station.address}</td>

                      <td>{station.totalSlots}</td>

                      <td>{station.availableBikes}</td>

                      <td>

                        <button
                          className="btn btn-warning btn-sm me-2"
                          onClick={() =>
                            openEditModal(station)
                          }
                        >
                          <i className="bi bi-pencil"></i>
                        </button>

                        <button
                          className="btn btn-danger btn-sm"
                          onClick={() =>
                            handleDelete(station)
                          }
                        >
                          <i className="bi bi-trash"></i>
                        </button>

                      </td>

                    </tr>

                  ))

                )}

              </tbody>

            </table>
          </div>
        </div>
      )}

      <StationFormModal
        show={showModal}
        handleClose={closeModal}
        handleSave={saveStation}
        station={selectedStation}
      />

      <ToastContainer position="top-right" />
    </DashboardLayout>
  );
}

export default Stations;