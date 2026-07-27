import { useEffect, useState } from "react";
import { ToastContainer, toast } from "react-toastify";

import DashboardLayout from "../../layouts/DashboardLayout";

import {
  getAllBikes,
  addBike,
  updateBike,
  deleteBike,
} from "../../services/bikeService";

import BikeFormModal from "../../components/operator/BikeFormModal";

function Bikes() {
  const [bikes, setBikes] = useState([]);
  const [loading, setLoading] = useState(true);

  const [showModal, setShowModal] = useState(false);
  const [selectedBike, setSelectedBike] = useState(null);

  const [searchTerm, setSearchTerm] = useState("");

  const loadBikes = async () => {
    try {
      setLoading(true);

      const data = await getAllBikes();

      setBikes(data);
    } catch (error) {
      console.error(error);
      toast.error("Failed to load bikes.");
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    loadBikes();
  }, []);

  const openAddModal = () => {
    setSelectedBike(null);
    setShowModal(true);
  };

  const openEditModal = (bike) => {
    setSelectedBike(bike);
    setShowModal(true);
  };

  const closeModal = () => {
    setShowModal(false);
    setSelectedBike(null);
  };

  const saveBike = async (bikeData) => {
    try {
      if (selectedBike) {
        await updateBike(selectedBike.id, bikeData);
        toast.success("Bike updated successfully.");
      } else {
        await addBike(bikeData);
        toast.success("Bike added successfully.");
      }

      closeModal();

      await loadBikes();
    } catch (error) {
      console.error(error);
      toast.error("Operation failed.");
    }
  };

  const handleDelete = async (bike) => {
    const confirmDelete = window.confirm(
      `Are you sure you want to delete "${bike.bikeNumber}"?`
    );

    if (!confirmDelete) return;

    try {
      await deleteBike(bike.id);

      toast.success("Bike deleted successfully.");

      await loadBikes();
    } catch (error) {
      console.error(error);
      toast.error("Failed to delete bike.");
    }
  };

  const filteredBikes = bikes.filter((bike) => {
    const keyword = searchTerm.toLowerCase();

    return (
      bike.bikeNumber.toLowerCase().includes(keyword) ||
      bike.model.toLowerCase().includes(keyword) ||
      bike.stationName.toLowerCase().includes(keyword) ||
      bike.status.toLowerCase().includes(keyword)
    );
  });

  const getStatusBadge = (status) => {
    switch (status) {
      case "AVAILABLE":
        return <span className="badge bg-success">Available</span>;

      case "RESERVED":
        return <span className="badge bg-warning text-dark">Reserved</span>;

      case "IN_USE":
        return <span className="badge bg-primary">In Use</span>;

      case "MAINTENANCE":
        return <span className="badge bg-danger">Maintenance</span>;

      default:
        return <span className="badge bg-secondary">{status}</span>;
    }
  };

  return (
    <DashboardLayout>
      <div className="d-flex justify-content-between align-items-center mb-4">
        <h2 className="dashboard-title mb-0">
          Bike Management
        </h2>

        <button
          className="btn btn-primary"
          onClick={openAddModal}
        >
          <i className="bi bi-plus-circle me-2"></i>
          Add Bike
        </button>
      </div>

      <div className="row mb-3">
        <div className="col-md-4">
          <input
            type="text"
            className="form-control"
            placeholder="🔍 Search bike..."
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
                  <th>Bike Number</th>
                  <th>Model</th>
                  <th>Status</th>
                  <th>Station</th>
                  <th width="140">Actions</th>

                </tr>

              </thead>

              <tbody>

                {filteredBikes.length === 0 ? (

                  <tr>

                    <td
                      colSpan="6"
                      className="text-center text-muted py-4"
                    >

                      No bikes found.

                    </td>

                  </tr>

                ) : (

                  filteredBikes.map((bike) => (

                    <tr key={bike.id}>

                      <td>{bike.id}</td>

                      <td>{bike.bikeNumber}</td>

                      <td>{bike.model}</td>

                      <td>{getStatusBadge(bike.status)}</td>

                      <td>{bike.stationName}</td>

                      <td>

                        <button
                          className="btn btn-warning btn-sm me-2"
                          onClick={() =>
                            openEditModal(bike)
                          }
                        >
                          <i className="bi bi-pencil"></i>
                        </button>

                        <button
                          className="btn btn-danger btn-sm"
                          onClick={() =>
                            handleDelete(bike)
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

      <BikeFormModal
        show={showModal}
        handleClose={closeModal}
        handleSave={saveBike}
        bike={selectedBike}
      />

      <ToastContainer position="top-right" />

    </DashboardLayout>
  );
}

export default Bikes;