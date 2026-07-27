import { useEffect, useState } from "react";
import { Modal, Button, Form } from "react-bootstrap";
import { toast } from "react-toastify";

import { getAllStations } from "../../services/stationService";

function BikeFormModal({

    show,
    handleClose,
    handleSave,
    bike

}) {

    const [formData, setFormData] = useState({

        bikeNumber: "",
        model: "",
        status: "AVAILABLE",
        stationId: ""

    });

    const [stations, setStations] = useState([]);

    const loadStations = async () => {

        try {

            const data = await getAllStations();

            setStations(data);

        } catch (error) {

            console.error(error);

            toast.error("Failed to load stations.");

        }

    };

    useEffect(() => {

        loadStations();

    }, []);

    useEffect(() => {

        if (bike) {

            setFormData({

                bikeNumber: bike.bikeNumber,
                model: bike.model,
                status: bike.status,
                stationId: bike.stationId

            });

        } else {

            setFormData({

                bikeNumber: "",
                model: "",
                status: "AVAILABLE",
                stationId: ""

            });

        }

    }, [bike, show]);

    const handleChange = (e) => {

        setFormData({

            ...formData,

            [e.target.name]: e.target.value

        });

    };

    const onSubmit = () => {

        if (

            !formData.bikeNumber ||
            !formData.model ||
            !formData.status ||
            !formData.stationId

        ) {

            toast.error("Please fill all fields.");

            return;

        }

        handleSave({

            ...formData,

            stationId: Number(formData.stationId)

        });

    };

    return (

        <Modal
            show={show}
            onHide={handleClose}
            centered
        >

            <Modal.Header closeButton>

                <Modal.Title>

                    {bike ? "Edit Bike" : "Add Bike"}

                </Modal.Title>

            </Modal.Header>

            <Modal.Body>

                <Form>

                    <Form.Group className="mb-3">

                        <Form.Label>Bike Number</Form.Label>

                        <Form.Control

                            type="text"

                            name="bikeNumber"

                            value={formData.bikeNumber}

                            onChange={handleChange}

                            placeholder="Enter Bike Number"

                        />

                    </Form.Group>

                    <Form.Group className="mb-3">

                        <Form.Label>Model</Form.Label>

                        <Form.Control

                            type="text"

                            name="model"

                            value={formData.model}

                            onChange={handleChange}

                            placeholder="Enter Bike Model"

                        />

                    </Form.Group>

                    <Form.Group className="mb-3">

                        <Form.Label>Status</Form.Label>

                        <Form.Select

                            name="status"

                            value={formData.status}

                            onChange={handleChange}

                        >

                            <option value="AVAILABLE">
                                AVAILABLE
                            </option>

                            <option value="RESERVED">
                                RESERVED
                            </option>

                            <option value="IN_USE">
                                IN USE
                            </option>

                            <option value="MAINTENANCE">
                                MAINTENANCE
                            </option>

                        </Form.Select>

                    </Form.Group>

                    <Form.Group>

                        <Form.Label>Station</Form.Label>

                        <Form.Select

                            name="stationId"

                            value={formData.stationId}

                            onChange={handleChange}

                        >

                            <option value="">
                                Select Station
                            </option>

                            {stations.map((station) => (

                                <option
                                    key={station.id}
                                    value={station.id}
                                >

                                    {station.stationName}

                                </option>

                            ))}

                        </Form.Select>

                    </Form.Group>

                </Form>

            </Modal.Body>

            <Modal.Footer>

                <Button
                    variant="secondary"
                    onClick={handleClose}
                >

                    Cancel

                </Button>

                <Button
                    variant="primary"
                    onClick={onSubmit}
                >

                    Save

                </Button>

            </Modal.Footer>

        </Modal>

    );

}

export default BikeFormModal;