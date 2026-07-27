import api from "./api";

export const reserveBike = async (bikeId) => {

    const response = await api.post(`/api/reservations/${bikeId}`);

    return response.data;

};

export const getMyReservations = async () => {

    const response = await api.get("/api/reservations/my");

    return response.data;

};

export const cancelReservation = async (reservationId) => {

    const response = await api.delete(
        `/api/reservations/${reservationId}`
    );

    return response.data;

};