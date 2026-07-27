import { useEffect, useState } from "react";
import { Modal, Button, Form } from "react-bootstrap";
import { toast } from "react-toastify";

function StationFormModal({

    show,
    handleClose,
    handleSave,
    station

}) {

    const [formData, setFormData] = useState({

        stationName: "",
        address: "",
        totalSlots: ""

    });

    useEffect(() => {

        if (station) {

            setFormData({

                stationName: station.stationName,
                address: station.address,
                totalSlots: station.totalSlots

            });

        } else {

            setFormData({

                stationName: "",
                address: "",
                totalSlots: ""

            });

        }

    }, [station, show]);

    const handleChange = (e) => {

        setFormData({

            ...formData,

            [e.target.name]: e.target.value

        });

    };

    const onSubmit = () => {

        if (
            !formData.stationName ||
            !formData.address ||
            !formData.totalSlots
        ) {

            toast.error("Please fill all fields.");

            return;

        }

        if (Number(formData.totalSlots) <= 0) {

            toast.error("Total slots must be greater than 0.");

            return;

        }

        handleSave({

            ...formData,

            totalSlots: Number(formData.totalSlots)

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

                    {station ? "Edit Station" : "Add Station"}

                </Modal.Title>

            </Modal.Header>

            <Modal.Body>

                <Form>

                    <Form.Group className="mb-3">

                        <Form.Label>Station Name</Form.Label>

                        <Form.Control
                            type="text"
                            name="stationName"
                            value={formData.stationName}
                            onChange={handleChange}
                        />

                    </Form.Group>

                    <Form.Group className="mb-3">

                        <Form.Label>Address</Form.Label>

                        <Form.Control
                            type="text"
                            name="address"
                            value={formData.address}
                            onChange={handleChange}
                        />

                    </Form.Group>

                    <Form.Group className="mb-3">

                        <Form.Label>Total Slots</Form.Label>

                        <Form.Control
                            type="number"
                            name="totalSlots"
                            value={formData.totalSlots}
                            onChange={handleChange}
                        />

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

export default StationFormModal;